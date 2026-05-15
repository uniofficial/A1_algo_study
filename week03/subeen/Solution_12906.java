import java.util.ArrayList;
import java.util.Arrays;

class Solution_12906 {
    public int[] solution(int[] arr) {
        ArrayList<Integer> list = new ArrayList<>();
        
        int prev = 10;
        
        for(int i = 0; i < arr.length; i++){
            if(prev != arr[i]){
                list.add(arr[i]);
                prev = arr[i];
            }
        }

        int[] answer = new int[list.size()];

        for(int i = 0 ; i < answer.length; i++){
            answer[i] = list.get(i);
        }

        return answer;
    }

    public static void main(String[] args) {
        Solution_12906 s = new Solution_12906();
        System.out.println(Arrays.toString(s.solution(new int[] {1,1,3,3,0,1,1})));
    }
}
