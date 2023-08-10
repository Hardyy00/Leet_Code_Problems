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

class Pair{
    int dia=0;
    int ht = 0;
}
class Solution {
    public int diameterOfBinaryTree(TreeNode root) {
        
        return dfs(root).dia;
    }

    private Pair dfs(TreeNode root){

        if(root==null) return new Pair();

        Pair left = dfs(root.left);
        Pair right = dfs(root.right);

        Pair temp = new Pair();

        temp.dia = Math.max(Math.max(left.dia,right.dia),  left.ht + right.ht);

        temp.ht = 1 + Math.max(left.ht, right.ht);

        return temp;
    }
}