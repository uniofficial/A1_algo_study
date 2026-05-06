public class Solution_Box {
    public int solution(int n, int w, int num) {
        int h = (n / w) + 1;
        int[][] box = new int[h][w];
        int idx = 1;
        boolean rightFirst = false;

        for (int i = 0; i < h; i++) {
            if (rightFirst) {
                for (int j = w - 1; j >= 0; j--) {
                    if (idx > n) break;
                    box[i][j] = idx++;
                }
                rightFirst = false;
            } else {
                for (int j = 0; j < w; j++) {
                    if (idx > n) break;
                    box[i][j] = idx++;
                }
                rightFirst = true;
            }
        }

        int targetX = -1, targetY = -1;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (box[i][j] == num) {
                    targetX = i;
                    targetY = j;
                    break;
                }
            }
        }

        int answer = 0;
        for (int i = targetX; i < h; i++) {
            if (box[i][targetY] != 0) {
                answer++;
            }
        }

        return answer;
    }
}