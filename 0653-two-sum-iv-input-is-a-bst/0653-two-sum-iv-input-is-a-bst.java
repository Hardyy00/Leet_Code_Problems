class BSTIterator{

    TreeNode root;
    Deque<TreeNode> stack;
    boolean isReverse;

    public BSTIterator(TreeNode root,boolean isReverse){
        this.root = root;
        this.isReverse = isReverse;
        this.stack = new ArrayDeque<>();

        fillStack(root);
    }

    public int next(){

        TreeNode removedElement = stack.pop();

        if(isReverse){
            fillStack(removedElement.left);
        }else{
            fillStack(removedElement.right);
        }

        return removedElement.val;
    }

    public int peek(){
        
        return stack.peek().val;
    }

    private void fillStack(TreeNode root){

        TreeNode iterator = root;

        while(iterator != null){

            stack.push(iterator);

            if(isReverse){
                iterator = iterator.right;
            }else{

                iterator = iterator.left;
            }
        }
    }


}
class Solution {
    public boolean findTarget(TreeNode root, int k) {
        
        BSTIterator iterator1 = new BSTIterator(root,false);

        BSTIterator iterator2 = new BSTIterator(root,true);

        while(true){

            int leftSideElement = iterator1.peek();
            int rightSideElement = iterator2.peek();

            if(leftSideElement >= rightSideElement){

                return false;
            }
            int sum = leftSideElement + rightSideElement;


            if(sum > k){
                iterator2.next();
            }else if(sum < k){

                iterator1.next();
            }else{

                return true;
            }
        }

       
    }
}