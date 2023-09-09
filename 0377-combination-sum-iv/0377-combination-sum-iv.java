class Solution {
    public int combinationSum4(int[] nums, int target) {

        int n = nums.length;
        int[] dp= new int[target+1];

        Arrays.fill(dp,-1);

        return solve(dp,nums, target);
        
    }

    private int solve(int[] dp, int [] nums, int target){

        if(target==0) return 1;

        if(dp[target] !=-1) return dp[target];

        int comb = 0;

        for(int i=0;i<nums.length;i++){

            if(nums[i] <= target){

                comb += solve(dp,nums, target-nums[i]);
            }

        }

        return dp[target] = comb;


    }
}