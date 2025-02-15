//https://www.naukri.com/code360/problems/shortest-path-in-dag_8381897?leftPanelTabValue=PROBLEM

/*
1. Create Adjaceny List, but include weights as well. Hence List<List<Map<Integer,Integer>>> instead of List<List<Integer>>
2. Make use of DFS to create Topology to get ordering for each node
3. Initialize a distance array with infinity (Integer.MAX_VALUE)
4. Make distance[source] = 0
5. Pop elements one by one and store in node variable
6.  if result[node] is not infinity
      then find neighbours and weight of each of those neighbour
      if neighbour != 0
        then resultWeight = weight[node] + weight
             if resultWeight < weight[neighbour]
               then weight[neighbour] = resultWeight
7. Replace all unreachable nodes with -1
*/

import java.util.*;
public class Solution {
    public static List<List<Map<Integer,Integer>>> prepareAdjList(int n, int m, int [][]edges) {
        List<List<Map<Integer,Integer>>> adjList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            List<Map<Integer, Integer>> temp = new ArrayList<>();
            adjList.add(temp);
        }

        for(int i=0;i<m;i++) {
            int u1 = edges[i][0];
            int v1 = edges[i][1];
            int weight = edges[i][2];

            Map<Integer, Integer> temp = new HashMap<>();
            temp.put(v1,weight);

            adjList.get(u1).add(temp);
        }

        return adjList;
    }

    private static void dfs(int node, Stack<Integer> stack, boolean[] visited, List<List<Map<Integer,Integer>>> adjList) {
        visited[node] = true;

        for(Map<Integer, Integer> map : adjList.get(node)) {
            int neighbour = new ArrayList<>(map.keySet()).get(0);
            if(!visited[neighbour]) {
                dfs(neighbour, stack, visited, adjList);
            }
        }

        stack.push(node);
    }

    private static int[] shortestDistanceForEachNode(int n, List<List<Map<Integer,Integer>>> adjList, Stack<Integer> stack) {
        int[] result = new int[n];
        Arrays.fill(result, Integer.MAX_VALUE);

        result[0] = 0;
        while(!stack.isEmpty()) {
            int node = stack.pop();
            if(result[node]!=Integer.MAX_VALUE) {
                for(Map<Integer, Integer> map : adjList.get(node)) {
                    int neighbour = new ArrayList<>(map.keySet()).get(0);
                    int weight = map.get(neighbour);

                    if(neighbour!=0) {
                        int resultantWeight = result[node] + weight;
                        if(resultantWeight<result[neighbour]) {
                            result[neighbour] = resultantWeight;
                        }
                    }
                }
            }
        }

        return result;
    }

    public static int[] shortestPathInDAG(int n, int m, int [][]edges) {
        List<List<Map<Integer,Integer>>> adjList = prepareAdjList(n,m,edges);
        boolean[] visited = new boolean[n];

        Stack<Integer> stack = new Stack<>();

        for(int i=0;i<n;i++) {
            if(!visited[i]) {
                dfs(i,stack,visited,adjList);
            }
        }

        int[] result = shortestDistanceForEachNode(n, adjList, stack);

        //change unreachable nodes distance to -1
        for(int i=0;i<result.length;i++) {
            if(result[i]==Integer.MAX_VALUE) {
                result[i]=-1;
            }
        }

        return result;
    }
}
