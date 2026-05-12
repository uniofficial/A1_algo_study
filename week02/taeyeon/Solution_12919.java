import java.util.*;

class Solution {
    public String solution(String[] seoul) {
        String answer = "";

        int idx = 0;
        int s_l = seoul.length;

        for(int i =0; i<s_l; i++){
            if(seoul[i].equals("Kim"))
            {   idx = i;
                break;}
        }

        answer = "김서방은 " + idx + "에 있다";

        return answer;
    }
}
