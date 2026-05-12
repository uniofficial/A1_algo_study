package com.algo;

public class Solution_12919 {

	class Solution {
	    public String solution(String[] seoul) {
	        String answer = "";
	        
	        for(int i=0; i<seoul.length; i++) {
	        	if(seoul[i].equals("Kim")) {
	        		return "김서방은 " +i + "에 있다";
	        		
	        	}
	        }
	        return "";
	    }
	}
}
