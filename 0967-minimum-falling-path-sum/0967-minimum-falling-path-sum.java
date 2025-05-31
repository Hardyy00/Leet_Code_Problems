class Solution {
    int maxValue = (int)1e9;
    public int minFallingPathSum(int[][] matrix) {

        // int[][] dp = new int[matrix.length][matrix.length];

        // for(int[] row : dp) Arrays.fill(row,Integer.MAX_VALUE);

        // return minimumPathSum1(0,0,matrix,dp);

        return minimumPathSum2(matrix);

    }
    
    int minimumPathSum3(int[][] mat){

        // Space Optimised
        // TC : O(N*N) & SC : O(N)

        int n = mat.length;
        int[] pre = new int[n];
        int min = Integer.MAX_VALUE;

        for(int i=n-1;i>=0;i--){

            int[] help = new int[n];
            for(int j=0;j<n;j++){

                if(i==n-1) help[j] = mat[i][j];
                else{

                    int left = maxValue;
                    if(j>0) left = pre[j-1] + mat[i][j];

                    int bottom = pre[j] + mat[i][j];

                    int right = maxValue;
                    if(j<n-1) right = pre[j+1] + mat[i][j];

                    int minPathSum = Math.min(left,Math.min(bottom,right));

                    help[j] = minPathSum;
                }

                if(i==0) min = Math.min(min,help[j]);
            }

            pre = help;
        }

        return min;
    }


    int minimumPathSum2(int[][] mat){

        // Tabulation
        // TC : O(N*N) && SC : O(N*N)

        int n = mat.length;
        int[][] dp = new int[n][n];
        int min = Integer.MAX_VALUE;

        for(int i=n-1;i>=0;i--){

            for(int j=0;j<n;j++){

                if(i==n-1) dp[i][j] = mat[i][j];
                else{

                    int left = maxValue;
                    if(j>0) left = dp[i+1][j-1] + mat[i][j];

                    int bottom = dp[i+1][j] + mat[i][j];

                    int right = maxValue;   
                    if(j<n-1) right = dp[i+1][j+1] + mat[i][j];

                    int minPathSum = Math.min(left,Math.min(bottom, right));

                    dp[i][j] = minPathSum;
                }

                if(i==0) min = Math.min(min,dp[i][j]);
            }
        }

        return min;
    }

    int minimumPathSum1(int r, int c, int[][] mat, int[][] dp){

        // Memoization
        // TC : O(N*N) & SC : O(N) + O(N*N) (recusion stack till matrix depth + dp matrix) 

        if(c<0 || c>=mat.length) return maxValue;
        if(dp[r][c]!=Integer.MAX_VALUE) return dp[r][c];
        if(r==mat.length-1) return mat[r][c];


        if(r==0){
            int min = Integer.MAX_VALUE;

            // calculating minimum path from every cell in 1st row and taking their minimum
            for(int i=0;i<mat.length;i++){

                int left = minimumPathSum1(r+1,i-1,mat,dp) + mat[r][i];
                int bottom = minimumPathSum1(r+1,i,mat,dp) + mat[r][i];
                int right = minimumPathSum1(r+1,i+1,mat,dp) + mat[r][i];

                int minPathSum = Math.min(left, Math.min(bottom,right));

                dp[r][i] = minPathSum;

                min = Math.min(min,minPathSum);
            }

            return min;
        }

        // each cell has 3 choices
        int left = minimumPathSum1(r+1,c-1,mat,dp) + mat[r][c];
        int bottom = minimumPathSum1(r+1,c,mat,dp) + mat[r][c];
        int right = minimumPathSum1(r+1,c+1,mat,dp) + mat[r][c];

        return dp[r][c] = Math.min(left, Math.min(bottom,right));

        
    }
}