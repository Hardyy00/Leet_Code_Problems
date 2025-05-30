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
    public TreeNode bstFromPreorder(int[] preorder) {
        
        int[] index ={0};

        return makeBSTFromPreorder(Integer.MIN_VALUE,Integer.MAX_VALUE,index,preorder);
    }

    private TreeNode makeBSTFromPreorder(int minValue,int maxValue, int[] index, int[] preorder){


        if(index[0] == preorder.length){
            return null;
        }

        int rootElement = preorder[index[0]];

        if(rootElement>minValue && rootElement < maxValue){
            
            index[0]++;
            TreeNode newRoot = new TreeNode(rootElement);

            newRoot.left = makeBSTFromPreorder(minValue,rootElement,index,preorder);

            newRoot.right = makeBSTFromPreorder(rootElement,maxValue,index,preorder);

            return newRoot;

        }else{

            return null;
        }
    }
}