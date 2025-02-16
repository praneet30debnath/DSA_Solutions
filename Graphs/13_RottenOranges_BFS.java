//https://www.youtube.com/watch?v=yf3oUhkvqA0&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=10
//https://leetcode.com/problems/rotting-oranges/

class Solution {
    private static final int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int orangesRotting(int[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];

        int timer = 0;

        Queue<int[]> q = new LinkedList<>();
            
        for(int i=0;i<grid.length;i++) {
            for(int j=0;j<grid[0].length;j++) {
                if(grid[i][j]==2) {
                    q.add(new int[]{i,j,timer});
                    visited[i][j] = true;
                }
            }
        }

        int time = 0;

        while(!q.isEmpty()) {
            int curr[] = q.poll();
            int row = curr[0];
            int col = curr[1];
            time = curr[2];

            for(int[] dir : directions) {
                int nRow = row + dir[0];
                int nCol = col + dir[1];

                if(nRow>=0 && nCol>=0 && nRow<grid.length && nCol<grid[0].length && grid[nRow][nCol]==1 && !visited[nRow][nCol]) {
                    visited[nRow][nCol] = true;
                    q.add(new int[]{nRow, nCol, time+1});
                    grid[nRow][nCol] = 2;
                }
            }
        }

        for(int i=0;i<grid.length;i++) {
            for(int j=0;j<grid[0].length;j++) {
                if(grid[i][j]==1) {
                    return -1;
                }
            }
        }

        return time;
    }
}
