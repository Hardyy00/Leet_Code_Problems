class Solution {
    public int maxProfit(int k, int[] prices) {
        
        int n = prices.length;
        // int totTrans = 2*k;

        // int[][] dp = new int[n][totTrans];      // 2*k = k buys and k sell
        

        // for(int[] row : dp) Arrays.fill(row, -1);


        // return maxProfit(0 , 0 ,totTrans,  prices, dp);

        // return maxProfit2(n, k , prices);

        // return maxProfit3(n, k , prices);

        return maxProfit4(n, k , prices);

    }

    int maxProfit4(int n,int k ,int[] prices){

        // Space Optimised
        // TC : O(N*2*K) (1 array)
        // SC : O(2*K)

        int totTrans = 2*k;
        int[] pre = new int[totTrans+1];
        // int[] curr = new int[totTrans+1];


        for(int i=n-1;i>=0;i--){

            // J==totTrans , for out of bound condition
            for(int j=0;j<totTrans;j++){

                if(j%2==0){

                    int buy = -prices[i] + pre[j+1];

                    int notBuy = pre[j];

                    pre[j] = Math.max(buy , notBuy);
                }else{

                    int sell = prices[i] + pre[j+1];

                    int notSell = pre[j];

                    pre[j] = Math.max(sell , notSell);
                }
            }

            // int[] temp = curr;
            // curr = pre;
            // pre = temp;
        }

        return pre[0];
    }

    int maxProfit3(int n,int k ,int[] prices){

        // Space Optimised
        // TC : O(N*2*K)
        // SC : O(2*K)

        int totTrans = 2*k;
        int[] pre = new int[totTrans+1];
        int[] curr = new int[totTrans+1];


        for(int i=n-1;i>=0;i--){

            // J==totTrans , for out of bound condition
            for(int j=0;j<totTrans;j++){

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

            int[] temp = curr;
            curr = pre;
            pre = temp;
        }

        return pre[0];
    }

    int maxProfit2(int n,int k ,int[] prices){

        // Tabulation
        // TC : O(N*2*K)
        // SC : O(N*2*K)

        int totTrans = 2*k;
        int[][] dp = new int[n+1][totTrans+1];

        for(int i=n-1;i>=0;i--){

            // J==totTrans , for out of bound condition
            for(int j=0;j<totTrans;j++){

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

    int maxProfit(int index, int trans,int totTrans, int[] prices, int[][] dp){

        // suppose for ,  you can buy only on even indexes and sell on odd indexes
        // since there are k transactions ,k are even (buy) && k are odd(sell)
        // therefore total of 2*k tarnsaction

        // Memoization
        // TC : O(N*2*K)
        // SC : O(N*2*K) + O(N)

        // you have done all 2*k transaction ((k buy k sell))
        if(trans == totTrans || index == prices.length) return 0;

        if(dp[index][trans]!=-1) return dp[index][trans];

        // since transaction is even, it must be buy
        if(trans % 2 == 0){
            
            // since you are buying, now you sell so increase the transaction from even to odd
            int buy = -prices[index] + maxProfit(index+1 , trans+1,totTrans, prices, dp );

            int notBuy = maxProfit(index+1 , trans,totTrans, prices , dp);

            return dp[index][trans] = Math.max(buy, notBuy);
        }

        // transaction is odd, it must be of sell
        // since you are selling, now you can buy again so increase the transaction from odd to even

        int sell = prices[index] + maxProfit(index+1 , trans+1 ,totTrans, prices, dp);

        int notSell = maxProfit(index+1, trans ,totTrans, prices, dp);

        return dp[index][trans] = Math.max(sell  , notSell);



    }
}