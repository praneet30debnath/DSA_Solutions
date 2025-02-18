//https://www.naukri.com/code360/problems/time-to-burn-tree_630563?ieSlug=cars24-interview-experience-aug-2021-exp-0-2-years-1796&ieCompany=cars24&leftPanelTabValue=PROBLEM

/**********************************************************	
	
	Following is the representation of Binary Tree Node:
 	
 	class BinaryTreeNode<T> {
		T data;
		BinaryTreeNode<T> left;
		BinaryTreeNode<T> right;
		
		public BinaryTreeNode(T data) {
			this.data = data;
		}
	}

*********************************************************/
import java.util.*;
public class Solution {
	// Build Graph - Adjacency List Representation
    public static void buildGraph(BinaryTreeNode<Integer> node, Map<BinaryTreeNode<Integer>, BinaryTreeNode<Integer>> parentMap, Map<Integer, List<Integer>> adjList, BinaryTreeNode<Integer> parent) {
        if (node == null) {
            return;
        }

        // If parent exists, link parent and current node in adjacency list
        if (parent != null) {
            adjList.putIfAbsent(node.data, new ArrayList<>());
            adjList.putIfAbsent(parent.data, new ArrayList<>());

            adjList.get(node.data).add(parent.data);
            adjList.get(parent.data).add(node.data);
        }

        // Store parent reference
        parentMap.put(node, parent);

        // Recursively build graph for left and right children
        buildGraph(node.left, parentMap, adjList, node);
        buildGraph(node.right, parentMap, adjList, node);
    }

    public static int timeToBurnTree(BinaryTreeNode<Integer> root, int start) {
        // Step 1: Build Graph using adjacency list
        Map<BinaryTreeNode<Integer>, BinaryTreeNode<Integer>> parentMap = new HashMap<>();
        Map<Integer, List<Integer>> adjList = new HashMap<>();

        buildGraph(root, parentMap, adjList, null);
        
        // Step 2: BFS to burn the tree
        if (adjList.size() == 0) {
            return 0;
        }

        Queue<Integer> q = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();

        q.add(start);
        visited.add(start);
        int time = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            boolean flag = false;

            for (int i = 0; i < size; i++) {
                int curr = q.poll();

                // Check if the current node has any adjacent nodes
                    for (int neighbor : adjList.get(curr)) {
                        if (!visited.contains(neighbor)) {
                            visited.add(neighbor);
                            q.add(neighbor);
                            flag = true;
                        }
                    }
                
            }

            // If at least one node burns at this level, increment time
            if (flag) time++;
        }

        return time;
    }
}
