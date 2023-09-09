class Solution {
    public int combinationSum4(int[] nums, int target) {

        // we can always store in how many way can we make a particular target
        // for that using dynammic programming

        // int n = nums.length;
        // int[] dp= new int[target+1];

        // Arrays.fill(dp,-1);

        // return solve(dp,nums, target);

        return solve2(nums, target);
        
    }

    private int solve2(int[] nums, int target){

        int[] dp = new int[target+1];
        int n = nums.length;

        dp[0] = 1;

        for(int tar=1;tar<=target;tar++){

            int comb = 0;

            for(int i=0;i<n;i++){

                if(nums[i] <= tar){
                    comb += dp[tar-nums[i]];
                }
            }

            dp[tar] = comb;
        }

        return dp[target];
    }

    private int solve(int[] dp, int [] nums, int target){

        // Memoization
        // TC : O(TARGET * N)
        // SC : O(N) + O(target)(recursion stack space)

        if(target==0) return 1;  // target has been completed

        if(dp[target] !=-1) return dp[target];  

        int comb = 0;

        for(int i=0;i<nums.length;i++){

            // when ever we can take a target we take it
            if(nums[i] <= target){

                comb += solve(dp,nums, target-nums[i]);
            }

        }

        // no of ways we can make a particular target (like 2 ) is comb, so storing that
        // and whenever we come across a sitution where we need to make the rest
        // of the target (like 2) just get that from dp[]
        return dp[target] = comb;

    }
}