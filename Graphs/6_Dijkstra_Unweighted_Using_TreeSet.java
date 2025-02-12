//https://www.naukri.com/code360/problems/dijkstra-s-shortest-path_920469?leftPanelTabValue=PROBLEM

/*
Why 
TreeSet<int[]> set = new TreeSet<>((a, b) -> a[0] == b[0] ? Integer.compare(a[1], b[1]) : Integer.compare(a[0], b[0]));
instead of 
TreeSet<int[]> set = new TreeSet<>(Comparator.comparingInt(a -> a[0]));

1. In Dijkstra's, multiple nodes can have the same shortest distance, but we still need to process each node separately.
2. This comparator prevents overwriting nodes with the same distance, preserving correctness.
*/

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
        TreeSet<int[]> set = new TreeSet<>((a, b) -> a[0] == b[0] ? Integer.compare(a[1], b[1]) : Integer.compare(a[0], b[0]));
        set.add(new int[]{0, source}); // {distance, node}

        while (!set.isEmpty()) {
            int[] curr = set.pollFirst(); // Remove node with the smallest distance
            int node = curr[1], d = curr[0];

            for (int[] neighbor : adjList.get(node)) {
                int nextNode = neighbor[0], weight = neighbor[1];

                if (distance[nextNode] > d + weight) {
                    set.remove(new int[]{distance[nextNode], nextNode}); // Remove old value
                    distance[nextNode] = d + weight;
                    set.add(new int[]{distance[nextNode], nextNode}); // Insert new value
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
