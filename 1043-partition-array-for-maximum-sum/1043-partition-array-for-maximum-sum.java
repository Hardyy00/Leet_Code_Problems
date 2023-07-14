class Solution {
    public int maxSumAfterPartitioning(int[] arr, int k) {

        int n = arr.length;
        // return solve(0, k, arr);

    
        // int[] dp = new int[n];
        // Arrays.fill(dp, -1);

        // return solve2(0,k,arr,dp);

        return solve3(k, arr, n);
    }

    int solve3(int k, int[] arr, int n){

        // Tabulation
        // TC : O(N*K)
        // SC : O(N)

        int[] dp = new int[n+1];

        for(int i=n-1;i>=0;i--){

            int maxi = 0;
            int maxNumber = 0;
            for(int j=i;j<=i+k-1 && j<n;j++){

                maxNumber = Math.max(maxNumber, arr[j]);

                int sum = (j-i+1) * maxNumber + dp[j+1];

                maxi = Math.max(maxi, sum);
                
            }

            dp[i] = maxi;
        }

        return dp[0];
    }

    int solve2(int index, int k, int[] arr, int[] dp){

        // Memoization
        // TC : O(N * K)
        // SC : O(N) + O(N)

        if(index==arr.length) return 0;

        if(dp[index]!=-1) return dp[index];

        int maxi = 0;

        int maxNumber = 0;

        // index+k-1 , k indexes from current index
        for(int i=index;i<=index+k-1 && i<arr.length;i++){
            
            // to get the current maximum in the current subarray
            maxNumber = Math.max(maxNumber, arr[i]);

            // maximum number in the current subarray * total elements in the subarray 
            // because we have to replace every element with maximum element in that sub array
            int sum = (i-index+1) * maxNumber + solve2(i+1,k, arr, dp);

            maxi = Math.max(maxi, sum);

        }

        return dp[index] = maxi; 

    }

    int solve(int index, int k, int[] arr){

        // Recursion
        // TC : O(Exponential)
        // SC : O(N)

        if(index==arr.length) return 0;

        int maxi = 0;

        int maxNumber = arr[index];
        for(int i=index;i<=index+k-1 && i<arr.length ; i++){

            maxNumber = Math.max(maxNumber, arr[i]);

            int sum = (i-index+1) * maxNumber + solve(i+1, k, arr);

            maxi = Math.max(maxi, sum);

        }

        return maxi;
    }
}