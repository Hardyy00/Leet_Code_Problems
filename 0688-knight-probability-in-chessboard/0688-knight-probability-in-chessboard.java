class Solution {
    int ra[] = {-2,-1,-2,-1,1,2,2,1};
    int ca[] = {-1,-2,1,2,2,1,-1,-2};
    public double knightProbability(int n, int k, int row, int column) {

        
        double[][][] dp = new double[n][n][k+1];

        for(double[][] mat : dp) for(double[] a :mat) Arrays.fill(a,-1.0);

        return solve(row,column,k,n,dp);
    }

    double solve(int r,int c, int k,int n, double[][][] dp){

        // Memoization
        // TC : O(N*N*K)
        // SC : O(N*N*K) + O(N+N)

        if(r<0 || c<0 || r>=n || c>=n) return 0;

        // k==0
        if(k==0) return 1.0; 

        if(dp[r][c][k]!=-1) return dp[r][c][k];

        double prob = 0;

        for(int i=0;i<8;i++){

            int nr = r + ra[i];
            int nc = c + ca[i];

            prob += solve(nr,nc,k-1,n,dp)/8.0;

        }


        return dp[r][c][k] = prob;

    }
}