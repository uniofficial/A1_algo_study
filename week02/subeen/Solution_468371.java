public class Solution_468371 {
    int[] a;
    
    public int solution(int[][] signals) {
        a = new int[signals.length];
        
        for(int i = 0; i < signals.length; i++){
            a[i] = signals[i][0] + signals[i][1] + signals[i][2];
        }
        
        int lcm = LCMArray(a);
        
        // i는 시간(초) 
        for(int i = 0; i < lcm; i++){
            int flag = 0; 
            
            // 확인할 배열 
            for(int j = 0; j < signals.length; j++){
                int red = signals[j][0];
                int yellow = signals[j][1];
                int cycle = a[j];
                
                int t = i % cycle;
                if(t >= red && t < red + yellow)
                    flag++;
                
            }
            
            if(flag == signals.length) 
                return i + 1;
        }
        
        return -1;
    }
    
    public int GCD(int a, int b){
        if(b == 0) return a;
        return GCD(b, a % b);
    }
    
    public int LCM(int a, int b) {
        return a / GCD(a, b) * b;
    }
                   
    public int LCMArray(int [] array){
        int result = array[0];
        
        for(int i = 1; i < array.length; i++){
            result = LCM(result, array[i]);
        }
        
        return result;
    }

    // Test 케이스 
    public static void main(String[] args){
        Solution_468371 s = new Solution_468371();
        System.out.println(
            s.solution(new int[][] {
                {3, 3, 3},
                {5, 4, 2},
                {2, 1, 2}
        })
);
    }
}
