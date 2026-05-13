package com.AlgoStudy.A1;
/*
 * 'Kim'의 위치
 * 
 * 문자열 배열을 돌면서 Kim을 찾으면
 * StringBuilder에 append
 * */

public class Solution_12919 {
	 public String solution(String[] seoul) {
		 StringBuilder sb = new StringBuilder();
		 sb.append("김서방은 ");
		 
		 for (int i = 0; i < seoul.length; i++) {
			if (seoul[i].equals("Kim")) {
				int count = i;
				sb.append(count).append("에 있다");
				break;
			}
		}
		 return sb.toString();
	    }
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution_12919 sol = new Solution_12919();
        String[] seoul = {"Jane", "Kim"};
        System.out.println(sol.solution(seoul));

	}

}
