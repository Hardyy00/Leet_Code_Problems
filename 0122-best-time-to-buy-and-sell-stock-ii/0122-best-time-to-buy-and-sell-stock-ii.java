class Solution {

    public int maxProfit(int[] prices) {

        // return maxProfit1(0,1, prices);

        // int[][] dp = new int[prices.length][2];

        // for(int[] row : dp) Arrays.fill(row , -1);     

        // return maxProfit2(0,1, prices, dp);    

        // return maxProfit3(prices);

        // return maxProfit4(prices);

        return maxProfit5(prices);

        
    }

    int maxProfit5(int[] prices){
        
        // Space Optimisation
        // TC : O(N)
        // SC : O(1) (space constant)

        int n = prices.length;

        int preBuy = 0, preNotBuy = 0;
        int currBuy = 0, currNotBuy = 0;

        for(int i=n-1;i>=0;i--){

            // you can only buy or not buy
                    
            // SINCE you are buying , on next day , i+1, you cannot buy, so 0
            int buy = -prices[i] + preNotBuy;
            // you can buy on next day
            int notBuy = preBuy;

            currBuy = Math.max(buy , notBuy);
      
            // you can buy on next day
            int sell = prices[i] + preBuy;

            // you cannot buy on next day , you have to selll it first
            int notSell = preNotBuy;

            currNotBuy = Math.max(sell, notSell);

            preBuy = currBuy;
            preNotBuy = currNotBuy;    

        }

        // since at o th day, you have to buy or not buy (so 1)
        return  preBuy;
    }

    int maxProfit4(int[] prices){
        
        // Space Optimisation
        // TC : O(N*2)
        // SC : O(4) (space constant)

        int n = prices.length;

        int[] pre = new int[2];
        int[] curr = new int[2];

        for(int i=n-1;i>=0;i--){

            for(int j=1;j>=0;j--){

                // you can only buy or not buy
                if(j==1){
                    
                    // SINCE you are buying , on next day , i+1, you cannot buy, so 0
                    int buy = -prices[i] + pre[0];
                    // you can buy on next day
                    int notBuy = pre[1];

                    curr[j] = Math.max(buy , notBuy);

                }else{
                    
                    // you can buy on next day
                    int sell = prices[i] + pre[1];

                    // you cannot buy on next day , you have to selll it first
                    int notSell = pre[0];

                    curr[j] = Math.max(sell, notSell);
                }
            }

            int[] temp = curr;
            curr = pre;
            pre = temp;
        }

        // since at o th day, you have to buy or not buy (so 1)
        return pre[1];
    }

    int maxProfit3(int[] prices){
        
        // Tabulation
        // TC : O(N*2)
        // SC : O(N*2)

        int n = prices.length;

        int[][] dp = new int[n+1][2];

        for(int i=n-1;i>=0;i--){

            for(int j=1;j>=0;j--){

                // you can only buy or not buy
                if(j==1){
                    
                    // SINCE you are buying , on next day , i+1, you cannot buy, so 0
                    int buy = -prices[i] + dp[i+1][0];
                    // you can buy on next day
                    int notBuy = dp[i+1][1];

                    dp[i][j] = Math.max(buy , notBuy);

                }else{
                    
                    // you can buy on next day
                    int sell = prices[i] + dp[i+1][1];

                    // you cannot buy on next day , you have to selll it first
                    int notSell = dp[i+1][0];

                    dp[i][j] = Math.max(sell, notSell);
                }
            }
        }

        // since at o th day, you have to buy or not buy (so 1)
        return dp[0][1];
    }

    int maxProfit2(int index, int canBuy, int[] prices, int[][] dp){

        // Memoization
        // TC : O(N*2)
        // SC : O(N*2) + O(N)

        if(index== prices.length) return 0;

        if(dp[index][canBuy]!=-1) return dp[index][canBuy];

        if(canBuy==1){

            int buy = -prices[index] + maxProfit2(index+1 , 0 , prices, dp);

            int notBuy = maxProfit2(index+1, 1 , prices,dp);

            return dp[index][canBuy] = Math.max(buy , notBuy);

        }

        // canBuy == 0 (sell)

        int sell = prices[index] + maxProfit2(index+1 , 1 , prices, dp);

        int notSell = maxProfit2(index+1, 0 , prices, dp);

        return dp[index][canBuy] = Math.max(sell , notSell);

    }

    int maxProfit1(int index, int canBuy , int[] prices){

        // intution : if you havn't bought anything , then you can either buy current stock or not buy that
        // but if you have alrady bought a stock then you can either sell it on current day or not sell it
        // 1 means you can buy on the day, 0 means you haave already bought a stock , so can only sell it

        // Recursion
        // TC : O(2^N) (since at every index you have two choices)
        // SC : O(N)
        
        // if the days are finised , so even if you buy a stock on last day , you cannot sell it, 
        // so return 0, the maximum condition will take care of it , as it will return 0
        if(index==prices.length)  return 0;
        

        if(canBuy==1){
            
            // since you are buying , you are losing money so -ve sign occurrs
            int buy = -prices[index] + maxProfit1(index+1, 0, prices);

            // you are not buying anything , so you can buy on another day , so pass 1
            int notBuy = maxProfit1(index+1 , 1, prices);

            return Math.max( buy, notBuy);
        }

        // canBuy == 0 (can sell)

        // you are sell the stock , so prices comes to you, now you can buy another stock , so 1
        int sell = prices[index] + maxProfit1(index+1 , 1 , prices);

        // currently ,you are not selling the stock
        int notSell =  maxProfit1(index+1, 0, prices);

        return Math.max(sell, notSell); 
    }
}