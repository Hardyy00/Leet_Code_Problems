class Solution {
    public int maxProfit(int[] prices) {
        
        // return solve(prices);

        return solve2(prices);
        
    }

    private int solve2(int[] prices){

        // Optimized approach : buy on the day , which has minimum buying price, and sell on the day which 
        // has maximum selling price, so just maintain a mainimumCost var, to store the minimum costing
        // and try to sell on every other day

        // TC : O(N)
        // SC : O(1)

        int maxProfit = 0;
        int minPrice = Integer.MAX_VALUE;

        for(int i : prices){

            minPrice = Math.min(minPrice,i);  // find the minimum buying price 
            maxProfit = Math.max(maxProfit,i-minPrice);  // try to sell on every day
        }

        return maxProfit;
        
    }

    private int solve(int[] prices){

        // Brute : try out all possible combination of buying and selling
        // TC : O(N^2)
        // SC : O(1)

        int maxi = 0;
        int n = prices.length;
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){

                maxi = Math.max(maxi,prices[j]-prices[i]);
            }
        }

        return maxi;
    }
}