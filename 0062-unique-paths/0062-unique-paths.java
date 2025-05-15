class Solution {
    public int uniquePaths(int m, int n) {
        
        // return uniquePathRecursion(m-1,n-1);


        // int[][] dp = new int[m][n];

        // for(int[] row  : dp) Arrays.fill(row,-1);

        // return uniquePathMemo(m-1,n-1,dp);

        // return uniquePathTab(m,n);

        return uniquePathSpaceOpt(m,n);
    }

    private int uniquePathSpaceOpt(int m, int n){

        // Space optimisation

        // TC : O(M*N)
        // SC : O(N)

        int[] pre = new int[n];

        for(int i=0;i<m;i++){

            int[] curr = new int[n];
            for(int j=0;j<n;j++){

                if(i==0 && j==0) curr[0] = 1;  // set the base case
                else{

                    int left = 0;
                    int up = 0;

                    if(j-1>=0) left = curr[j-1];

                    if(i-1>=0) up = pre[j];


                    curr[j] = left + up;
                }
            }

            pre = curr;
        }

        return pre[n-1];
    }

    private int uniquePathTab(int m, int n){

        // Tabulation

        // TC : O(N*M)
        // SC : O(N*M)

        int[][] dp= new int[m][n];

        dp[0][0] = 1;

        for(int i=0;i<m;i++){


            for(int j=0;j<n;j++){

                if(i==0 && j==0) continue;  // base case is already set so skip it

                int left = 0;
                int up =0;

                if(j-1>=0) left = dp[i][j-1];
                
                if(i-1>=0) up = dp[i-1][j];

                dp[i][j] = left + up;
            }
        }

        return dp[m-1][n-1];


    }

    private int uniquePathMemo(int r, int c, int[][] dp){
        
        // Memoization

        // TC : O(N*M)
        // SC : O(N*M) + O(N+M)

        if(r==0 && c==0) return 1;

        if(r < 0 || c < 0) return 0;

        if(dp[r][c]!=-1) return dp[r][c];

        int left = uniquePathMemo(r,c-1, dp);
        int up = uniquePathMemo(r-1,c,dp);

        return dp[r][c] = left + up;
    }



    private int uniquePathRecursion(int r, int c){

        // using recursion

        // TC : O(exponential) or O(2^(m*n))
        // sc : O(M+N) (explicit stack space)

        if(r==0 && c==0) return 1;

        if(r < 0 || c < 0) return 0;

        int left = uniquePathRecursion(r,c-1);  //  move to top
        int up = uniquePathRecursion(r-1,c);    // move to bottom

        return left + up;
    }
}