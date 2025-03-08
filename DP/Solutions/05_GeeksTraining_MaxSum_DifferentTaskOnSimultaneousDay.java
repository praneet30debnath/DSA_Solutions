//https://www.geeksforgeeks.org/problems/geeks-training/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=geeks-training
//https://www.youtube.com/watch?v=AE39gJYuRog

class Solution {
    private int f(int day, int last, int[][] arr, int[][] dp) {
        if(day == 0) {
            int maxi = 0;
            for(int task=0;task<arr[0].length;task++) {
                if(task != last) {
                    maxi = Math.max(maxi,arr[0][task]);
                }
            }
            return maxi;
        }
        
        if(dp[day][last] != -1) return dp[day][last];
        
        int maxi = 0;
        
        for(int task=0;task<arr[day].length;task++) {
            if(task != last) {
                int point = arr[day][task] + f(day-1,task,arr,dp);
                maxi = Math.max(maxi,point);
            }
        }
        
        return dp[day][last] = maxi;
    }
    
    public int maximumPoints(int arr[][]) {
        int n = arr.length;
        int m = arr[0].length;
        int[][] dp = new int[n][m+1];
        for(int i=0;i<n;i++) {
            Arrays.fill(dp[i],-1);
        }
        return f(n-1,3,arr,dp);
    }
}
