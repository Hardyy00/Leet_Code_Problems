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
    public int kthSmallest(TreeNode root, int k) {
        
        int[] index = {0};

        return findkthSmallestInBST(root,k,index);
    }

    private int findkthSmallestInBST(TreeNode root, int k,int[] index){

        if(root==null){

            return -1;
        }

        int leftSideValue =  findkthSmallestInBST(root.left,k,index);

        if(leftSideValue !=-1){

            return leftSideValue;
        }

        index[0]++;

        if(index[0]==k){
            return root.val;
        }

        int rightSideValue = findkthSmallestInBST(root.right,k,index);

        if(rightSideValue !=-1){

            return rightSideValue;
        }

        return -1;
    }
}