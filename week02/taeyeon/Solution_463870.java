import java.util.*;

class Solution {
    public int solution(String message, int[][] spoiler_ranges) {
        int answer = 0;
        boolean[] isSpoilerIndex = new boolean[message.length()];

        for (int i = 0; i < spoiler_ranges.length; i++) {
            int start = spoiler_ranges[i][0];
            int end = spoiler_ranges[i][1];
            for (int j = start; j <= end; j++) {
                isSpoilerIndex[j] = true;
            }
        }

        String[] words = message.split(" ");
        boolean[] wordIsSpoiler = new boolean[words.length];

        int currentIdx = 0;
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            for (int j = 0; j < word.length(); j++) {
                if (isSpoilerIndex[currentIdx + j]) {
                    wordIsSpoiler[i] = true;
                    break;
                }
            }
            currentIdx += word.length() + 1;
        }

        ArrayList<String> normalWords = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            if (!wordIsSpoiler[i]) {
                normalWords.add(words[i]);
            }
        }

        ArrayList<String> foundImportant = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            if (wordIsSpoiler[i]) {
                String target = words[i];
                if (!normalWords.contains(target) && !foundImportant.contains(target)) {
                    answer++;
                    foundImportant.add(target);
                }
            }
        }

        return answer;
    }
}