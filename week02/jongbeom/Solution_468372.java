package com.algo;

import java.util.*;

public class Solution_468372 {
    class Solution {
    	int answer = 1;

        public int solution(int dist_limit, int split_limit) {
            answer =1;
            dfs(1, 1, 1, 0, dist_limit, split_limit);
            return answer;
        }
        
        void dfs(long cur, long used, long split, long leaf, int distLimit, int splitLimit) {
        	if(used > distLimit) return;
        	
        	answer = (int)Math.max(answer, leaf + cur);
        	
        	for(int child = 2; child <=3; child++) {
        		
        		long nextSplit = split * child;
        		if(nextSplit > splitLimit) continue;
        		
        		long nextNodes = cur * child;
        		
        		long remain = distLimit - used;
        		
        		long nextCur = Math.min(nextNodes, remain);
        		
        		long nextLeaf = leaf + (nextNodes - nextCur);
        		
        		dfs(nextCur, used+ nextCur, nextSplit , nextLeaf, distLimit, splitLimit);
        	}
        }
    }
}