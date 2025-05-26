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
    public int maxPathSum(TreeNode root) {

         int[] maxiSum = {Integer.MIN_VALUE};

        getMaxSum(root,maxiSum);

        return maxiSum[0];
        
    }

    private int getMaxSum(TreeNode root, int[] maxiSum){

        if(root==null) return 0;

        int leftMaximumSum = getMaxSum(root.left, maxiSum);

        if(leftMaximumSum < 0) leftMaximumSum = 0;
        
        int rightMaximumSum = getMaxSum(root.right,maxiSum);

        if(rightMaximumSum < 0) rightMaximumSum =0;

        maxiSum[0] = Math.max(maxiSum[0],root.val + leftMaximumSum + rightMaximumSum);

        return root.val +Math.max(leftMaximumSum, rightMaximumSum);


    }
}