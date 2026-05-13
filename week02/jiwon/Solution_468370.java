package com.AlgoStudy.A1;
import java.util.*;

/*
 * 단어는 공백으로 구분
 * 중요한 단어가 몇개
 * 단어 중 하나이상 스포방지 구간에 포함 -> 스포방지단어
 * 한 단어 -> 여러스포 방지구간, 한 구간 -> 여러단어 가능
 * 
 * 중요단어
 * 스포방지 단어
 * 스포방지구간에만 등장
 * 이전 스포 방지 단어와 중복 안됨 
 * 여러단어가 동시공개면 왼쪽부터 중요단어
 * -----------------
 * 단어를 공백으로 구분해서
 * 단어별로 인덱스 구한다
 * StringTokenizer
 * 
 * 단어별로 훝어서 공개된 단어 목록을 만는다
 * 
 *왼쪽부터 중요단어이므로 spoiler_ranges를 정렬
 *
 * 단어의 인덱스중 하나라도 스포구간에 있으면 스포방지단어
 * HashSet에 넣는다
 * */
public class Solution_468370 {
	
	class Word{
		String text;
        int start, end;
        boolean isSpoiler = false;
        
        Word(String text,int start,int end){
        	this.text=text;
        	this.start = start;
            this.end = end;
        }
		
	}
	
	public int solution(String message, int[][] spoiler_ranges) {
		List<Word> allWords = new ArrayList<>();
		StringTokenizer st = new StringTokenizer(message);
		int lastIndex = 0;
		
		while (st.hasMoreTokens()) {
		    String word = st.nextToken();
		    int start = message.indexOf(word, lastIndex); 
		    int end = start + word.length() - 1;
		    allWords.add(new Word(word, start, end));
		    lastIndex = end + 1;
		}
		//이미 공개된 단어 목록
		//스포일러 구간에 포함 안된거
		Set<String> normalWords = new HashSet<>();
		for (Word w : allWords) {
			for (int[] range : spoiler_ranges) {
				//스포일러 구간이면
				if (!(w.end<range[0]||w.start>range[1])) {
					w.isSpoiler=true;
					break;
				}
			}
			//스포일러 구간이 아니면 공개된단어구간 에 추가한다
			if (!w.isSpoiler) {
				normalWords.add(w.text);
			}
		}
		//왼쪽 부터
		Arrays.sort(spoiler_ranges, (a, b) -> a[0] - b[0]);
        int answer = 0;
        Set<String> importantWords = new HashSet<>();
        boolean[] processed = new boolean[allWords.size()];
        
        for (int[] range : spoiler_ranges) {
        	//모든 단어에서
			for (int i = 0; i < allWords.size(); i++) {
				Word w = allWords.get(i);//내가 지금 조사하고 있는 단어
				
				if(processed[i]) continue;
				if (w.end < range[0] || w.start > range[1]) continue;//스포단어 구간 아님
				
				//normalWords에 없고 importantWords에 없는거 -> importantWords
				
				if (!normalWords.contains(w.text)&&!importantWords.contains(w.text)) {
					answer++;
					importantWords.add(w.text);
				}
				processed[i] = true;
			}
		}
        
        return answer;
    }

}
