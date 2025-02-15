//https://takeuforward.org/data-structure/g-35-print-shortest-path-dijkstras-algorithm/
//https://www.geeksforgeeks.org/problems/shortest-path-in-weighted-undirected-graph/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=shortest-path-in-weighted-undirected-graph

class Solution {
    public List<List<int[]>> prepareAdjList(int n, int m, int edges[][]) {
        List<List<int[]>> adjList = new ArrayList<>();
        
        for(int i = 0; i <= n; i++) {
            adjList.add(new ArrayList<>());
        }
        
        for(int i = 0; i < m; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            int weight = edges[i][2];
            
            adjList.get(u).add(new int[]{v, weight});
            adjList.get(v).add(new int[]{u, weight});
        }
        
        return adjList;
    }
    
    public List<Integer> shortestPath(int n, int m, int edges[][]) {
        List<List<int[]>> adjList = prepareAdjList(n, m, edges);
        
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        
        int[] distance = new int[n+1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        int[] parent = new int[n+1];
        for(int i = 0; i <= n; i++) {
            parent[i] = i;
        }
        
        distance[1] = 0;
        pq.add(new int[]{0, 1});
        
        while(!pq.isEmpty()) {
            int[] curr = pq.poll();
            int currNode = curr[1];
            int currDistance = curr[0];
            
            if (currDistance > distance[currNode]) continue; // Skip outdated distances
            
            for(int[] neighbours : adjList.get(currNode)) {
                int nextNode = neighbours[0];
                int nextWeight = neighbours[1];
                
                if(distance[nextNode] > currDistance + nextWeight) {
                    distance[nextNode] = currDistance + nextWeight;
                    pq.add(new int[]{distance[nextNode], nextNode});
                    parent[nextNode] = currNode;
                }
            }
        }
        
        // Check if target is reachable
        if (distance[n] == Integer.MAX_VALUE) {
            return List.of(-1);
        }
        
        // Reconstruct path from 1 to n
        List<Integer> path = new ArrayList<>();
        int target = n;
        while(target != 1) {
            path.add(target);
            target = parent[target];
        }
        path.add(1);
        Collections.reverse(path);
        
        // Prepare result with path weight as first element
        List<Integer> result = new ArrayList<>();
        result.add(distance[n]);
        result.addAll(path);
        
        return result;
    }
}
