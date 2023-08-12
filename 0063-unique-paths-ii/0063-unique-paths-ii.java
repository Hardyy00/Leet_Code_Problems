class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {

        int m = obstacleGrid.length , n = obstacleGrid[0].length;

        // int[][] dp = new int[m][n];

        // return moveOnGrid1(m-1,n-1,obstacleGrid,dp);

        // return moveOnGrid2(m,n,obstacleGrid);

        return moveOnGrid3(m,n,obstacleGrid);
    }

    int moveOnGrid3(int m, int n, int[][] mat){

        // Space optimised
        // TC : O(M*N) && SC : O(N)

        if(mat[0][0]==1 || mat[m-1][n-1]==1) return 0;

        int[] pre =new int[n];
        pre[0] = 1;
        

        for(int i=0;i<m;i++){
            int[] help = new int[n];
            for(int j=0;j<n;j++){

                if(mat[i][j]==1) continue;
                else{
                    int up = pre[j];
                    int left = j>0 ? help[j-1] : 0;

                    help[j]  = up + left;
                }
            }

            int[] swap = help;
            help = pre;
            pre = swap;
        } 

        return pre[n-1];
    }

    int moveOnGrid2(int m, int n,int[][] mat){

        // Tabulation
        // TC : O(M*N) && SC : O(M*N)

        if(mat[0][0]==1) return 0;

        int[][] dp = new int[m][n];

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){

                if(i==0 && j==0) dp[0][0] = 1;
                else if(mat[i][j]==1) continue;     // the obstacle is not included in the path
                else{

                    int up=0 , left = 0;

                    if(i>0) up = dp[i-1][j];
                    if(j>0) left = dp[i][j-1];

                    dp[i][j] = up + left;
                }
            }
        }

        return dp[m-1][n-1];
    }

    int moveOnGrid1(int m, int n, int[][] mat, int[][] dp){

        // Memoization 
        // TC : O(M*N) && SC : O(M-1 + N-1) + O(M*N) (max path recursion stack + dp matrix)
        if(m<0 || n<0 || mat[m][n]==1) return 0;
        if(dp[m][n]!=0) return dp[m][n];
        if(m==0 && n==0) return 1;

        int left = moveOnGrid1(m,n-1,mat,dp);
        int up = moveOnGrid1(m-1,n,mat,dp);

        return dp[m][n] = left + up;
    }

}