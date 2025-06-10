class Solution {
    public int countSquares(int[][] mat) {
        
        // for every (i,j), calculating how many squares can have their right bottom end at (i,j)
        //  suppose the matrix 1 1  , where 1 occurr in i-1, i-1 & i-1, j & i, j-1, we can make 
        //                     1 1    2*2 square and a 1*1 square (cuz (i,j) is 1) , but even if any
        // element is 1 we cannot make a 2*2, hence we take a minimum of them and add 1
        
        // we can make a 3*3 , when all three indexes are 2, even if 1 index is 1 , then we cannot make
        // a 3*3 matrix
        
        // Tabulation
        // TC : O(M*N)
        // SC : O(M*N)
        
        
        int m = mat.length;
        int n = mat[0].length;
        
        int[][] dp = new int[m][n];
        
        int sum = 0;
        
        // 1st column and 1 st row remains same
        for(int i=0;i<n;i++){
            
            dp[0][i] = mat[0][i];
            
            sum += dp[0][i];    // add the sum on the way
        }
        
        // since 0,0 has been included once not including it twice
        for(int i=1;i<m;i++){
            
            dp[i][0] = mat[i][0];
            
            sum += dp[i][0];
        }
        
        for(int i=1;i<m;i++){
            
            for(int j=1;j<n;j++){
                
                if(mat[i][j]!=0){
                    
                    dp[i][j] = 1 + Math.min(dp[i-1][j-1], Math.min(dp[i][j-1], dp[i-1][j]));
                    
                    sum += dp[i][j];    
                }
            }
        }
        
        return sum;
    }
}