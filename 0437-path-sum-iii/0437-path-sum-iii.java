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
    int count = 0;
    public int pathSum(TreeNode root, int targetSum) {

        if(root==null) return count;

        findPath(root,targetSum);
        pathSum(root.left,targetSum);
        pathSum(root.right,targetSum);

        return count;
        
    }
    void findPath(TreeNode root,long target){

        if(root==null) return;

        if(target-root.val==0) count++;

        findPath(root.left,target-root.val);
        findPath(root.right,target-root.val);
    }
}