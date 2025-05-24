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
    public List<Integer> postorderTraversal(TreeNode root) {

        // Always move to left, uponn finding null, go to stack peek's right
        // if it is still null, pop the stack, add in list, and pop & add
        // all the element whose right is curr element
        
        Deque<TreeNode> stack =new ArrayDeque<>();
        List<Integer> list = new ArrayList<>();

        TreeNode curr= root;

        while(!stack.isEmpty() || curr!=null){

            if(curr!=null){
                stack.push(curr);
                curr =curr.left;
            }else {

                curr = stack.isEmpty() ? null : stack.peek().right;

                if(curr==null && !stack.isEmpty()){

                    TreeNode temp = stack.pop();
                    list.add(temp.val);


                    while(!stack.isEmpty() && stack.peek().right == temp){
                        
                        temp = stack.poll();
                        list.add(temp.val);
                    }

                }
            }
        }

        return list;
    }
}