class Solution {

    int mod = (int)1e9 + 7;
    public int numTilings(int n) {


        long[][] dp = new long[n][2];

        for(long[] row : dp){
            Arrays.fill(row, -1);
        }        

        return (int)memo(0,0,n,dp);
    }

    private long memo(int index,int gap, int n, long[][] dp){

        if(index>=n){

            if(index==n && gap==0){
                return 1;
            }

            return 0;
        }

        if(dp[index][gap] !=-1){
            return dp[index][gap];
        }

        if(gap==0){

            return dp[index][gap] = (memo(index+1, 0,n, dp) % mod + memo(index+2,0,n ,dp) % mod  + 2*memo(index+1, 1,n,dp) ) % mod;
        }else{


            return dp[index][gap] = (  memo(index+2 ,0,n,dp) % mod  + memo(index+1, 1,n,dp) % mod )  % mod;
        }
    }
}