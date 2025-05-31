class Solution {
    public int rob(int[] nums) {

        // int[] dp = new int[nums.length];
        // Arrays.fill(dp,-1);
        // return houseRob1(nums.length-1,nums,dp);

        // return houseRob2(nums);

        return houseRob3(nums);
        
    }

    int houseRob3(int[] arr){

        // Space Optimization
        // TC : O(N) && SC : O(1)

        int pre1 = arr[0];
        int pre2 = 0;

        for(int i=1;i<arr.length;i++){

            int pick = arr[i] + pre2;
            int notPick = pre1;

            int curr = Math.max(pick,notPick);

            pre2 = pre1;
            pre1 = curr;
        }

        return pre1;
    }

    int houseRob2(int[] arr){

        // Tabulation
        // TC : O(N) && SC : O(N)

        int[] dp = new int[arr.length];

        dp[0] = arr[0];

        for(int i=1;i<arr.length;i++){

            int pick = arr[i] + (i-2<0 ?0 : dp[i-2]);
            int notPick = dp[i-1];

            dp[i] = Math.max(pick,notPick);
        }

        return dp[arr.length-1];
    }


    int houseRob1(int n , int[] nums, int[] dp){

        // TC : O(N) + O(N) (recursion and fill() method) && SC : O(N) + O(N) (recursion stack + dp)

        if(n==0) return nums[0];
        if(n < 0) return 0;

        if(dp[n]!=-1) return dp[n];

        int pick = nums[n] + houseRob1(n-2,nums,dp);
        
        int notPick = houseRob1(n-1,nums,dp);

        return dp[n] = Math.max(pick,notPick);
    }
}