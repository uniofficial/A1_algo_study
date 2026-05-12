public class Solution_468372 {
    
    public long solution(int dist_limit, int split_limit) {
        long answer = 1; 

        for (int i = 0; ; i++) {
            long pow2 = pow(2, i);
            if (pow2 > split_limit) break;

            for (int j = 0; ; j++) {
                long path = pow2 * pow(3, j);
                if (path > split_limit) break;
                
                long result = simulate(i, j, dist_limit);
                answer = Math.max(answer, result);
            }
        }
        
        return answer;
    }

    private long simulate(int i, int j, int dist_limit) {
        long frontier = 1;
        long budget = dist_limit;
        long leaf = 1;
        

        for (int depth = 0; depth < i; depth++) {
            if (budget >= frontier) {
                budget -= frontier;
                leaf += frontier; 
                frontier *= 2;
            } else {
                leaf += budget;
                return leaf;
            }
        }

        for (int depth = 0; depth < j; depth++) {
            if (budget >= frontier) {
                budget -= frontier;
                leaf += frontier * 2; 
                frontier *= 3;
            } else {
                leaf += budget * 2;
                return leaf;
            }
        }
        
        return leaf;
    }
    
    private long pow(int base, int exp) {
        long result = 1;
        for (int i = 0; i < exp; i++) {
            result *= base;
        }
        return result;
    }

    // Test 케이스 
    public static void main(String[] args){
        Solution_468372 s = new Solution_468372();
        System.out.println(s.solution(3, 6));
    }
}
