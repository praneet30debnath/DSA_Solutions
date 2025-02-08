//https://www.naukri.com/code360/problems/shortest-path-in-an-unweighted-graph_981297?leftPanelTabValue=PROBLEM

import java.util.*;
public class Solution {
	public static Map<Integer,List<Integer>> prepareAdj(int[][] edges, int n, int m) {
		Map<Integer,List<Integer>> adjList = new HashMap<>();

		for(int i=1;i<=n;i++) {
			adjList.put(i,new ArrayList<>());
		}

		for(int i=0;i<m;i++) {
			int u = edges[i][0];
			int v = edges[i][1];

			adjList.get(u).add(v);
			adjList.get(v).add(u);
		}

		return adjList;
	}

	public static LinkedList<Integer> shortestPath(int[][] edges, int n, int m, int s, int t) {
		Map<Integer,List<Integer>> adjList = prepareAdj(edges,n,m);

		Queue<Integer> q = new LinkedList<>();
		boolean[] visited = new boolean[n+1];
		int[] parent = new int[n+1];

		parent[s] = -1;
		q.add(s);
		visited[s] = true;

		while(!q.isEmpty()) {
			int node = q.poll();

			for(int neighbour : adjList.get(node)) {
				if(!visited[neighbour]) {
					visited[neighbour] = true;
					parent[neighbour] = node;
					q.add(neighbour);
				}
			}
		}

		LinkedList<Integer> result = new LinkedList<Integer>();

		int ans = t;

		while(ans != -1) {
			result.add(ans);
			ans = parent[ans];
		}

		Collections.reverse(result);

		return result;
	}

}
