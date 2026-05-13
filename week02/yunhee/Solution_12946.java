// 하노이의 탑 

/* 1번 기둥에 있는 원판 개수 n이 주어졌을 때, n개의 원판을 3번 원판으로 최소 횟수로 옮기는 방법은? 
 * 1. 한 번에 하나의 원판만 이동 가능 
 * 2. 큰 원판이 작은 원판 위에 있어서는 안 됨 
 */

/* 1번 기둥 n개 중 n-1개 2번으로 
 * 1번 기둥에 남아 있는 1개 3번으로 
 * 2번 기둥 n-1개 3번으로 
 */

package com.Algo.week02;

import java.util.ArrayList;
import java.util.List;

public class Solution_12946 {
	private static List<int[]> ansList;	// 원판 이동 기록 담을 리스트 
	
	public int[][] solution(int n) {
		ansList = new ArrayList<>();
		hanoi(n, 1, 3, 2);
		
		int[][] answer = new int[ansList.size()][];	// 행 수가 총 이동횟수이므로 
		
		for(int i=0; i<ansList.size(); i++) {
			answer[i]=ansList.get(i);
		}
		return answer;
	}
	
	// 재귀 
	// n: 현재 옮겨야 할 원판 수, start: 출발 기둥, to: 도착 기둥, mid: 경유 기둥 
	private static void hanoi(int n, int start, int to, int mid) {
		
		// 기저: 원판 1개면 바로 원판 옮기고 종료 
		if(n==1) {
			ansList.add(new int[] {start, to});
			return;
		}
		
		hanoi(n-1, start, mid, to);	
		ansList.add(new int[] {start, to});
		hanoi(n-1, mid, to, start);
	}
}
