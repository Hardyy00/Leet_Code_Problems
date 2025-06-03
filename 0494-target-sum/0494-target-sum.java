class Solution {
    public int findTargetSumWays(int[] nums, int target) {

        // we have to divide the array into two subsets such that their difference yields the target
        // hence , uses the same logic as , no. of subsets with given differnce
        
        // s1 -s2 = target && s1 + s2 = sum => 2s1 = sum + target => s1 = (sum+target)/2;

        int sum = 0;

        for(int i : nums) sum += i;

        // if it isn't divisible then such subsets are not possible
        if((target+sum)%2!=0) return 0;

        int newTarget = (target+sum)/2; 
        int n = nums.length;

        // if newTarget is -ve , then such subsets are not possible
        if(newTarget<0) return 0;

        // int[][] dp = new int[n][newTarget+1];

        // for(int[] row : dp) Arrays.fill(row,-1);

        // return subsetsWithTargetSum(n-1,newTarget,nums, dp);

        // return subsetsWithTargetSum2(n,newTarget,nums);

         return subsetsWithTargetSum3(n,newTarget,nums);
    
    }

    int subsetsWithTargetSum3(int n, int target, int[] nums){

        // Space Optimised
        // TC : O(N*TARGET)  && SC : O(N * Target)

        int[] pre = new int[target+1];

        if(nums[0]==0) pre[0] = 2;
        else pre[0] = 1;

        if(nums[0]!=0 && nums[0]<=target) pre[nums[0]] = 1;

        for(int i=1;i<n;i++){
            int[] curr = new int[target+1];
            for(int tar = 0;tar<=target;tar++){

                int take = 0;
                if(nums[i]<=tar){
                    take = pre[tar-nums[i]];
                }

                int notTake = pre[tar];

                curr[tar] = take + notTake;
            }

            pre = curr;
        }

        return pre[target];
    }



    int subsetsWithTargetSum2(int n, int target, int[] nums){

        // Tabulation
        // TC : O(N*TARGET)  && SC : O(N * Target)

        int[][] dp = new int[n][target+1];

        if(nums[0]==0) dp[0][0] = 2;
        else dp[0][0] = 1;

        if(nums[0]!=0 && nums[0]<=target) dp[0][nums[0]] = 1;

        for(int i=1;i<n;i++){

            for(int tar = 0;tar<=target;tar++){

                int take = 0;
                if(nums[i]<=tar){
                    take = dp[i-1][tar-nums[i]];
                }

                int notTake = dp[i-1][tar];

                dp[i][tar] = take + notTake;
            }
        }

        return dp[n-1][target];
    }

    int subsetsWithTargetSum(int index, int target, int[] nums, int[][] dp){

        // Memoization
        // TC : O(N*TARGET) && SC : O(N*TARGET) + O(N)
        
        if(dp[index][target]!=-1) return dp[index][target];
        if(index==0){

            if(nums[0]==0 && target==0) return 2;
            if(nums[0]==target || target==0) return 1;

            return 0;
        }

        int take = 0;
        if(nums[index]<=target){
            take = subsetsWithTargetSum(index-1,target-nums[index],nums,dp);
        }

        int notTake = subsetsWithTargetSum(index-1,target,nums,dp);

        return dp[index][target] = take + notTake;
    }
}