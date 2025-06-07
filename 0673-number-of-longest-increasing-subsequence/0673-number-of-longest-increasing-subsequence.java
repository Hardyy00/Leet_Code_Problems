class Solution {
    public int findNumberOfLIS(int[] arr) {

        // Tabulation
        // TC : O(N^2)
        // SC : O(N)

        // ALWAYS keep track of no. of ways you can make a particular length lcs
        
        int n = arr.length;

        int[] dp = new int[n];
        int[] count = new int[n]; 

        int maxLen = 0;
        for(int i=0;i<n;i++){

            dp[i] = 1;
            count[i] = 1;
            for(int j=0;j<i;j++){

                if(arr[i]>arr[j] && 1 + dp[j] > dp[i]){
                    dp[i] = 1+dp[j];
                    // if its first time i am making a lis of greater size, then getting in how many ways
                    // was i able make sequence till previous element
                    count[i] = count[j];

                }else if(arr[i]>arr[j] && 1+dp[j]==dp[i]){
                    // if i can make the lis in another way, then getting in how many ways i can make the 
                    // increasing sequence till last element (arr[j]) and adding them to the current
                    // total number of ways in which i can make lis of that same length
                    count[i] += count[j];
                }
            }

            maxLen = Math.max(maxLen, dp[i]);
        }


        int ways = 0;

        // adding all the ways in with i can make lis
        for(int i = 0;i<n;i++){

            if(dp[i]==maxLen) ways += count[i];
        }

        return ways;
    }
}