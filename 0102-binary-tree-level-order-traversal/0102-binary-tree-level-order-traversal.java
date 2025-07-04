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
    public List<List<Integer>> levelOrder(TreeNode root) {
        

        List<List<Integer>> answer = new ArrayList<>();

        if(root==null) return answer;

        Queue<TreeNode> queue = new LinkedList<>();

        queue.offer(root);

        while(!queue.isEmpty()){

            List<Integer> list =new ArrayList<>();

            for(int i=queue.size();i>0;i--){

                TreeNode node = queue.poll();

                list.add(node.val);

                if(node.left != null) queue.offer(node.left);

                if(node.right != null) queue.offer(node.right);
            }

            answer.add(list);
        }

        return answer;
    }
}