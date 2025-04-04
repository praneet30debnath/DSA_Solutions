//https://www.geeksforgeeks.org/problems/geek-jump/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=geek-jump
//https://takeuforward.org/data-structure/dynamic-programming-frog-jump-dp-3/

class Solution {
    private static final String MEMOIZATION = "MEMOIZATION";
    private static final String TABULAR = "TABULAR";
    
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
    
    int minCost(int[] height) {
        String approach = MEMOIZATION;
        
        int[] dp = new int[height.length];
        for(int i=0;i<dp.length;i++) {
            dp[i] = -1;
        }
        
        if(approach.equals(MEMOIZATION))
            return solveMemoization(height.length-1,height,dp);
        if(approach.equals(TABULAR))
            return solveTabular(dp,height);
        return 0;
    }
}
