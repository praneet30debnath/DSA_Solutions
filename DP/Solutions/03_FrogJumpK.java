//https://www.geeksforgeeks.org/problems/minimal-cost/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=minimal-cost
//https://takeuforward.org/data-structure/dynamic-programming-frog-jump-with-k-distances-dp-4/

class Solution {
    public int minimizeCost(int k, int arr[]) {
        int n = arr.length;
        int[] dp = new int[n];
        dp[0] = 0;
        
        for (int i = 1; i < n; i++) {
            int minValue = Integer.MAX_VALUE;
            
            for (int j = 1; j <= k; j++) {
                if (i - j >= 0) {
                    int jumpCost = Math.abs(arr[i] - arr[i - j]) + dp[i - j];
                    minValue = Math.min(minValue, jumpCost);
                }
            }
            dp[i] = minValue;
        }
        return dp[n - 1];
    }
}
