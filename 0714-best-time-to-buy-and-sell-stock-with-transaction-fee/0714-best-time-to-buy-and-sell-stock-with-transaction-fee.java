class Solution {
    public int maxProfit(int[] prices, int fee) {

        int n = prices.length;

        // int[][] dp = new int[n][2];

        // for(int[] row : dp) Arrays.fill(row, -1);

        // return maxProfit(0, 1, fee, prices, dp);

        // return maxProfit2(n, prices, fee);

        return maxProfit3(n, prices, fee);
        
    }

    int maxProfit3(int n, int[] prices, int fee){

        // Space Optimized
        // TC : O(N*2)
        // SC : O(2)

        int[] pre = new int[2];
        int[] curr = new int[2]; 

        for(int i=n-1;i>=0;i--){

            for(int j=0;j<=1;j++){

                if(j==1){

                    int buy = -prices[i] - fee + pre[0];

                    int notBuy = pre[1];

                    curr[j] = Math.max(buy , notBuy);

                }else{
                    
                    int sell = prices[i] + pre[1];

                    int notSell = pre[0];

                    curr[j] = Math.max(sell , notSell);

                }
            }

            int[] temp = pre;
            pre = curr;
            curr = temp;
        }

        return pre[1];

    }


    int maxProfit2(int n, int[] prices, int fee){

        // Tabulation
        // TC : O(N*2)
        // SC : O(N*2)

        int[][] dp = new int[n+1][2];

        for(int i=n-1;i>=0;i--){

            for(int j=0;j<=1;j++){

                if(j==1){

                    int buy = -prices[i] - fee + dp[i+1][0];

                    int notBuy = dp[i+1][1];

                    dp[i][j] = Math.max(buy , notBuy);

                }else{
                    
                    int sell = prices[i] + dp[i+1][1];

                    int notSell = dp[i+1][0];

                    dp[i][j] = Math.max(sell , notSell);

                }
            }
        }

        return dp[0][1];

    }


    int maxProfit(int index, int canBuy , int fee, int[] prices, int[][] dp){

        // Memoization
        // TC : O(N*2)
        // SC : O(N*2)

        if(index==prices.length) return 0;

        if(dp[index][canBuy]!=-1) return dp[index][canBuy];

        if(canBuy==1){
            
            // on buying a stock you have to pay a fee
            int buy = -prices[index] - fee + maxProfit(index+1, 0 , fee, prices, dp);

            int notBuy = maxProfit(index+1 , 1 , fee, prices, dp);

            return dp[index][canBuy] = Math.max(buy, notBuy);
        }


        int sell = prices[index] + maxProfit(index + 1, 1, fee, prices, dp);

        int notSell = maxProfit(index+1 , 0 , fee, prices, dp);

        return dp[index][canBuy] = Math.max(sell, notSell);
    }

}