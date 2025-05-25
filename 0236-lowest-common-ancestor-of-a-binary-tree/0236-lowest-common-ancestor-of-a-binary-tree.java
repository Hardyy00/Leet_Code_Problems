/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        return getLowestCommonAncestor(root,p,q);
    }

    private TreeNode getLowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q){

        if(root==null) return null;

        if(root==q || root==p) return root;

        TreeNode leftNode = getLowestCommonAncestor(root.left, p,q);

        TreeNode rightNode = getLowestCommonAncestor(root.right,p,q);

        if(leftNode !=null && rightNode !=null) return root;

        if(leftNode !=null) return leftNode;

        return rightNode;

    }
}