//https://www.naukri.com/code360/problems/detect-cycle-in-a-directed-graph_1062626

import java.util.*;
public class Solution {
  public static Map<Integer,List<Integer>> prepareAdjList(int n, ArrayList < ArrayList < Integer >> edges) {
      Map<Integer,List<Integer>> adjList = new HashMap<>();
      for(int i=1;i<=n;i++) {
        adjList.put(i,new ArrayList<>());
      }

      for(int i=0;i<edges.size();i++) {
        int u = edges.get(i).get(0);
        int v = edges.get(i).get(1);
        adjList.get(u).add(v);
      }

      return adjList;
  }

  private static boolean isCyclic(int node, int parent, boolean[] visited,
            boolean[] dfsVisited, Map<Integer,List<Integer>> adjList) {
    visited[node] = true;
    dfsVisited[node] = true;

    for(int neighbour : adjList.get(node)) {
      if(!visited[neighbour]) {
        boolean ans = isCyclic(neighbour, node, visited, dfsVisited, adjList);
        if(ans) {
          return true;
        }
      } else if(dfsVisited[neighbour]){
        return true;
      }
    }
    dfsVisited[node] = false;
    return false;
  }

  public static boolean detectCycleInDirectedGraph(int n, ArrayList < ArrayList < Integer >> edges) {
    // create adjList in form of map
    Map<Integer,List<Integer>> adjList = prepareAdjList(n,edges);

    boolean[] visited = new boolean[n+1];
    boolean[] dfsVisited = new boolean[n+1];

    for(int i=1;i<=n;i++) {
      if(!visited[i]) {
        boolean ans = isCyclic(i,-1,visited,dfsVisited,adjList);
        if(ans) {
          return true;
        }
      }
    }
    return false;
  }
}
