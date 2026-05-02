import java.util.ArrayList;
import java.util.List;

public class Solution_12946 {
    List<int[]> list = new ArrayList<>();
    
    public int[][] solution(int n) {
        hanoi(n, 1, 3, 2);
        return list.toArray(int[][]::new);
    }

    void hanoi(int n, int from, int to, int via){
        if(n == 1){
            list.add(new int[]{from, to});
            return ;
        }
        
        hanoi(n - 1, from, via, to);
        list.add(new int[] {from, to});
        hanoi(n - 1, via, to, from);
    }

    // 테스트 케이스 #1
    public static void main(String[] args) {
        Solution_12946 s = new Solution_12946();
        StringBuilder sb = new StringBuilder();
        
        int[][] result = s.solution(2);

        sb.append("[");
        for (int i = 0; i < result.length; i++) {
            sb.append("[").append(result[i][0]).append(",").append(result[i][1]).append("]");
            if (i != result.length - 1) sb.append(",");
        }
        sb.append("]");
        System.out.println(sb);
    }
}
