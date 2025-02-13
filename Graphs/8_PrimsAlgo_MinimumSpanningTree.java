//Minimum Spanning Tree: A tree/graph from a weighted undirected graph where all nodes are reachable from any node in minimum cost.
//https://www.naukri.com/code360/problems/prim-s-mst_1095633?leftPanelTabValue=PROBLEM

//NOTE : Solution is in O(n²). Can be optimized till O(nlogn) using PriorityQueue.

import java.util.*;
import java.io.*; 

public class Solution 
{
    public static List<List<int[]>> prepareAdj(int n, int m, ArrayList<ArrayList<Integer>> g) {
        List<List<int[]>> adjList = new ArrayList<>();

        for(int i=0;i<n;i++) {
            adjList.add(new ArrayList<>());
        }

        for(int i=0;i<m;i++) {
            int u = g.get(i).get(0)-1;
            int v = g.get(i).get(1)-1;
            int weight = g.get(i).get(2);

            adjList.get(u).add(new int[]{v, weight});
            adjList.get(v).add(new int[]{u, weight});
        }
        return adjList;
    }

    public static int getMinimumKey(int[] key, boolean[] visited) {
        int min = Integer.MAX_VALUE;
        int node = -1;
        for(int i=0;i<key.length;i++) {
            if(!visited[i] && key[i]<min) {
                node = i;
                min = key[i];
            }
        }
        return node;
    }

    public static boolean containsFalse(boolean[] arr) {
        for (boolean value : arr) {
            if (!value) {
                return true;
            }
        }
        return false;
    }

    public static ArrayList<ArrayList<Integer>> calculatePrimsMST(int n, int m, ArrayList<ArrayList<Integer>> g)
    {
        List<List<int[]>> adjList = prepareAdj(n,m,g);

        int[] key = new int[n];
        boolean[] mst = new boolean[n];
        int[] parent = new int[n];

        Arrays.fill(key, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);

        key[0] = 0;
        parent[0] = -1;

        while(containsFalse(mst)) {
            int currNode = getMinimumKey(key,mst);
            mst[currNode] = true;
            for(int[] neighbours : adjList.get(currNode)) {
                int nextNode = neighbours[0];
                int nextWeight = neighbours[1];

                if(!mst[nextNode] && nextWeight < key[nextNode]) {
                    key[nextNode] = nextWeight;
                    parent[nextNode] = currNode;
                }
            }
        }

        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        for(int i=0;i<parent.length;i++) {
            if(parent[i] == -1)
                continue;
            else {
                ArrayList<Integer> temp = new ArrayList<>();
                temp.add(parent[i] + 1);
                temp.add(i+1);
                temp.add(key[i]);
                result.add(temp);
            }
        }
        return result;
    }
}
