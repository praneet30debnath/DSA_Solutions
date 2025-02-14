//https://www.naukri.com/code360/problems/minimum-spanning-tree_631769?leftPanelTabValue=PROBLEM
//https://www.youtube.com/watch?v=KxLtIrCyXwE&list=PLDzeHZWIZsTobi35C3I-tKB3tRDX6YxuA&index=13

import java.util.*;
public class Solution {
    public static int findParent(int[] parent, int node) {
        if(parent[node]==node) {
            return node;
        }
        return parent[node] = findParent(parent, parent[node]);
    }

    public static void unionSet(int[] parent, int[] rank, int u, int v) {
        u = findParent(parent, u);
        v = findParent(parent, v);

        if(rank[u]<rank[v]) {
            parent[u]=v;
        } else if(rank[u]>rank[v]) {
            parent[v]=u;
        } else {
            parent[v]=u;
            rank[u]++;
        }
    }

	public static int minimumSpanningTree(ArrayList<ArrayList<Integer>> edges, int n) {
		//sort edges based on weight (edges[2])
        edges.sort((a, b) -> a.get(2).compareTo(b.get(2)));

        //declare and initialize parent and rank
        int[] parent = new int[n];
        int[] rank = new int[n];

        for(int i=0;i<parent.length;i++) {
            parent[i] = i;
        }
        Arrays.fill(rank,0);

        int minWeight = 0;

        for(int i=0;i<edges.size();i++) {
            int u = edges.get(i).get(0);
            int v = edges.get(i).get(1);
            int wt = edges.get(i).get(2);

            int rootU = findParent(parent, u);
            int rootV = findParent(parent, v);

            if (rootU != rootV) {
                minWeight += wt;
                unionSet(parent, rank, rootU, rootV);
            }

        return minWeight;
	}
}
