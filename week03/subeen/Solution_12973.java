import java.util.ArrayDeque;
import java.util.Deque;

class Solution_12973 {
    public int solution(String s)
    {
        Deque<Character> stack = new ArrayDeque<>();
        
        for(char ch : s.toCharArray()){
            if(!stack.isEmpty() && stack.peek() == ch){
                stack.pop();
            } else
                stack.push(ch);
        }
        
        
        return stack.isEmpty() ? 1 : 0;
    } 
    
    public static void main(String[] args){
        Solution_12973 s = new Solution_12973();
        System.out.println(s.solution("baabaa"));
        System.out.println(s.solution("cdcd"));
    }
}
