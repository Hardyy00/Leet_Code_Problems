class Solution {
    public int removeDuplicates(int[] nums) {
        
        // Use a pointer, to insert unique element in the at the position where it should be.
        // in the end pointer's value will be equal to the number of unique elements

        // TC : O(N)
        // SC : O(1)
        
        int pointer =1;

        int n= nums.length;
        for(int i=1;i<n;i++){

            if(nums[i] != nums[i-1]) nums[pointer++] = nums[i];

        }

        return pointer;
    }
}