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
    public List<Integer> inorderTraversal(TreeNode root) {

        // use stack ,if curr is not null, put it not stack, go to left
        // if curr is null, pop from stack, add in list, and then move right

        // if curr == null && stack is empty then break

        // TC : O(N)
        // SC : O(N)

        Deque<TreeNode> stack = new ArrayDeque<>();
        List<Integer> list = new ArrayList<>();

        TreeNode curr = root;
        while(!stack.isEmpty() || curr != null){

            if(curr!=null){
                stack.push(curr);
                curr = curr.left;
            }else{

                if(!stack.isEmpty()){
                    TreeNode temp = stack.pop();
                    list.add(temp.val);
                    curr = temp.right;
                }
            }
        }

        return list;
        
    }
}