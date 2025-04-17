//https://www.naukri.com/code360/problems/ninja-s-training_3621003?source=youtube&campaign=striver_dp_videos&utm_source=youtube&utm_medium=affiliate&utm_campaign=striver_dp_videos&leftPanelTabValue=PROBLEM
//https://www.youtube.com/watch?v=AE39gJYuRog

public class Solution {
    private static final String MEMOIZATION = "MEMOIZATION";
    private static final String TABULAR = "TABULAR";
    private static final String TABULAR_OPTIMIZED_SPACE = "TABULAR_OPTIMIZED_SPACE";

    private static int[][] initDP(int n) {
        int[][] dp = new int[n][4];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 4; j++) {
                dp[i][j] = -1;
            }
        }
        return dp;
    }

    public static int ninjaMemoization(int day, int[][] points, int last, int[][] dp) {
        if (day < 0) return 0;

        if (dp[day][last] != -1) return dp[day][last];

        if (day == 0) {
            int max = 0;
            for (int i = 0; i < 3; i++) {
                if (i != last) {
                    max = Math.max(max, points[0][i]);
                }
            }
            dp[0][last] = max; // memoize the base case
            return max;
        }

        int maxPoints = 0;

        for (int task = 0; task < 3; task++) {
            if (task != last) {
                int point = points[day][task] + ninjaMemoization(day - 1, points, task, dp);
                maxPoints = Math.max(maxPoints, point);
            }
        }

        dp[day][last] = maxPoints;
        return maxPoints;
    }

    private static int ninjaTabular(int[][] points, int[][] dp, int n) {
        for(int day=1;day<n;day++) {
            for(int last=0;last<4;last++) {
                dp[day][last] = 0;
                for(int task=0;task<3;task++) {
                    if(task!=last) {
                        int point = points[day][task] + dp[day-1][task];
                        dp[day][last] = Math.max(dp[day][last],point);
                    }
                }
            }
        }
        return dp[n-1][3];
    }
    

    public static int ninjaTraining(int n, int[][] points) {
        String approach = TABULAR;

        if(approach.equals(MEMOIZATION)) {
            int[][] dp=initDP(n);
            return ninjaMemoization(n - 1, points, 3, dp);
        } else if(approach.equals(TABULAR)) {
            int[][] dp=initDP(n);
            dp[0][0] = Math.max(points[0][1],points[0][2]);
            dp[0][1] = Math.max(points[0][0],points[0][2]);
            dp[0][2] = Math.max(points[0][0],points[0][1]);
            dp[0][3] = Math.max(Math.max(points[0][0],points[0][1]),points[0][2]);
            return ninjaTabular(points, dp, n);
        } else {
            return 0;
        }
    }

}
