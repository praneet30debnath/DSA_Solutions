//https://www.youtube.com/watch?v=C-2_uSRli8o&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=9
//https://leetcode.com/problems/flood-fill/description/

class Solution {
    private static final int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{sr,sc});
        boolean[][] visited = new boolean[image.length][image[0].length];
        visited[sr][sc] = true;
        int temp = image[sr][sc];

        while(!q.isEmpty()) {
            int curr[] = q.poll();
            int currRow = curr[0];
            int currCol = curr[1];

            image[currRow][currCol] = color;

            for(int[] dir : directions) {
                int nRow = currRow + dir[0];
                int nCol = currCol + dir[1];

                if (nRow >= 0 && nCol >= 0 && nRow < image.length && nCol < image[0].length && image[nRow][nCol] == temp && !visited[nRow][nCol]) {
                    visited[nRow][nCol] = true;
                    q.add(new int[]{nRow, nCol});
                }
            }
        }
        return image;
    }
}
