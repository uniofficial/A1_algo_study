class Solution_389478 {
    public int solution(int n, int w, int num) {
        int answer = 0;
        
        int row = (num - 1) / w;
        int col = (row % 2 == 0) ? (num - 1) % w : w - 1 - ((num - 1) % w);
        
        int totalR = (n + w - 1) / w;
        int val; 
        
        for(int i = row; i < totalR; i++){
            if(i % 2 == 0){
                val = i * w + col + 1;
            } else {
                val = i * w + (w - col);
            }
            
            if(val <= n) answer++;
        }
        
        return answer;
    }

    // 테스트 케이스 #1
    public static void main(String[] args) {
        Solution_389478 s = new Solution_389478();
        System.out.println(s.solution(13, 3, 6)); 
    }
}