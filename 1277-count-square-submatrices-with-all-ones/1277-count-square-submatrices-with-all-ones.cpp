class Solution {
public:
    int countSquares(vector<vector<int>>& mat) {
        
        int m = mat.size();
        int n = mat[0].size();
        
        vector<vector<int>> dp(m , vector<int>(n, 0));
        
        int sum = 0;
        
        for(int i=0;i<n;i++){ 
            
            dp[0][i] = mat[0][i];
            sum += dp[0][i];
        }   
        
        for(int i=0;i<m;i++){
            
            dp[i][0] = mat[i][0];
            
            sum += dp[i][0];
        }
        
        
        for(int i=1;i<m;i++){
            
            for(int j=1;j<n;j++){
                
                if(mat[i][j]!=0){
                    
                    dp[i][j] = 1 + min(dp[i-1][j-1] , min(dp[i][j-1],dp[i-1][j]));
                    
                    sum += dp[i][j];
                }
            }
        }
        
        return mat[0][0]==1 ? sum-1 : sum;
        
    }
};