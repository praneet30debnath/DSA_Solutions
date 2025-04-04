//https://www.geeksforgeeks.org/problems/geek-jump/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=geek-jump
//https://takeuforward.org/data-structure/dynamic-programming-frog-jump-dp-3/

class Solution {
    private static final String MEMOIZATION = "MEMOIZATION";
    private static final String TABULAR = "TABULAR";
    private static final String TABULAR_OPTIMIZED = "TABULAR_OPTIMIZED";
    
    static int solveMemoization(int ind,int[] height,int[] dp){
        if(ind==0) return 0;
        if(dp[ind]!=-1) return dp[ind];
        int jumpTwo = Integer.MAX_VALUE;
        int jumpOne= solveMemoization(ind-1, height,dp)+ Math.abs(height[ind]-height[ind-1]);
        if(ind>1)
            jumpTwo = solveMemoization(ind-2, height,dp)+ Math.abs(height[ind]-height[ind-2]);
        
        return dp[ind]=Math.min(jumpOne, jumpTwo);
    }
    
    static int solveTabular(int[] dp, int[] height) {
        dp[0] = 0;
        for(int ind=1;ind<dp.length;ind++) {
            int firstStep = dp[ind-1] + Math.abs(height[ind]-height[ind-1]);
            int secondStep = Integer.MAX_VALUE;
            if(ind-2>=0)
                secondStep = dp[ind-2] + Math.abs(height[ind]-height[ind-2]);
                
            dp[ind] = Math.min(firstStep,secondStep);
        }
        return dp[dp.length-1];
    }
    
    static int solveTabularSpaceOpt(int[] height) {
        int prev = 0;
        int prev2 = 0;
        for(int ind=1;ind<height.length;ind++) {
            int firstStep = prev + Math.abs(height[ind]-height[ind-1]);
            int secondStep = Integer.MAX_VALUE;
            if(ind-2>=0) {
                secondStep = prev2 + Math.abs(height[ind]-height[ind-2]);
            }
            int curr = Math.min(firstStep, secondStep);
            prev2 = prev;
            prev = curr;
        }
        return prev;
    }
    
    int minCost(int[] height) {
        String approach = TABULAR_OPTIMIZED;
        
        if(approach.equals(MEMOIZATION)) {
            int[] dp = new int[height.length];
            for(int i=0;i<dp.length;i++) {
                dp[i] = -1;
            }
            return solveMemoization(height.length-1,height,dp);
        }
        if(approach.equals(TABULAR)) {
            int[] dp = new int[height.length];
            for(int i=0;i<dp.length;i++) {
                dp[i] = -1;
            }
            return solveTabular(dp,height);
        }
        if(approach.equals(TABULAR_OPTIMIZED))
            return solveTabularSpaceOpt(height);
        return 0;
    }
}
