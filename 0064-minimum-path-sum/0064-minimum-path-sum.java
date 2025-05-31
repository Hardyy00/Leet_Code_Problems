class Solution {
    int maxValue = (int)1e9;
    public int minPathSum(int[][] grid) {
    
        // int[][] dp = new int[grid.length][grid[0].length];

        // for(int[] row : dp) Arrays.fill(row,-1);

        // return minimumPathSum1(grid.length-1,grid[0].length-1,grid,dp);  

        // return minimumPathSum2(grid.length,grid[0].length,grid);

        return minimumPathSum3(grid.length,grid[0].length,grid);
        
    }

    int minimumPathSum3(int m, int n, int[][] mat){

        int[] pre = new int[n];
        Arrays.fill(pre,Integer.MAX_VALUE);
        
        int[] help = new int[n];

        for(int i=0;i<m;i++){

            for(int j=0;j<n;j++){

                if(i==0 && j==0){
                    help[0] = mat[0][0];
                }else{

                    int up =  pre[j];
                
                    if(up!=Integer.MAX_VALUE) up += mat[i][j];

                    int left = j>0 ? help[j-1] + mat[i][j] : Integer.MAX_VALUE;

                    help[j] = Math.min(up,left);
                }
            }

            // System.out.println(Arrays.toString(help));

            int[] swap = help;
            help = pre;
            pre = swap;
        }

        return pre[n-1];
    }

    int minimumPathSum2(int m, int n, int[][] mat){

        // Tabulation
        // TC : O(M*N) && SC : O(M*N)

        int[][] dp = new int[m][n];

        for(int i=0;i<m;i++){

            for(int j=0;j<n;j++){

                if(i==0 && j==0) dp[0][0] = mat[0][0];
                else{

                    int up = Integer.MAX_VALUE;
                    int left = Integer.MAX_VALUE;

                    if(i>0) up = dp[i-1][j] + mat[i][j];
                    if(j>0) left = dp[i][j-1] + mat[i][j];

                    dp[i][j] = Math.min(up,left);
                }
            }
        }

        return dp[m-1][n-1];

    }

    int minimumPathSum1(int m, int n, int[][] grid, int[][] dp){

        /// Memoization
        // TC : O(N*M) && SC : O(M-1 + N-1) + O(N*M) (recursion for mximum path length + dp matrix)

        if(m<0 || n<0) return maxValue;
        if(dp[m][n]!=-1) return dp[m][n];
        if(m==0 && n==0) return grid[0][0];

        int left = minimumPathSum1(m,n-1,grid,dp) + grid[m][n];
        int up = minimumPathSum1(m-1,n,grid,dp) + grid[m][n];

        return dp[m][n] = Math.min(left,up);
    }
}