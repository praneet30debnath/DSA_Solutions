//https://leetcode.com/problems/diameter-of-binary-tree/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public int dfsFast(TreeNode node, int[] ans) {
        if(node == null)
            return 0;
        
        int lh = dfsFast(node.left, ans);
        int rh = dfsFast(node.right, ans);

        ans[0] = Math.max(ans[0], lh+rh);

        return 1 + Math.max(lh,rh);
    }

    public int diameterOfBinaryTree(TreeNode root) {
        int[] ans = new int[1];
        dfsFast(root, ans);
        return ans[0];
    }
}
