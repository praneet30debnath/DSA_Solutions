//https://leetcode.com/problems/number-of-islands/
//https://www.youtube.com/watch?v=muncqlKJrH0

class Solution {
    private static final int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    public void bfs(int row, int col, char[][] grid, boolean[][] visited){
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{row, col});
        visited[row][col] = true;

        while(!q.isEmpty()) {
            int[] curr = q.poll();
            int currRow = curr[0];
            int currCol = curr[1];

            for (int[] dir : directions) {
                int nRow = currRow + dir[0];
                int nCol = currCol + dir[1];

                if (nRow >= 0 && nCol >= 0 && nRow < grid.length && nCol < grid[0].length 
                    && grid[nRow][nCol] == '1' && !visited[nRow][nCol]) {
                    visited[nRow][nCol] = true;
                    q.add(new int[]{nRow, nCol});
                }
            }
        }
    }

    public int numIslands(char[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int count = 0;
        for(int i=0;i<grid.length;i++) {
            for(int j=0;j<grid[0].length;j++) {
                if(grid[i][j] == '1' && !visited[i][j]) {
                    bfs(i,j,grid,visited);
                    count++;
                }
            }
        }
        return count;
    }
}
