import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

class Solution_42889 {

    public int[] solution(int N, int[] stages) {
        int[] answer = new int[N];
        
        HashMap<Integer, Integer> hm = new HashMap<>();
        
        for(int a : stages){
            hm.put(a, hm.getOrDefault(a, 0) + 1);
        }
        
        int less = 0;
        int p = stages.length;
        
        ArrayList<double[]> list = new ArrayList<>();
        
        for(int i = 1; i <= N; i++){
            int value = hm.getOrDefault(i, 0);
            
            double fail = 0;
            
            if(p - less > 0){
                fail = (double)value / (p - less);
            }
            less += value;
            list.add(new double[]{(double)i, fail});
        }

        Collections.sort(list, (a, b) -> {
            if(a[1] == b[1])
                return Double.compare(a[0], b[0]);
            return Double.compare(b[1], a[1]); 
        });
        
        for(int i = 0; i < list.size();i++){
            double[] arr = list.get(i);
            
            answer[i] = (int)arr[0];
        }
        
        return answer;
    }

    public static void main(String[] args) {
        Solution_42889 s = new Solution_42889();

        int[] stage1 = {2, 1, 2, 6, 2, 4, 3, 3};
        int[] stage2 = {4,4,4,4,4};

        System.out.println(Arrays.toString(s.solution(5, stage1)));
        System.out.println(Arrays.toString(s.solution(4, stage2)));
    }
}
