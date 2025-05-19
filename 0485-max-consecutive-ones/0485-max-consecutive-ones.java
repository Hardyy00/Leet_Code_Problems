class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {

        // Since count of 1== sum of 1 , so do sum of 1, on encountering a 0, take maximum of that sum
        //  and assign sum to zero
        
        // TC : O(N)
        // SC : O(1)
        
        int sum =0;
        int maxi =0;

        int n= nums.length;
        for(int i=0;i<n;i++ ){

            if(nums[i]==0) {
                maxi =Math.max(maxi,sum);
                sum = 0;
            }else{
                sum++;
            }
        }

        maxi = Math.max(maxi,sum);

        return maxi;
    }
}