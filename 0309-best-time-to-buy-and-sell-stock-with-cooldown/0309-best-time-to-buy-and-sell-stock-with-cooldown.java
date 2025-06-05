class Solution {
    public int maxProfit(int[] prices) {

        int n = prices.length;

        // int[][] dp = new int[n][2];

        // for(int[] row : dp) Arrays.fill(row, -1);

        // return maxProfit(0, 1 , prices, dp);

        // return maxProfit2(n, prices);

        return maxProfit3(n, prices);
        
    }

    int maxProfit3(int n, int[] prices){

        // Space Optimised
        // TC : O(N*2)
        // SC : O(2) (constant space)

        // for current row (i) , previous row (i+1) , previous of previous row (i+2)
        int[] past = new int[2];
        int[] pre = new int[2];
        int[] curr = new int[2];

        for(int i=n-1;i>=0;i--){

            for(int j=0;j<=1;j++){

                if(j==1){

                    int buy = -prices[i] + pre[0];
                    
                    int notBuy = pre[1];

                    curr[j] = Math.max(buy , notBuy);

                }else{
                    
                    int sell = prices[i] + past[1];

                    int notSell = pre[0];

                    curr[j] = Math.max(sell , notSell);

                }
            }
            // make the previous past
            int[] temp = pre;
            pre = past;
            past = temp;

            // make the current row to previous row
            temp = pre;
            pre = curr;
            curr = temp;
        }

        return pre[1];
    }


    int maxProfit2(int n, int[] prices){

        // Tabulation
        // TC : O(N*2)
        // SC : O(N*2)

        int[][] dp = new int[n+2][2];

        for(int i=n-1;i>=0;i--){

            for(int j=0;j<=1;j++){

                if(j==1){

                    int buy = -prices[i] + dp[i+1][0];
                    
                    int notBuy = dp[i+1][1];

                    dp[i][j] = Math.max(buy , notBuy);

                }else{
                    
                    int sell = prices[i] + dp[i+2][1];

                    int notSell = dp[i+1][0];

                    dp[i][j] = Math.max(sell , notSell);

                }

            }
        }

        return dp[0][1];
    }




    int maxProfit(int index, int canBuy , int[] prices, int[][] dp ){

        // Memoization
        // TC : O(N*2)
        // SC : O(N*2) + O(N)

        if(index>=prices.length) return 0;

        if(dp[index][canBuy]!=-1) return dp[index][canBuy];

        if(canBuy == 1){

            int buy = -prices[index] + maxProfit(index+1 , 0 , prices, dp);

            int notBuy = maxProfit(index + 1, 1 , prices, dp);

            return dp[index][canBuy] = Math.max(buy , notBuy);
        }

        // since , you have sold a stock, you cannot move to a next index, instead jump 2 indexex
        int sell = prices[index] + maxProfit(index+2, 1, prices, dp);

        int notSell = maxProfit(index+1 , 0 , prices, dp);

        return dp[index][canBuy] = Math.max(sell , notSell);
    }
}