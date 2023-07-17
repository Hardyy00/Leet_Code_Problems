class Solution {
public:
    int maximalSquare(vector<vector<char>>& matrix) {
        
        int m = matrix.size();
        int n = matrix[0].size();

        return solve2(m,n, matrix);
        
    }

    int solve2(int m, int n, vector<vector<char>> &mat){

        // Space Optimisation
        // TC : O(M*N)
        // SC : O(N)

        vector<int> pre(n, 0);

        int maxi = 0;

        for(int i=0;i<n;i++){ 
            pre[i] = mat[0][i]-'0';
            maxi = max(maxi , pre[i]);
        }
        

        for(int i=1;i<m;i++){
            vector<int> curr(n, 0);
            curr[0] = mat[i][0]-'0';
            maxi = max(maxi, curr[0]);

            for(int j=1;j<n;j++){

                if(mat[i][j]=='0') continue;

                curr[j] = 1 + min(pre[j-1] , min(curr[j-1] , pre[j]));

                maxi = max(maxi, curr[j]);
            }

            pre = curr;
        }

        return maxi * maxi;
    }


    int solve1(int m, int n, vector<vector<int>> &mat){

        vector<vector<int>> dp(m, vector<int>(n, 0));

        int maxi = 0;

        for(int i=0;i<n;i++){ 
            dp[0][i] = mat[0][i]-'0';
            maxi = max(maxi , dp[0][i]);
        }

        for(int i=1;i<m;i++){ 
            dp[i][0] = mat[i][0]-'0';
            maxi = max(maxi , dp[i][0]);
        }

        

        for(int i=1;i<m;i++){

            for(int j=1;j<n;j++){

                if(mat[i][j]=='0') continue;

                dp[i][j] = 1 + min(dp[i-1][j-1] , min(dp[i][j-1] , dp[i-1][j]));

                maxi = max(maxi, dp[i][j]);
            }
        }

        // for(auto &arr : dp){

        //     for(auto &i :arr) cout <<i << " ";

        //     cout << endl;
        // }
        return maxi * maxi;
    }
};