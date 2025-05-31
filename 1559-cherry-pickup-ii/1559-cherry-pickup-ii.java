class Solution {

    int minValue = (int)-1e9;

    public int cherryPickup(int[][] grid) {

        int n= grid.length;
        int m = grid[0].length;

        int[][][] dp = new int[n][m][m];

        for(int [][] mat : dp){

            for(int[] row : mat) Arrays.fill(row,-1);
        }

        // memoization
        return cherryPick1(0,0,m-1,grid,dp);

        // tabulation
        // return cherryPick2(n,m,grid);

        // space optimized
        // return cherryPick3(n,m,grid);
        
    }

    int cherryPick3(int n, int m, int[][] mat){
        
        // Space optimised
        // TC : O(N*M*M)*9
        // SC : O(M*M)

        // for below row
        int[][] pre = new int[m][m];
        // for storing current answers
        int[][] help  = new int[m][m];

        // setting base cases
        for(int i=0;i<m;i++){

            for(int j=0;j<m;j++){

                if(i==j) pre[i][j] = mat[n-1][i];
                else pre[i][j] = mat[n-1][i] + mat[n-1][j];
            }
        }

        for(int r=n-2;r>=0;r--){

            for(int c1 = 0;c1<m;c1++){

                for(int c2 = 0;c2<m;c2++){

                    int max = 0;

                    for(int i=-1;i<=1;i++){

                        for(int j=-1;j<=1;j++){

                            int pathSum = Integer.MIN_VALUE;

                            if(c1+i>=0 && c1+i<m && c2+j>=0 && c2+j<m){

                                if(c1==c2) pathSum = pre[c1+i][c2+j] + mat[r][c1];
                                else pathSum = pre[c1+i][c2+j] + mat[r][c1] + mat[r][c2];
                            }

                            max = Math.max(max,pathSum);
                        }
                    }

                    help[c1][c2] = max;
                }
            }

            int[][] swap = help;
            help = pre;
            pre = swap;
        }


        return pre[0][m-1];
    }

    int cherryPick2(int n , int m, int[][] mat){

       // Tabulation
        // TC : O(N*M*M)*9
        // SC : O(N*M*M)

        int[][][] dp = new int[n][m][m];

        // Initializing the base cases
        for(int i=0;i<m;i++){
            for(int j=0;j<m;j++){

                if(i==j) dp[n-1][i][j] = mat[n-1][i];
                else dp[n-1][i][j] = mat[n-1][i] + mat[n-1][j];
            }
        }


        // for every state we have 9 ways
        // i - 0 to n-1
        // c1 can be 0 to m-1
        // c2 can be 0 to m-1

        for(int r=n-2;r>=0;r--){
            for(int c1=0;c1<m;c1++){
                for(int c2=0;c2<m;c2++){

                    int max = 0;
                    // taking the maximum out of all possible ways for each state
                    for(int i=-1;i<=1;i++){
                        for(int j=-1;j<=1;j++){

                            int pathSum = Integer.MIN_VALUE;
                            // only when the cell is in boundary we consider it , otherwise neglect it by making it minimum
                            if(c1+i>=0 && c1+i<m && c2+j>=0 && c2+j<m){
                                // both can be present at a same cell
                                if(c1==c2) pathSum = dp[r+1][c1+i][c2+j] + mat[r][c1];
                                else pathSum = dp[r+1][c1+i][c2+j] + mat[r][c1] + mat[r][c2];
                            }

                            max = Math.max(max,pathSum);
                        }
                    }

                    dp[r][c1][c2] = max;
                }
            }
        }

        return dp[0][0][m-1];
    }

    int cherryPick1(int r, int c1, int c2, int[][] mat, int[][][] dp){

        // Memoization
        // TC : O(N*M*M)*9 , there are n*m*m states in the dp and for each state for LOOP runs for 9 times
        // sc : o(N) + O(M*N*N)

        if(c1<0 || c1>=mat[0].length || c2<0 || c2>=mat[0].length) return minValue;

        if(dp[r][c1][c2]!=-1) return dp[r][c1][c2];

        if(r==mat.length-1){
            if(c1==c2) return mat[r][c1];

            return mat[r][c1] + mat[r][c2];
        }

        int max = 0;

        for(int i=-1;i<=1;i++){

            for(int j=-1;j<=1;j++){

                int pathSum = 0;

                if(c1==c2) pathSum = cherryPick1(r+1,c1+i,c2+j,mat,dp) + mat[r][c1];
                else  pathSum = cherryPick1(r+1,c1+i,c2+j,mat,dp) + mat[r][c1] + mat[r][c2];

                max = Math.max(max,pathSum);
            }
        }

        return dp[r][c1][c2] = max;
    }
}