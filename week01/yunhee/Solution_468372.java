// 리프 노드 수 최대화 

// 같은 레벨에 있는 분배 노드 자식 수 모두 같아야 함 
// 루트 노드는 자식 노드 하나 가짐 (분배도 계산에 영향 x) 

// cur: 현재 레벨에서 분배 가능한 노드 개수 
// leaf: 확정된 리프 노드 수
// DFS로 내려가면서 max(answer, leaf + cur) 갱신하고 자식노드 2개/3개 중 선택
// 다음 레벨의 노드 수는 cur * child 
// 분배 노드 개수 <= dist_limit 이므로 다음 레벨로 min(cur * child, dist_limit - 지난 노드 개수) 넘김 
// 리프 노드 분배도 <= split_limit 

package com.Algo;
	
public class Solution_468372 {
	int answer=1;
	
	public int solution(int dist_limit, int split_limit) {
		answer=1;
		dfs(1, 1, 1, 0, dist_limit, split_limit);
		return answer;
	}
	
	// used: 지난 분배 노드 개수, split: 현재 레벨 분배도, leaf: 현재 리프 노드 수 
	void dfs(long cur, long used, long split, long leaf, int dl, int sl) {
		if (used > dl) return;	// 지난 분배 노드 개수가 dist_limit 넘으면 리턴 
		answer = (int)Math.max(answer, leaf+cur);
		
		for(int child=2; child<=3; child++) {	// 자식노드 2개/3개 중 선택 
			long nextSplit = split * child;	// 다음 레벨 분배도는 현재까지의 분배도와 자식노드 개수 곱한 것 
			if (nextSplit > sl) continue;	// 리프 노드 분배도 제한 
			
			long nextCur = Math.min(cur*child, dl - used);	
			long nextLeaf = leaf+(cur*child - nextCur);
			
			dfs(nextCur, used+nextCur, nextSplit, nextLeaf, dl, sl);
		}
	}
}