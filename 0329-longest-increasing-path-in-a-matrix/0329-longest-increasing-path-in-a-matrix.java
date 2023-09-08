class Solution {
    public int longestIncreasingPath(int[][] matrix) {

        int n = matrix.length;
        int m = matrix[0].length;

        int[][] dp = new int[n][m];

        for(int[] row : dp) Arrays.fill(row , -1);

        int[] ra = {-1, 0, 1, 0};
        int[] ca= {0, 1, 0, -1};

        int maxLen = 0;

        for(int i=0;i<n;i++){

            for(int j=0;j<m;j++){

                if(dp[i][j] ==-1) {

                    int len = solve(i,j, matrix,dp, ra, ca);

                    maxLen = Math.max(maxLen, len);
                }
            }
        }

        return maxLen ;
        
    }

    private int solve(int r, int c, int[][] mat, int[][] dp, int[] ra, int[] ca){

        if(dp[r][c] !=-1) return dp[r][c];

        int maxLen = 0;

        for(int i = 0;i<4;i++){

            int nr = r + ra[i];
            int nc = c + ca[i];

            if(nr >=0 && nr < mat.length && nc >=0 && nc< mat[0].length && mat[nr][nc]> mat[r][c]){

                int len = solve(nr, nc, mat, dp, ra, ca);

                maxLen = Math.max(maxLen, len);

            }
        }

        return dp[r][c] = 1 + maxLen;
    }
}

