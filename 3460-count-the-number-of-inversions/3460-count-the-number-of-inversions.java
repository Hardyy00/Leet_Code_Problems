class Solution {

    long MOD = (int)1e9 + 7;
    long[][] dp;

    public int numberOfPermutations(int n, int[][] requirements) {

        dp = new long[n][(n * (n-1))/2 + 1];

        for(long[] row : dp){

            Arrays.fill(row, -1);
        }

        Arrays.sort(requirements, (a,b)-> a[0] - b[0]);

        for(int[] row : requirements){

            if(memo(row[0], row[1]) == 0){
                return 0;
            }

            for(int i=0;i<dp[0].length;i++){

                if(i != row[1]){
                    dp[row[0]][i] = 0;
                }
            }
        }

        int len = requirements.length;

        int[] lastRow = requirements[len-1];

        return (int) dp[lastRow[0]][lastRow[1]];
        

    }

    private long memo(int pos, int inv){


        if(inv < 0){
            return 0;
        }

        if(pos==0){
            return inv == 0 ? 1 : 0;
        }

        if(dp[pos][inv] !=-1){
            return dp[pos][inv];
        }

        long res = 0;

        for(int i=0;i<=pos;i++){

            res = (res + memo(pos-1, inv - (pos-i)))  % MOD;
        }

        return dp[pos][inv] = res;
    }
}