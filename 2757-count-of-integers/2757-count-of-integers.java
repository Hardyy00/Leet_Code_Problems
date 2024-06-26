class Solution {

    long[][][] dp = new long[25][220][2]; 
    long MOD = (long)1e9 + 7;
    long maxSum;
    long minSum;
    public int count(String num1, String num2, int min_sum, int max_sum) {
    
        maxSum = max_sum;
        minSum = min_sum;

        for(long[][] mat : dp){

            for(long[] row : mat){
                Arrays.fill(row, -1);
            }
        }

        long rSolve = solve(num2, num2.length(), 0, 1);

         for(long[][] mat : dp){

            for(long[] row : mat){
                Arrays.fill(row, -1);
            }
        }


        long lSolve = solve(num1,num1.length(), 0, 1);

        // System.out.println(rSolve +  " " +lSolve);

        long include = bruteForce(num1);

        long ans = (rSolve - lSolve + include + MOD) % MOD;


        return  (int)ans;
    }

    private long solve(String limit, int n, int sum, int tight){

        if(n==0){

            // System.out.println(sum);
            return sum>=minSum && sum<=maxSum ? 1 : 0;
        }

        if(dp[n][sum][tight] !=-1){
            return dp[n][sum][tight];
        }

        int upperBound = tight == 1 ? (limit.charAt(limit.length()-n) - '0') : 9;

        long tot = 0;

        for(int dig = 0;dig<=upperBound;dig++){

            tot = ( tot + solve(limit, n-1, sum + dig, tight == 0 ? 0 : (dig==upperBound ? 1 : 0)) )% MOD;
        }

        return dp[n][sum][tight] =  tot;


    }

    private int bruteForce(String num){

        int sum = 0;

        for(int i=0;i<num.length();i++){
            sum += num.charAt(i)-'0';
        }

        return sum >=minSum && sum <=maxSum ? 1 : 0;
    }
}