package com.AlgoStudy.A1;

import java.util.*;

/*
 * 하노이의 탑 - 재귀
 * Divide and Conquer
 * 
 * 가장 큰거 제외A n-1 개를 B에 옮김
 * 가장 큰거B를-> A-> C
 *	B에 있는거 전부를 C
 * */
public class Solution_12946 {
	List<int[]> list = new ArrayList<>();
	public void hanoi(int n, int A, int B, int C) {
		if (n==1) {
			list.add(new int[]{A,C});
			return;
		}
		hanoi(n-1, A, C, B);
		list.add(new int[]{A,C});
		hanoi(n-1, B, A, C);
	}
	
	 public int[][] solution(int n) {
		 hanoi(n, 1, 2, 3);
	       
		 return list.toArray(new int[list.size()][]);
	        
	       
	    }
}
