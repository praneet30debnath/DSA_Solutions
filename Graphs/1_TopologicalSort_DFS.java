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

    private static void prepareStack(int ele, Stack<Integer> stack, List<List<Integer>> adjList, boolean[] visited) {
        visited[ele] = true;

        for(int neighbour : adjList.get(ele)) {
            if(!visited[neighbour]) {
                visited[neighbour] = true;
                prepareStack(neighbour, stack, adjList, visited);
            }
        }
        
        stack.add(ele);
        return;
    }


    public static ArrayList<Integer> topologicalSort(ArrayList<ArrayList<Integer>> edges, int v, int e) 
    {
        ArrayList<Integer> result = new ArrayList<>();
        List<List<Integer>> adjList = prepareAdjList(v,e,edges);
        
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[v];

        for(int i=0;i<v;i++) {
            if(!visited[i]) {
                prepareStack(i, stack, adjList, visited);
            }
        }
        
        while(!stack.isEmpty()) {
            result.add(stack.pop());
        }

        return result;
    }
}
