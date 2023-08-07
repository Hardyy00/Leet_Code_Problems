/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode() : val(0), left(nullptr), right(nullptr) {}
 *     TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
 *     TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
 * };
 */
class Solution {
private:
    long long solve(double index, TreeNode* root, unordered_map<long long, long long> &dp){

        if(root==nullptr) return 0;
        
        if(root->left==nullptr && root->right==nullptr) return root->val;

        if(dp.count(index)) return dp[index];

        long long l = index*2+1;
        long long r = index*2+2;
        // not take value

        long long left = solve(l,root->left,dp);
        long long right = solve(r,root->right,dp);

        // take the node's value

        long long a= 0, b= 0 , c =  0, d=0;
        long long sum = root->val;

        if(root->left!=nullptr){

            long long ll = l*2+1;
            long long lr = l*2+2;

            a = solve(ll,root->left->left,dp);
            b = solve(lr, root->left->right,dp);
        }

        if(root->right!=nullptr){

            long long rl = r*2 +1;
            long long rr = r*2+2;

            c = solve(rl,root->right->left, dp);
            d = solve(rr,root->right->right,dp); 
        }

        sum += a+b+c+d;

        long long maxSum = max(sum , left+right);

        return dp[index] = (int)maxSum;
    }  
public:
    int rob(TreeNode* root) {
        
        unordered_map<long long, long long> dp;

        return solve(0,root,dp);
    }
};