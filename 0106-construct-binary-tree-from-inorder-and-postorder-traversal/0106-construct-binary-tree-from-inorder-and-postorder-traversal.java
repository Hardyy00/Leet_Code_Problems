class Solution {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        
        if(inorder.length == 0) {
            return null;
        }

        int[] index = {postorder.length-1};

        Map<Integer,Integer> map =new HashMap<>();

        for(int i=0;i<inorder.length;i++){

            map.put(inorder[i],i);
        }

        return constructTreeFromInorderAndPostorder(0,inorder.length-1,index,postorder,map);
    }

    private TreeNode constructTreeFromInorderAndPostorder(int start, int end, int[] index, int[] postorder,Map<Integer,Integer> map){


        if(start > end){

            return null;
        }

        int rootElement = postorder[index[0]--];
        TreeNode newRoot = new TreeNode(rootElement);

        int rootElementIndex = map.get(rootElement);

        newRoot.right = constructTreeFromInorderAndPostorder(rootElementIndex+1,end,index,postorder,map);

        newRoot.left = constructTreeFromInorderAndPostorder(start,rootElementIndex-1,index,postorder,map);

        return newRoot;


    }


}