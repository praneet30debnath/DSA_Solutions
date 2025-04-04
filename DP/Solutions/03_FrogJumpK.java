//https://www.geeksforgeeks.org/problems/minimal-cost/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=minimal-cost
//https://takeuforward.org/data-structure/dynamic-programming-frog-jump-with-k-distances-dp-4/

class Solution {
    public int solveMemoization(int ind, int[] dp,int[] height, int k) {
        if(ind == 0) return 0;
        if(dp[ind]!=-1) return dp[ind];
        
        int minEnergy = Integer.MAX_VALUE;
        
        for(int i=1;i<=k;i++) {
            if(ind-i>=0) {
                int kEnergy = solveMemoization(ind-i,dp,height,k) + Math.abs(height[ind] - height[ind-i]);
                minEnergy = Math.min(minEnergy, kEnergy);
            }
        }
        return dp[ind] = minEnergy;
    }
    
    public int solveTabular(int[] dp, int[] arr, int k, int n) {
        dp[0] = 0;
        
        for (int i = 1; i < n; i++) {
            int minValue = Integer.MAX_VALUE;
            
            for (int j = 1; j <= k; j++) {
                if (i-j >= 0) {
                    int jumpCost = Math.abs(arr[i] - arr[i - j]) + dp[i - j];
                    minValue = Math.min(minValue, jumpCost);
                }
            }
            dp[i] = minValue;
        }
        return dp[n - 1];
    }
    
    public int minimizeCost(int k, int arr[]) {
        int n = arr.length;
        int[] dp = new int[n];
        for(int i=0;i<dp.length;i++) {
            dp[i]=-1;
        }
        
        return solveMemoization(arr.length-1,dp,arr,k);
    }
}
