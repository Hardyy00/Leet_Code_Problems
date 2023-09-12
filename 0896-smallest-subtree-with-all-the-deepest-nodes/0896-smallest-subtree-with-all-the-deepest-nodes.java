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
    public TreeNode subtreeWithAllDeepest(TreeNode root) {

        int maxHeight = findHeight(root);

        return lca(root, maxHeight-1);
        
    }

    private TreeNode lca(TreeNode root, int height){

        if(root == null) return null;
        if(height == 0) return root;


        TreeNode left = lca(root.left, height-1);

        TreeNode right = lca(root.right, height -1);

        if(left!=null && right !=null) return root;

        if(left !=null) return left;

        return right;
    }

    private int findHeight(TreeNode root){

        if(root == null) return 0;

        return 1 + Math.max( findHeight(root.left) , findHeight(root.right));
    }
}