class Solution {
    public int numberOfArithmeticSlices(int[] nums) {

        // int n = nums.length;
        // int sum = 0;
        // int[] dp = new int[n];


        // for(int i=2;i<n;i++){

        //     if(nums[i]-nums[i-1]==nums[i-1]-nums[i-2]){
        //         dp[i]=dp[i-1] + 1;
        //         sum += dp[i];
        //     }
        // }

        // return sum;

        int n = nums.length;
        int sum = 0;

        for(int i=2;i<n;i++){

            for(int j=i-2;j>=0;j--){

                if(nums[j+2]-nums[j+1]==nums[j+1]-nums[j]){
                    sum++;
                }else{
                    break;
                }
            }
        }

        return sum;
        
    }
}