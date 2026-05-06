public class Solution_468371 {
    public int solution(int[][] signals) {
        int n = signals.length;
        long[] cycles = new long[n];

        for (int i = 0; i < n; i++) {
            cycles[i] = (long) signals[i][0] + signals[i][1] + signals[i][2];
        }

        long period = cycles[0];
        for (int i = 1; i < n; i++) {
            period = getLcm(period, cycles[i]);
        }
        
        for (int t = 1; t <= period; t++) {
            boolean allYellow = true;
            for (int i = 0; i < n; i++) {
            int green = signals[i][0];
                int yellow = signals[i][1];
                long pos = (t - 1) % cycles[i];

                if (pos < green || pos >= green + yellow) {
                    allYellow = false;
                    break;
                }
            }
            if (allYellow) return t;
        }

        return -1;
    }

    private long getLcm(long a, long b) {
        return (a / getGcd(a, b)) * b;
    }

    private long getGcd(long a, long b) {
        while (b != 0) {
            long temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }
}