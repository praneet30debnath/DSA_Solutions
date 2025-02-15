//https://www.youtube.com/watch?v=ACzkVtewUYA&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=7
//https://leetcode.com/problems/number-of-provinces/

class Solution {
    public void bfs(int node, List<List<Integer>> adjList, boolean[] visited) {
        visited[node] = true;
        Queue<Integer> q = new LinkedList<>();

        q.add(node);

        while(!q.isEmpty()) {
            int curr = q.poll();
            for(int neighbour : adjList.get(curr)) {
                if(!visited[neighbour]) {
                    visited[neighbour] = true;
                    q.add(neighbour);
                }
            }
        }
    }
    
    public int findCircleNum(int[][] isConnected) {
        List<List<Integer>> adjList = new ArrayList<>();
        for(int i=0;i<isConnected.length;i++) {
            adjList.add(new ArrayList<>());
        }
        for(int i=0;i<isConnected.length;i++) {
            for(int j=0;j<isConnected[0].length;j++) {
                if(i!=j && isConnected[i][j]==1) {
                    adjList.get(i).add(j);
                }
            }
        }
        
        int count = 0;
        
        boolean[] visited = new boolean[isConnected.length];

        for(int i=0;i<isConnected.length;i++) {
            if(!visited[i]) {
                count++;
                bfs(i, adjList, visited);
            }
        }

        return count;
    }
}
