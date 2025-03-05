//https://www.geeksforgeeks.org/problems/geek-jump/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=geek-jump
//https://takeuforward.org/data-structure/dynamic-programming-frog-jump-dp-3/

class Solution {
    static int solve(int ind,int[] height,int[] dp){
        if(ind==0) return 0;
        if(dp[ind]!=-1) return dp[ind];
        int jumpTwo = Integer.MAX_VALUE;
        int jumpOne= solve(ind-1, height,dp)+ Math.abs(height[ind]-height[ind-1]);
        if(ind>1)
            jumpTwo = solve(ind-2, height,dp)+ Math.abs(height[ind]-height[ind-2]);
        
        return dp[ind]=Math.min(jumpOne, jumpTwo);
    }
    
    int minCost(int[] height) {
        int[] dp = new int[height.length];
        for(int i=0;i<dp.length;i++) {
            dp[i] = -1;
        }
        return solve(height.length-1,height,dp);
    }
}
