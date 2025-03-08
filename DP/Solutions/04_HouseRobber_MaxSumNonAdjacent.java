//https://leetcode.com/problems/house-robber/

class Solution {
    private static final String MEMOIZATION = "Memoization";
    private static final String TABULAR = "Tabular";
    private static final String TABULAR_OPTIMIZED_SPACE = "Tabular Optimized Space";

    public int robMemoization(int ind, int[] nums, int dp[]) {
        if(ind == 0) return nums[ind];
        if(ind < 0) return 0;
        if(dp[ind] != -1) return dp[ind];
        int pick = nums[ind] + robMemoization(ind-2,nums,dp);
        int notPick = 0 + robMemoization(ind-1,nums,dp);
        return dp[ind] = Math.max(pick, notPick);
    }

    public int robTabular(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        for(int i=1;i<n;i++) {
            int pick = nums[i];
            if(i-2>=0) {
                pick += dp[i-2];
            }
            int notPick = 0 + dp[i-1];
            dp[i] = Math.max(pick, notPick);
        }
        return dp[n-1];
    }

    public int robTabularOptimized(int[] nums) {
        int prev2 = 0;
        int prev = nums[0];
        int n = nums.length;
        for(int i=1;i<n;i++) {
            int pick = nums[i];
            if(i-2>=0) {
                pick += prev2;
            }
            int notPick = 0 + prev;
            int curr = Math.max(pick, notPick);
            prev2 = prev;
            prev = curr;
        }
        return prev;
    }

    public int rob(int[] nums) {
        String approach = "Tabular Optimized Space";

        if(approach.equals(MEMOIZATION)) {
            int n = nums.length;
            int[] dp = new int[n];
            Arrays.fill(dp,-1);
            return robMemoization(n-1,nums,dp);
        } else if(approach.equals(TABULAR)) {
            return robTabular(nums);
        } else if(approach.equals(TABULAR_OPTIMIZED_SPACE)) {
            return robTabular(nums);
        }
        return 0;
    }
}
