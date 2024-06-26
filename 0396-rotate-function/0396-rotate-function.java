class Solution {
    public int maxRotateFunction(int[] nums) {

        int n= nums.length;
        int[] dp = new int[n];
        int sum = 0;
        for(int i=0;i<n;i++){
            sum += nums[i];
            dp[0] += i * nums[i];
        }

        int maxi = dp[0];

        for(int i=1;i<n;i++){

            dp[i] = dp[i-1] + sum - n * (nums[n-i]);

            maxi = Math.max(maxi, dp[i]);
        }

        return maxi;
        
    }
}