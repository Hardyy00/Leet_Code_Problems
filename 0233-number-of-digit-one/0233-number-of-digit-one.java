class Solution {
    int[][] dp = new int[12][2];
    int[][] countDp =new int[12][2];
    public int countDigitOne(int n) {
        for(int[] row : dp){
            Arrays.fill(row, -1);
        }

        for(int[] row : countDp){
            Arrays.fill(row, -1);
        }

        String r=  Integer.toString(n);

        return solve(r, r.length(), 1);
        
    }

    private int solve(String limit, int n, int tight){

        if(n==0){
            return 0;
        }

        if(dp[n][tight] !=-1){
            return dp[n][tight];
        }

        int total = 0;

        int upperBound = tight == 1 ? (limit.charAt(limit.length()-n)-'0') : 9;

        for(int dig=0;dig<=upperBound;dig++){

            int val = 0;
            if(dig==1){
                val = count(limit, n-1, tight == 0 ? 0 : (dig==upperBound ? 1 : 0));
            }

            total += val + solve(limit, n-1, (tight == 0 ? 0 : (dig==upperBound ? 1 : 0)));
        }

        return dp[n][tight] =  total;
    }

    private int count(String limit, int n, int tight){

        if(countDp[n][tight] !=-1){
            return countDp[n][tight];
        }

        if(tight ==0){
            return (int)(Math.pow(10,n) + 0.1);
        }

        if(n==0){
            return 1;
        }



        int upperBound = limit.charAt(limit.length()-n)-'0';

        int total = 0;

        for(int dig=0;dig<=upperBound;dig++){

            total += count(limit,n-1, dig==upperBound ? 1 : 0);
        }

        return countDp[n][tight] = total;
    }
}