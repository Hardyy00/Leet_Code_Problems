class Solution {
    int[][] dp = new int[12][2];
    int[][] countDp =new int[12][2];
    public int countDigitOne(int n) {

        // using digit dp
        // if i am putting 1 at a position , then count of number have 1, as their right digit
        // will be count(n-1)

        // hence dp(n,tight) = (dig==1 && count(n-1, tight)) + dp(n-1, (0 or 1))

       // TC : O(logN)
       // SC : O(logN)   

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

        // if tight ==0, then we can use 10 digits for each position , hence for n positions 10 ^9
        if(tight ==0){
            return (int)(Math.pow(10,n) + 0.1);
        }

        if(n==0){
            return 1;
        }

        // if we must follow the tight constraint
        int upperBound = limit.charAt(limit.length()-n)-'0';

        int total = 0;

        for(int dig=0;dig<=upperBound;dig++){
            
            // send tight ==1 , if dig==upperBound
            total += count(limit,n-1, dig==upperBound ? 1 : 0);
        }

        return countDp[n][tight] = total;
    }
}