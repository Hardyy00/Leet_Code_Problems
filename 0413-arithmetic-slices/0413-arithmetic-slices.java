class Solution {
    public int numberOfArithmeticSlices(int[] nums) {

    //    return solve(nums);


        return solve2(nums);
    }

    private int solve2(int[] nums){
    
        int n = nums.length;
        int diff = 10000;
        int count = 0;
        int run = 0;

        if(nums.length < 3){
            return 0;
        }

        for(int i=1;i<n;i++){

            if(nums[i]-nums[i-1]==diff){
                run++;
            }else{

                count += (run * (run +1))/2;
                run = 0;
            }

            diff = nums[i]-nums[i-1];
        }

        count += (run * (run + 1))/2;


        return count;
    }

    private int solve(int[] nums){

         // TC : O(N)
        // SC : O(N)

        // for each element we calculate the number of slice with this element at their last element
        // the size must be atleast 3, so start with idx 2
        // if nums[i]-nums[i-1]==nums[i-1]-nums[i-2], that means it make an arithmetic  sequence
        // of length 3, now number of subarray for this index will be dp[i-1] + 1 
        // why? it will make sequence with all the subarray, ending with nums[i-1] 
        // and +1 , as it make a length 3 sequnce ith num[i-1] and nums[i-2]

        // along the way we add dp[i] , to sum variable to count all the subarrays

        int n = nums.length;
        int sum = 0;
        int[] dp = new int[n];


        for(int i=2;i<n;i++){

            if(nums[i]-nums[i-1]==nums[i-1]-nums[i-2]){
                dp[i]=dp[i-1] + 1;
                sum += dp[i];
            }
        }

        return sum;

    }
}