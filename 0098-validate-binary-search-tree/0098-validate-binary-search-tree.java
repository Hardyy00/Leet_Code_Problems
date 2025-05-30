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
    public boolean isValidBST(TreeNode root) {
        
        return checkForValidBST(Long.MIN_VALUE,Long.MAX_VALUE,root);
    }

    private boolean checkForValidBST(long minValue,long maxValue,TreeNode root){

        if(root==null){

            return true;
        }

        if(root.val <= minValue || root.val >= maxValue){

            return false;
        }


        boolean leftSideIsOk = checkForValidBST(minValue,root.val,root.left);

        if(!leftSideIsOk) {

            return false;
        }

        boolean rightSideIsOk = checkForValidBST(root.val, maxValue,root.right);

        if(!rightSideIsOk){

            return false;
        }


        return true;
    }
}