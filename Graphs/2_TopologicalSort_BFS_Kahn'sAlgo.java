//https://www.naukri.com/code360/problems/topological-sort_982938?leftPanelTabValue=PROBLEM

import java.util.* ;
import java.io.*; 
public class Solution 
{
    public static List<List<Integer>> prepareAdjList(int v, int e, ArrayList<ArrayList<Integer>> edges) {
        List<List<Integer>> adjList = new ArrayList<>();

        for (int i = 0; i < v; i++) {
            adjList.add(new ArrayList<>());
        }

        for(int i=0;i<e;i++) {
            int u1 = edges.get(i).get(0);
            int v1 = edges.get(i).get(1);

            adjList.get(u1).add(v1);
        }

        return adjList;
    }

    public static int[] prepareIndegree(List<List<Integer>> adjList) {
        int[] indegree = new int[adjList.size()];
        for(List<Integer> neighbours : adjList) {
            for(int neighbour : neighbours) {
                indegree[neighbour]++;
            }
        }
        return indegree;
    }

    public static ArrayList<Integer> topologicalSort(ArrayList<ArrayList<Integer>> edges, int v, int e) 
    {
        Queue<Integer> q = new LinkedList<>();
        List<List<Integer>> adjList = prepareAdjList(v,e,edges);

        int[] indegree = prepareIndegree(adjList);

        for (int i = 0; i < v; i++) {
            if (indegree[i] == 0) { 
                q.add(i);
            }
        }

        ArrayList<Integer> ans = new ArrayList<>();

        while(!q.isEmpty()) {
            int node = q.poll();

            ans.add(node);

            for(int neighbour : adjList.get(node)) {
                indegree[neighbour]--;
                if(indegree[neighbour]==0) {
                    q.add(neighbour);
                }
            }
        }

        return ans;
    }
}
