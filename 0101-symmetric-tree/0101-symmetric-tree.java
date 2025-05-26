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
    public boolean isSymmetric(TreeNode root) {
        
        Queue<TreeNode> queue = new LinkedList<>();

        queue.offer(root.left);
        queue.offer(root.right);

        while(!queue.isEmpty()){

            TreeNode leftTreeNode = queue.poll();
            TreeNode rightTreeNode = queue.poll();

            if(leftTreeNode == null && rightTreeNode == null) {
                continue;
            }

            if(leftTreeNode == null || rightTreeNode == null) {
                return false;
            }

            if(leftTreeNode.val != rightTreeNode.val){

                return false;
            }

            queue.offer(leftTreeNode.left);
            queue.offer(rightTreeNode.right);

            queue.offer(leftTreeNode.right);
            queue.offer(rightTreeNode.left);


        }

        return true;
    }
}