//https://www.naukri.com/code360/problems/cycle-detection-in-undirected-graph_1062670

import java.util.LinkedList;
import java.util.List;
import java.util.*;
import java.util.Queue;

public class Solution {
    private static final String YES = "Yes";
    private static final String NO = "No";
    private static final int NO_PARENT = -1;

    public static String cycleDetection(int[][] edges, int n, int m) {
        // Step 1: Build adjacency list
        Map<Integer, List<Integer>> adj = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            adj.put(i, new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int u = edges[i][0];
            int v = edges[i][1];

            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        // Step 2: Initialize visited array
        boolean[] visited = new boolean[n + 1];

        // Step 3: Perform BFS for each component
        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                if (bfsDetectCycle(i, adj, visited)) {
                    return YES;
                }
            }
        }

        return NO;
    }

    private static boolean bfsDetectCycle(int start, Map<Integer, List<Integer>> adj, boolean[] visited) {
        Queue<Integer> q = new LinkedList<>();
        Map<Integer, Integer> parent = new HashMap<>();

        q.add(start);
        visited[start] = true;
        parent.put(start, NO_PARENT);

        while (!q.isEmpty()) {
            int node = q.poll();

            for (int neighbor : adj.get(node)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    parent.put(neighbor, node);
                    q.add(neighbor);
                } else if (parent.get(node) != neighbor) {
                    // Cycle detected
                    return true;
                }
            }
        }

        return false;
    }
    
}
