class Solution {
    long mod = (long)1e9 + 7;
    long[][] dp = new long[2001][51];
    public int countOfPairs(int[] nums) {

        // for the inc maintain as parameter , for the dec , mutate the array according

        // TC : O(N * max(a) * max(a))
        
        for(long[] row : dp){
            Arrays.fill(row, -1);
        }
        

        return (int) memo(0,0,nums);
    }

    private long memo(int index, int inc, int[] nums){

        if(index == nums.length){
            return 1;
        }

        if(dp[index][inc] !=-1){
            return dp[index][inc];
        }

        long tot = 0;
        for(int i=inc;i<=nums[index];i++){

            int temp = nums[index];
            if(index==0){

                nums[index] = nums[index] - i;
                tot = (tot + memo(index+1, i,nums) ) % mod;


            }else if(nums[index]-i <=nums[index-1]){
    
                nums[index] = nums[index] - i;
                tot = (tot + memo(index+1, i, nums) ) % mod;

            }

            nums[index] = temp;
        }

        return dp[index][inc] = tot;
    }
}