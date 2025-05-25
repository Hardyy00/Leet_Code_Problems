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

    TreeNode node;
    int index;

    public Pair(TreeNode node, int index){

        this.node = node;
        this.index = index;
    }
} 
class Solution {
    public int widthOfBinaryTree(TreeNode root) {

        // Find the indexes of the nodes in binary tree , at each level, get the maximum and minimum
        // level , and take the difference b/w them as the width of current level

        // TC : O(n)
        // SC : O(n)
        
        Queue<Pair> queue = new LinkedList<>();

        queue.offer(new Pair(root,0));
        int width = Integer.MIN_VALUE; 

        while(!queue.isEmpty()){
            int miniIndex = -1;
            int maxIndex = -1;
            
            for(int i=queue.size();i>0;i--){

                Pair pairNode = queue.poll();

                if(miniIndex ==-1) miniIndex = pairNode.index;

                maxIndex = pairNode.index;

                if(pairNode.node.left !=null) queue.offer(new Pair(pairNode.node.left,2*pairNode.index+1));

                if(pairNode.node.right !=null) queue.offer(new Pair(pairNode.node.right, 2*pairNode.index+2));
            }

            int currWidth = maxIndex - miniIndex +1;

            width = Math.max(width , currWidth);
        }

        return width;

    }
}