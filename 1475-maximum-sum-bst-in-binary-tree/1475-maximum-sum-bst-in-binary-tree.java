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

class NodeInfo{

    int maxValue = Integer.MIN_VALUE;
    int minValue = Integer.MAX_VALUE;

    int sum = 0;
    boolean isValidBST = true;

}
class Solution {
    public int maxSumBST(TreeNode root) {
        
        int[] maxSum = {0};

        findMaxSumBST(root,maxSum);
        return maxSum[0];
    }

    private NodeInfo findMaxSumBST(TreeNode root, int[] maxSum){

        // For a BST , root node must be greater than max value of left side and minimum value of right side

        // TC : O(N)
        // SC : O(logN)

        if(root == null){

            return new NodeInfo();
        }

        NodeInfo leftSideInfo = findMaxSumBST(root.left, maxSum);

        NodeInfo rightSideInfo = findMaxSumBST(root.right, maxSum);

        NodeInfo currentInfo = new NodeInfo();

        if(leftSideInfo.maxValue < root.val && root.val < rightSideInfo.minValue && leftSideInfo.isValidBST && rightSideInfo.isValidBST){

            currentInfo.sum = leftSideInfo.sum + rightSideInfo.sum + root.val;

            maxSum[0] = Math.max(currentInfo.sum, maxSum[0]);

            currentInfo.maxValue = Math.max(root.val, Math.max(leftSideInfo.maxValue,rightSideInfo.maxValue));

            currentInfo.minValue = Math.min(root.val,Math.min(leftSideInfo.minValue,rightSideInfo.minValue));

            return currentInfo;

        }

        currentInfo.isValidBST = false;

        return currentInfo;
    }
}