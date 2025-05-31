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
class BSTIterator {

    TreeNode root;
    Deque<TreeNode> stack;

    public BSTIterator(TreeNode root) {
        
        this.root = root;
        this.stack = new ArrayDeque<>();

        fillStack(root);
    }
    
    public int next() {
        
        TreeNode removedElement = stack.pop();

        if(removedElement.right != null){

            fillStack(removedElement.right);
        }

        return removedElement.val;
    }

    private void fillStack(TreeNode root){

        TreeNode iterator = root;

        while(iterator != null){

            stack.push(iterator);
            iterator = iterator.left;
        }
    }
    
    public boolean hasNext() {
        return !stack.isEmpty();
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */