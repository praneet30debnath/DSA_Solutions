//https://takeuforward.org/dynamic-programming/striver-dp-series-dynamic-programming-problems
//https://www.youtube.com/watch?v=sdE0A2Oxofw

class Solution {
    private static final String MEMOIZATION = "MEMOIZATION";
    private static final String TABULAR = "TABULAR";
    private static final String TABULAR_OPT = "TABULAR_OPT";

    private int countPathMemoized(int x, int y, int[][] dp) {
        if(x==0 && y==0) return 1;

        if(x<0 || y<0) return 0;

        if(dp[x][y]!=-1) return dp[x][y];

        int up = countPathMemoized(x-1,y,dp);
        int left = countPathMemoized(x,y-1,dp);

        dp[x][y] = up + left;

        return dp[x][y];
    }

    private int countPathTabular(int m, int n, int[][] dp) {
        // Step 1: Fill the first row and column with 1
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }

        // Step 2: Fill the rest of the grid
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        // Step 3: Return the value at bottom-right corner
        return dp[m - 1][n - 1];
    }

    private int[][] initDP(int m, int n) {
        int[][] dp = new int[m][n];
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                dp[i][j] = -1;
            }
        }
        return dp;
    }

    public int uniquePaths(int m, int n) {
        String approach = TABULAR;

        if(approach.equals(MEMOIZATION)) {
            int[][] dp = initDP(m,n);
            return countPathMemoized(m-1,n-1,dp);
        } else if(approach.equals(TABULAR)) {
            int[][] dp = initDP(m,n);
            return countPathTabular(m,n,dp);
        } else {
            return 0;
        }
    }
}
