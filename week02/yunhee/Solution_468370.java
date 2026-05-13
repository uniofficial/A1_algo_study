// 중요한 단어를 스포 방지 

package com.Algo.week02;

import java.util.HashSet;
import java.util.Set;

// 스포 방지 기능이 적용된 메시지(message)와 스포 방지 구간 배열(spoiler_ranges)이 주어졌을 때 
// '중요한 단어'의 개수를 구하는 문제

// 메시지 시작부터 왼 -> 오 순으로 스포 방지 구간 하나씩 클릭.
// 단어는 공백으로 구분. 알파벳 소문자와 숫자로만 구성된 연속 문자열. 
// 단어 구성하는 문자들의 인덱스 중 하나라도 스포 방지 구간 포함되면 스포 방지 단어 
// 한 단어가 여러 스포 방지 구간에 걸쳐 있을 수도, 하나의 스포 방지 구간에 여러 단어 포함될 수도 있음 

// 스포 방지 구간 클릭해 모든 문자가 공개되었을 때, 아래 조건 모두 만족하면 중요 단어. 
// 1. 스포 방지 단어여야 함 
// 2. 스포 방지 구간 아닌 구간에 등장한 적 없어야 함 
// 3. 이전에 공개된 스포 방지 단어와 중복 x 
// 4. 여러 단어 동시에 공개된 경우, 왼쪽부터 순서대로 하나씩 중요한 단어인지 판단 

/* 중요하지 않은 단어 Set으로 관리(일반 구간에 노출된 단어 전부 Set으로)
 * 스포 방지 구간 순회하며 이 단어가 중요하지 않은 단어 Set에 없는지 확
 * 뒤에서 중복 등장 시 걸러야 하므로 중요한 단어로 판별됐으면 카운트 올리고 이 단어 Set에 추가
 */

public class Solution_468370 {
    public int solution(String message, int[][] spoiler_ranges) {
        
    	// 1. 중요하지 않은 단어 Set (일반 구간에 한 번이라도 등장한 단어 모음) 만들기 
        Set<String> notImportant = new HashSet<>();
        int s = 0;	// 현재 탐색 중인 단어 시작 인덱스 
        while (s < message.length()) {	// message 전체 왼->오 순회 
        	
        	// 공백 나올 때까지 전진시켜 단어 끝 인덱스 찾고 자름 
            int t = s; 
            while (t < message.length() && message.charAt(t) != ' ') {
                t++;
            }
            
            // 해당 단어 notImportant에 추가할 것인지 판단 (true는 일반 구간 단어. 추가 대상.) 
            boolean toBeSaved = true; 
            
            // 이미 중요하지 않다고 판별된 단어와 스포 방지 단어는 확인할 필요 없음 
            if (notImportant.contains(message.substring(s,t))) 
            	toBeSaved = false;
            // 아직 notImportant에 없다면 스포 방지 구간과 겹치나 확인 
            else {
                for (int[] r : spoiler_ranges) { 
                    if (isIn(s,t-1,r[0],r[1])) toBeSaved = false;
                }
            }
            
            
            if (toBeSaved) {
            	// 어떤 스포 방지 구간과도 겹치지 않은 일반 구간 단어들 -> notImportant 저장 
            	notImportant.add(message.substring(s,t));
            }
            s = t + 1; // 공백 한 칸 건너뛰고 다음 단어 이어서 순회
        }
        
        // 2. 스포 방지 구간 순회하며 중요 단어 카운트 
        int cnt = 0;	// 최종 중요 단어 개수 
        for (int[] spoiler : spoiler_ranges) {
            int l = spoiler[0];	// 스포 방지 구간 왼쪽 경계 
            int r = spoiler[1]; // 스포 방지 구간 오른쪽 경계 
            
            while (l >= 0 && message.charAt(l) != ' ') {
                l--;
            }
            while (r < message.length() && message.charAt(r) != ' ') {
                r++; 
            }
            if (l + 1 > r) continue;
            String[] parts = message.substring(l+1, r).split(" ");
            
            for (String p : parts) {
                if (notImportant.contains(p)) continue;
                else {
                    notImportant.add(p);
                    cnt++;
                }
            }
        }
        return cnt;
        
    }
    public boolean isIn(int s1, int s2, int r1, int r2) {
        if (s1 <= r2 && s2 >= r1) return true;
        else return false;
    }
}