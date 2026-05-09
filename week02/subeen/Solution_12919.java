public class Solution_12919 {
    public String solution(String[] seoul) {
        StringBuilder sb = new StringBuilder();
        sb.append("김서방은 ");
        
        int answer = -1;
        
        for(int i = 0; i < seoul.length; i++){
            if(seoul[i].equals("Kim"))
                answer = i;
        }
        
        sb.append(answer).append("에 있다");
        
        return sb.toString();
    }

    public static void main(String[] args){
        Solution_12919 s = new Solution_12919();
        System.out.println(s.solution(new String[] {"Jane", "Kim"}));
    }
}