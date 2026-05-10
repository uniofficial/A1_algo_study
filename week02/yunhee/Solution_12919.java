// 서울에서 김서방 찾기 

// String형 배열 seoul에서 "Kim" 위치 x 찾아 "김서방은 x에 있다" 반환 

package com.Algo.week02;

class Solution_12919 {
	public String solution(String[] seoul) {
		String answer="";
		
		for(int i=0; i<seoul.length; i++) {
			if(seoul[i].equals("Kim")) {
				answer = "김서방은 " + i + "에 있다";
				break;
			}
		}
		return answer;
	}

}
