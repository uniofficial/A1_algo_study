package com.AlgoStudy.A1;
import java.util.*;
/*
 * 짝지어 제거
 * Stack 이용
 * if(isEmpty())
 * push
 * 
 * if(peek == current)
 * pop
 * */
public class Solution_12973 {
	
	 public int solution(String s)
	    {
		 Stack<Character> stack = new Stack<>();
		 
		 for (int i = 0; i < s.length(); i++) {
			char current = s.charAt(i);
			
			if (!stack.isEmpty() && stack.peek()==current) {
				stack.pop();
			}
			else {
				stack.push(current);
			}
		}
		 return stack.isEmpty() ? 1: 0;
	    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
Solution_12973 solver = new Solution_12973();
		
		
		String example1 = "baabaa"; 
		String example2 = "cdcd";  
		
	
		
		System.out.println(solver.solution(example1));
		System.out.println(solver.solution(example2));

	}

}
