package com.algo;

import java.util.*;

class Solution {
    public int solution(String message, int[][] spoiler_ranges) {
    	
    	int count =0;
    	int curIdx = 0;
    	
    	List<String> spoilerWords = new ArrayList<>();
    	Set<String> noSpoilerWords = new HashSet<>();
    	
    	String[] words = message.split(" ");
    	
    	for(String word : words) {
    		int start = message.indexOf(word, curIdx);
    		int end = start + word.length() - 1;
    		
    		curIdx = end + 1;
    
    		boolean isSpoiler = false;
    		
        	for(int[] range : spoiler_ranges) {
        		int l = range[0];
        		int r = range[1];
        		
        		if(start <= r && end >= 1) {
        			isSpoiler = true;
        			break;
        		}
        	}
        	
        	if(isSpoiler) spoilerWords.add(word);
        	else noSpoilerWords.add(word);
    	}
    	
    	
    	Set<String> importantWords = new HashSet<>();
    	
    	for(String spoilerWord : spoilerWords) {
    		if(!noSpoilerWords.contains(spoilerWords)) {
    			importantWords.add(spoilerWord);
    		}
    	}

        return importantWords.size();
    }
}