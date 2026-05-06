package com.AlgoStudy.A1;
/*
 * 
 * 행중심 지그재그
 * 
 * 전체 층수
 * (n - 1) / w + 1
 * 
 * w(row)
 * col 번호
 * 
 * if(targetRow % 2 ==0)짝수층(0층부터)
 * targetCol =(num - 1) % w 
 * if
 * targetRow % 2 ==1
 * 오른쪽 -> 왼쪽
 * targetCol =(w - 1) - ((num - 1) % w)
 * 
 * 꺼내야하는 상자의 
 * num의 col번호 알면 targetRow부터 totalRows까지 같은 col에 있는게 몇개인지 확인
 * 
 * 
 * */
public class Solution_389478 {
	 public int solution(int n, int w, int num) {
	        int answer = 0;
	        
	        int targetRow = (num - 1) / w;
	        int targetCol = 0;
	        int totalRows = (n - 1) / w + 1;
	        
	        if (targetRow%2==0) {
	        	targetCol =(num - 1) % w;
			}
	        else if (targetRow % 2 ==1) {
	        	targetCol =(w - 1) - ((num - 1) % w);
			}
	        
	        for (int i = targetRow; i < totalRows; i++) {//행
	        	int currentBoxNum = 0;
	        	
	        	if (i%2==0) {
					currentBoxNum=i*w+targetCol+1;
				}else {
	                currentBoxNum = i * w + (w - 1 - targetCol) + 1;
	            }
	        	
	        	if (currentBoxNum <= n) {
	                answer++;
	            }
	        	
			}
	        return answer;
	    }

}
