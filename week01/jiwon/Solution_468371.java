package com.Algo;
import java.util.*;
public class Solution_468371 {
	/*
	 * 신호등 주기: 초+노+빨
	 * 노란불일 조건:	초록불 + 노란불 에서 시작 + 주기 마다
	 * 3초 끝, 8초 끝, 13초 끝
	 * 6초 끝, 13초 끝
	 * 
	 * */
	    public int solution(int[][] signals) {
	        // 1. 모든 신호등의 개별 주기를 고려한 전체 최소공배수(LCM) 계산
	        long totalLcm = 1;
	        for (int[] s : signals) {
	            int cycle = s[0] + s[1] + s[2];
	            totalLcm = getLcm(totalLcm, (long) cycle);
	        }

	        // 2. 1초부터 LCM까지 순차 탐색
	        for (long time = 1; time <= totalLcm; time++) {
	            boolean allYellow = true;
	            
	            for (int[] s : signals) {
	                int g = s[0];
	                int y = s[1];
	                int cycle = g + y + s[2];
	                
	                long remain = (time - 1) % cycle;
	                
	                // 해당 시각에 노란불이 아니면 즉시 중단
	                if (!(g <= remain && remain < g + y)) {
	                    allYellow = false;
	                    break;
	                }
	            }
	            
	            // 모든 신호등이 노란불인 경우에만 결과 반환
	            if (allYellow) {
	                return (int) time;
	            }
	        }

	        // LCM 한 바퀴를 돌 때까지 찾지 못하면 -1
	        return -1;
	    }

	    // 최소공배수(LCM) 계산
	    private long getLcm(long a, long b) {
	        if (a == 0 || b == 0) return 0;
	        return (a * b) / getGcd(a, b);
	    }

	    // 최대공약수(GCD) 계산
	    private long getGcd(long a, long b) {
	        while (b > 0) {
	            long r = a % b;
	            a = b;
	            b = r;
	        }
	        return a;
	    }
	}