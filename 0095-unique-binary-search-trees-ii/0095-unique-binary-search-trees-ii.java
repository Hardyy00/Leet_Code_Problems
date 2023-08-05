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
    public List<TreeNode> generateTrees(int n) {

        List<TreeNode>[][] dp = new List[n+1][n+1];

        return solve(1,n,dp);
        
    }

    private List<TreeNode> solve(int i, int j, List<TreeNode>[][] dp){

        if(i==j){

            List<TreeNode> temp = new ArrayList<>();

            temp.add(new TreeNode(i));

            return dp[i][j] =  temp;
        }

        if(i>j){

            List<TreeNode> temp = new ArrayList<>();

            temp.add(null);

            return temp;
        }

        if(dp[i][j] != null) return dp[i][j];

        List<TreeNode> ans = new ArrayList<>();

        for(int k=i;k<=j;k++){

            List<TreeNode> left = solve(i,k-1,dp);

            List<TreeNode> right = solve(k+1,j,dp);

            for(TreeNode leftNode : left){

                for(TreeNode rightNode : right){

                    TreeNode root  = new TreeNode(k);
                    root.left = leftNode;
                    root.right = rightNode;

                    ans.add(root);
                }
            }

        }

        return dp[i][j] = ans;
    }
}