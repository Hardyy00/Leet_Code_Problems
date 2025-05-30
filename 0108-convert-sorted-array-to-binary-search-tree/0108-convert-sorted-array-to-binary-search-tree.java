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
    public TreeNode sortedArrayToBST(int[] nums) {
        

        return makeBSTOutOfSortedArray(0,nums.length-1,nums);
    }

    private TreeNode makeBSTOutOfSortedArray(int start, int end, int[] nums){

        if(start >end){

            return null;
        }

        int middleIndex = (start + end +1)/2;

        int middleElement = nums[middleIndex];

        TreeNode newRoot = new TreeNode(middleElement);


        newRoot.left = makeBSTOutOfSortedArray(start,middleIndex-1,nums);

        newRoot.right = makeBSTOutOfSortedArray(middleIndex+1,end,nums);

        return newRoot;

    }
}