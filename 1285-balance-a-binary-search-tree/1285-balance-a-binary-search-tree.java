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
    List<TreeNode> ls = new ArrayList<>();

    public TreeNode balanceBST(TreeNode root) {
        // get all the node of the tree in a list (deattach them)
        // now use divide and conquer to create a tree out of them

        // TC : O(N)
        // SC : O(N)


        inorder(root);
        return createTree(0, ls.size()-1); 
    }

    private TreeNode createTree(int low, int high){

        if(low>high){
            return null;
        }
        if(low==high){
            return ls.get(low);
        }

        int mid = (low + high) /2;
        TreeNode root = ls.get(mid);

        root.left = createTree(low, mid-1);
        root.right = createTree(mid+1, high);


        return root;


    }

    private void inorder(TreeNode root){

        if(root==null){
            return;
        }

        TreeNode left = root.left , right = root.right;
        root.left = root.right = null;

        inorder(left);
        ls.add(root);
        inorder(right);
    }
}