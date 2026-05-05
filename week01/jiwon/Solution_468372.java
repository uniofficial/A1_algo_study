package com.AlgoStudy.A1;
import java.util.*;
/*
 * 루트노드: 자식-1
 * 자식노드: 자식-2,3-분배노드,0
 * 분배노드<dist_limit
 * 깊이: 루드노드 to 해당노드 최단거리
 * 깊이 == -> 분배노드의 자식 수 ==
 * 리프노드: 자식노드 0
 * 분배도 == 루트 to 리프의 부모까지 최단거리에 있는 모든 노드의 자식 노드 개수의 곱
 * 분배도 < split_limit
 * 
 * 경로가 아닌 상태를 보는 문제
 * 트리의 앞의 해 -> 뒤의 해
 * 중복X -> DP_memoization
 * 
 * current_dist, current_split, current_leaves
 * 
 * current_dist > dist_limit
 * current_split> split_limit
 * return 0
 * 
 * child 2 or 3 중에서 max-> DFS
 * 깊이 == -> 분배노드의 자식 수 ==이여서
 * current_split(n+1)=current_split(n+1)*child
 * */
public class Solution_468372 {
	
	// 상태를 저장할 메모이제이션 맵
    // Key를 String으로 하되, 재귀 깊이가 깊지 않아 효율적으로 동작합니다.
	// [메모리 초과 방지를 위해 주석 처리]
    // private Map<String, Integer> memo = new HashMap<>();
    private int dLimit;
    private int sLimit;
    
	 public int solution(int dist_limit, int split_limit) {
		 	this.dLimit = dist_limit;
	        this.sLimit = split_limit;
//	        memo.clear();
	        
	        return dp(1, 1, 1, 0);
	        
	    }
	 public int dp(int current_dist, int current_split, int current_leaves, int confirmed_leaves) {
//		// 이미 계산한 상태라면 결과 반환
//		 String key = current_dist + "," + current_split + "," + current_leaves + "," + confirmed_leaves;
//	        if (memo.containsKey(key)) {
//	            return memo.get(key);
//	        }

	        // 현재 리프 노드 수를 일단 최댓값으로 설정 (여기서 멈추는 경우)
	        int max_leaves = current_leaves + confirmed_leaves;
		
		for (int k : new int[]{2, 3}) {
			
			long next_split = (long)current_split*k;
			if (next_split > sLimit) continue;
			
			// 다음 층에서 분배 노드가 될 수 있는 후보는 current_leaves * k 개입니다.
            // 하지만 예산(dLimit - current_dist)이 부족하면 그만큼만 분배 노드로 씁니다.
			
			long total_children = (long)current_leaves * k;
			long next_leaves = total_children;
			
            long next_dist_candidates = Math.min(total_children, (long)dLimit - current_dist);
            long next_dist = (long)current_dist + next_dist_candidates;
            
			
         // 분배 노드가 되지 못한 자식들은 confirmed_leaves에 누적됨
            long next_confirmed = confirmed_leaves + (total_children - next_dist_candidates);

            if (next_dist_candidates > 0) {
                // 다음 층으로 내려감 (next_dist_candidates가 새로운 current_leaves가 됨)
                max_leaves = Math.max(max_leaves, dp((int)next_dist, (int)next_split, (int)next_dist_candidates, (int)next_confirmed));
            } else if (next_dist_candidates == 0 && total_children > 0) {
                // 더 이상 분배 노드를 못 만들지만 자식은 생겼을 때, 그 자식들을 모두 리프에 포함
                max_leaves = Math.max(max_leaves, (int)(total_children + confirmed_leaves));
            }
		}
//		memo.put(key, max_leaves);
        return max_leaves;
		 
	 }

}
