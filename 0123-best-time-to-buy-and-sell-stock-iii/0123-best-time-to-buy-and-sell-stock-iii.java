class Solution {
    public int maxProfit(int[] prices) {

        int n = prices.length;
        // int[][][] dp = new int[n][2][3];

        // for(int[][] mat : dp){

        //     for(int[] row : mat) Arrays.fill(row , -1);
        // }

        // return maxProfit1(0 , 1, 2, prices, dp);

        // return maxProfit2(n , prices);  
        // return maxProfit3(n , prices);

        // int[][] dp = new int[n][4];

        // for(int[] row : dp) Arrays.fill(row , -1);

        // return profit(0,0,prices, dp);  

        // return profit2(n, prices); 

        return profit3(n, prices);      

        
    }

    int profit3(int n, int[] prices){

        // Space optimised
        // TC : O(N*4)
        // SC : O(5)

        int[] pre = new int[5];
        int[] curr = new int[5];

        for(int i=n-1;i>=0;i--){

            for(int j=0;j<4;j++){

                if(j%2==0){

                    int buy = -prices[i] + pre[j+1];
                    int notBuy = pre[j];

                    curr[j] = Math.max(buy , notBuy);
                }else{

                    int sell = prices[i] + pre[j+1];
                    int notSell = pre[j];

                    curr[j] = Math.max(sell , notSell);
                }
            }

            int[] temp = pre;
            pre = curr;
            curr = pre;
        }

        return pre[0];
    }

    int profit2(int n, int[] prices){

        // Tabulation
        // TC : O(N*4)
        // SC : O(N*5)

        int[][] dp = new int[n+1][5];

        for(int i=n-1;i>=0;i--){

            for(int j=0;j<4;j++){

                if(j%2==0){

                    int buy = -prices[i] + dp[i+1][j+1];
                    int notBuy = dp[i+1][j];

                    dp[i][j] = Math.max(buy , notBuy);
                }else{

                    int sell = prices[i] + dp[i+1][j+1];
                    int notSell = dp[i+1][j];

                    dp[i][j] = Math.max(sell , notSell);
                }
            }
        }

        return dp[0][0];
    }

    int profit(int index, int transaction, int[] prices, int[][] dp){

        // suppose for you can buy only on even indexes and sell on odd indexes
        // since there are two transactions , 2 are even (buy) && 2 are odd(sell)
        // therefore total of 4 tarnsaction

        // Memoization
        // TC : O(N*4)
        // SC : O(N*4) + O(N)

        // you have done all 2 transaction ((1 buy 1 sell) && (1buy 1 sell))
        if(transaction == 4 || index == prices.length) return 0;

        if(dp[index][transaction]!=-1) return dp[index][transaction];
        
        // since transaction is even, it must be buy
        if(transaction %2 ==0){

            // since you are buying, now you sell so increase the transaction from even to odd
            int buy = -prices[index] + profit(index+1, transaction+1 , prices, dp);

            int notBuy = profit(index+1, transaction , prices, dp);

            return dp[index][transaction] = Math.max(buy , notBuy);
        }

        // transaction is odd, it must be of sell
        // since you are selling, now you can buy again so increase the transaction from odd to even
        int sell = prices[index] + profit(index+1, transaction+1, prices, dp);

        int notSell = profit(index+1, transaction, prices, dp);

        return dp[index][transaction] = Math.max(sell, notSell);
    }

    int maxProfit3(int n,int[] prices){

        // Space Optimization
        // TC : O(N*2*3)
        // SC : O(2*3)

        int[][] pre = new int[2][3];
        int[][] curr = new int[2][3];

        for(int i=n-1;i>=0;i--){

            for(int j=0;j<=1;j++){

                for(int k=1;k<=2;k++){
                    
                    if(j==1){

                        int buy = -prices[i] + pre[0][k];
                        int notBuy = pre[1][k];

                        curr[j][k] = Math.max(buy, notBuy);
                    }else{

                        // sice you have sold a stock reduce the remaining transaction by 1
                        int sell = prices[i] + pre[1][k-1];
                        int notSell = pre[0][k];

                        curr[j][k] = Math.max(sell , notSell);
                    }

                }
            }

            int[][] temp = pre;
            pre = curr;
            curr = temp;
        }

        return pre[1][2];
    }

    int maxProfit2(int n,int[] prices){

        // Tabulation
        // TC : O(N*2*3)
        // SC : O(N*2*3)

        int[][][] dp = new int[n+1][2][3];

        for(int i=n-1;i>=0;i--){

            for(int j=0;j<=1;j++){

                for(int k=1;k<=2;k++){
                    
                    if(j==1){

                        int buy = -prices[i] + dp[i+1][0][k];
                        int notBuy = dp[i+1][1][k];

                        dp[i][j][k] = Math.max(buy, notBuy);
                    }else{

                        // sice you have sold a stock reduce the remaining transaction by 1
                        int sell = prices[i] + dp[i+1][1][k-1];
                        int notSell = dp[i+1][0][k];

                        dp[i][j][k] = Math.max(sell , notSell);
                    }

                }
            }
        }

        return dp[0][1][2];
    }

    int maxProfit1(int index, int canBuy, int remainingTransaction,int[] prices, int[][][] dp){
        
        // an transaction occurs when a buy and sell is completed , so when you sell, decrease the
        // remaining tarnsactions by 1

        // Memoization
        // TC : O(N*2*3)
        // SC : O(N*2*3) + O(N)

        // no transactions are remaining so return from it
        if(remainingTransaction==0) return 0;

        if(index==prices.length) return 0;

        if(dp[index][canBuy][remainingTransaction]!=-1) return dp[index][canBuy][remainingTransaction];

        if(canBuy == 1){

            int buy = -prices[index] + maxProfit1(index+1 , 0 , remainingTransaction, prices, dp);
        
            int notBuy = maxProfit1(index+1, 1 , remainingTransaction, prices, dp);

            return dp[index][canBuy][remainingTransaction] = Math.max(buy , notBuy);
        }

        // since you are selling a stock, 1 transaction occurs so decrease the remaining transaction
        int sell = prices[index] + maxProfit1(index+1 , 1, remainingTransaction-1, prices, dp);

        int notSell = maxProfit1(index+1, 0 , remainingTransaction, prices, dp);

        return dp[index][canBuy][remainingTransaction] = Math.max(sell , notSell);

    }
}