class Solution {
    public boolean canPartition(int[] nums) {
        
        int sum = 0;

        for(int i : nums) {
            sum +=i;
        }

        if(sum %2 !=0){

            return false;
        }

        return canPartition(nums,sum/2);
    }

    private boolean canPartition(int[] nums,int target){

        int n = nums.length;
        int[] pre= new int[target+1];

        if(nums[0] <= target){
            pre[nums[0]] = 1;
        }
        
        for(int i=1;i<n;i++){

            int[] curr = new int[target+1];

            curr[0] = 1;

            for(int tar=1;tar<=target;tar++){

                int take = 0;

                if(nums[i] <= tar){
                    take = pre[tar- nums[i]];
                }

                int notTake = pre[tar]; 

                curr[tar] = take == 1 || notTake ==1 ? 1 : 0;
            }

            pre = curr;
        }

        return pre[target] == 1;
    }

    private int canPartition(int index, int amount,int[] coins,int[][] dp){

        if(amount==0){
            return 1;
        }

        if(index==0){

            return coins[index]==amount ?1 :0;
        }

        if(dp[index][amount] !=-1){

            return dp[index][amount];
        }

        int take = 0;

        if(coins[index] <= amount){
            take = canPartition(index-1,amount-coins[index],coins,dp);
        }

        int notTake = canPartition(index-1,amount,coins,dp);

        return dp[index][amount] = take==1 || notTake==1 ? 1 : 0;
    }
}