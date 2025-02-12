//https://www.naukri.com/code360/problems/dijkstra-s-shortest-path_920469?leftPanelTabValue=PROBLEM

import java.util.* ;
import java.io.*; 
public class Solution {
	// Function to prepare adjacency list
    public static List<List<int[]>> prepareAdjList(int vertices, int edges, ArrayList<ArrayList<Integer>> vec) {
        List<List<int[]>> adjList = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            adjList.add(new ArrayList<>());
        }
        for (ArrayList<Integer> edge : vec) {
            int u = edge.get(0);
            int v = edge.get(1);
            int weight = edge.get(2);

            adjList.get(u).add(new int[]{v, weight});
            adjList.get(v).add(new int[]{u, weight}); // Undirected graph
        }
        return adjList;
    }

    // Dijkstra's Algorithm using TreeSet
    public static ArrayList<Integer> dijkstra(ArrayList<ArrayList<Integer>> vec, int vertices, int edges, int source) {
        List<List<int[]>> adjList = prepareAdjList(vertices, edges, vec);
        int[] distance = new int[vertices];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[source] = 0;

        // Min-Heap (TreeSet) to store {distance, node}
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.add(new int[]{0, source});

        while(!pq.isEmpty()) {
            int[] curr = pq.poll();
            int currNode = curr[1];
            int currDist = curr[0];

            for(int[] neighbor : adjList.get(currNode)) {
                int nextNode = neighbor[0];
                int weight = neighbor[1];

                if(distance[nextNode] > currDist + weight) {
                    distance[nextNode] = currDist + weight;
                    pq.add(new int[]{distance[nextNode],nextNode});
                }
            }
        }

        // Convert distance array to ArrayList
        ArrayList<Integer> result = new ArrayList<>();
        for (int dist : distance) {
            result.add(dist == Integer.MAX_VALUE ? Integer.MAX_VALUE : dist); // Convert unreachable nodes to -1
        }
        return result;
    }
}
