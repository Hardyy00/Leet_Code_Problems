class Solution {
    public int change(int amount, int[] coins) {


        int n = coins.length;
        // int[][] dp = new int[n][amount+1];

        // for(int[] row : dp) Arrays.fill(row,-1);

        // return coinChange(n-1,amount,coins,dp);

        // return coinChange2(n,amount,coins);

         return coinChange3(n,amount,coins);
    }

    int coinChange3(int n, int amount, int[] coins){

        // Space Optimised
        // TC : O(N*Amount) && SC : O(Amount)

        int[] pre = new int[amount+1];

        for(int i=0;i<=amount;i++){

            if(i%coins[0]==0) pre[i]=1;
        }

        for(int i=1;i<n;i++){
            int[] curr = new int[amount+1];
            for(int am = 0;am<=amount;am++){

                int take = 0;
                if(coins[i]<=am){
                    take = curr[am-coins[i]];
                }

                int notTake = pre[am];

                curr[am] = take + notTake;
            }

            pre = curr;
        }

        return pre[amount];
    }

    int coinChange2(int n, int amount, int[] coins){

        // Tabulation
        // TC : O(N*Amount) && SC : O(N * Amount)

        int[][] dp = new int[n][amount+1];

        for(int i=0;i<=amount;i++){

            if(i%coins[0]==0) dp[0][i]=1;
        }

        for(int i=1;i<n;i++){

            for(int am = 0;am<=amount;am++){

                int take = 0;
                if(coins[i]<=am){
                    take = dp[i][am-coins[i]];
                }

                int notTake = dp[i-1][am];

                dp[i][am] = take + notTake;
            }
        }

        return dp[n-1][amount];
    }

    int coinChange(int index, int amount , int[] coins, int[][] dp){

        // Memoization
        // TC : O(N*Amount) && SC : O(N*Amount) + O(Amount)

        if(dp[index][amount]!=-1) return dp[index][amount];
        if(index==0){
            return amount%coins[0]==0 ? 1 : 0;
        }

        int take = 0;

        if(coins[index]<=amount){

            take = coinChange(index,amount-coins[index],coins,dp);
        }

        int notTake = coinChange(index-1,amount,coins,dp);

        return dp[index][amount] = take + notTake;
    }
}