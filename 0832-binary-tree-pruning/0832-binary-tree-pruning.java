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

    boolean delete = false;
    boolean oneEverFound = false;


}
class Solution {
    public TreeNode pruneTree(TreeNode root) {

        return dfs(root).delete ? null : root;
        
    }

    private Pair dfs(TreeNode node){

        if(node == null) return new Pair();


        Pair left = dfs(node.left);
        Pair right = dfs(node.right);

        if(left.delete) node.left = null;
        if(right.delete) node.right = null;

        Pair temp = new Pair();

        if(node.val == 1){
            temp.oneEverFound = true;
            return temp;
        }

        temp.oneEverFound = left.oneEverFound || right.oneEverFound;

        if(temp.oneEverFound) temp.oneEverFound = true;
        else temp.delete = true;

        return temp;
            
    }
}