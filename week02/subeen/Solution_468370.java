import java.util.HashSet;
import java.util.Set;

public class Solution_468370 {

    public int solution(String message, int[][] spoiler_ranges) {
        int answer = 0;
        
        boolean[] isSpoiler = new boolean[message.length()];
        
        for(int[] p : spoiler_ranges){
            for(int i = p[0]; i <= p[1]; i++)
                isSpoiler[i] = true;
        }
        
        String[] words = message.split(" ");
        
        Set<String> spoilerWord = new HashSet<>();
        Set<String> normalWord = new HashSet<>();
        
        int idx = 0;
        
        for(String word : words){
            boolean hasSpo = false; 
            
            for(int i = 0; i < word.length(); i++){
                if(isSpoiler[idx + i]){
                    hasSpo = true;
                    break;
                }
            }
            
            if(hasSpo) 
                spoilerWord.add(word);
            else 
                normalWord.add(word);
            
            idx += word.length() + 1;
        }
        
        for(String word : spoilerWord){
            if(!normalWord.contains(word)) answer++;
        }
        
        return answer;
    }

    // Test 케이스 
    public static void main(String[] args) {
        String message = "my phone number is 01012345678 and may i have your phone number";
        int[][] spoiler = {{5, 5}, {25, 28}, {34, 40}, {53, 59}};

        Solution_468370 s = new Solution_468370();
        System.out.println(s.solution(message, spoiler));
    }
}