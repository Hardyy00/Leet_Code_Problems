class Solution {
    public int uniquePaths(int m, int n) {

        // int[][] dp = new int[m][n];
        // return moveOnGrid1(m-1,n-1,dp);

        // return moveOnGrid2(m,n);

        return moveOnGrid3(m,n);        
    }

    int moveOnGrid3(int m, int n){

        // Space Optimization
        // TC : O(M* N) && SC : O(N)

        // only maintaining the row containing the previous answers to use it to calculate the upward direction paths

        int[] pre = new int[n];
        int[] help = new int[n];
        pre[0] = 1;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){

                int up = pre[j];    // paths from upward direction
                int left = j>0 ? help[j-1] : 0; // paths from left direction
                help[j] = up + left;    // store the total paths from current cell

            }

            // preserve the current answer as the previous answer
            int[] swap = help;
            help = pre;
            pre = swap;
        }

        return pre[n-1];
    }

    int moveOnGrid2(int m, int n){

        // Tabulation 
        // TC : O(M*N) && SC : O(M*N)

        int[][] dp= new int[m][n];
        dp[0][0] = 1;

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){

                if(!(i==0 && j==0)){
                    int left = 0;
                    int up = 0;

                    if(j-1>=0) left+= dp[i][j-1];
                    if(i-1>=0) up += dp[i-1][j];

                    dp[i][j] = left + up;
                }
            }
        }


        return dp[m-1][n-1];
    }


    int moveOnGrid1(int m, int n,int[][] dp){
        
        // Memoization
        // TC : O(M*N) && SC : O(M-1 + N-1) (path length due to recusion stack) + O(M*N)

        if(m<0 || n < 0) return 0;
        if(dp[m][n]!=0) return dp[m][n];
        if(m==0 && n==0) return 1;

        int left = moveOnGrid1(m,n-1,dp);
        int up = moveOnGrid1(m-1,n,dp);

        return dp[m][n] = left + up;
    }
}