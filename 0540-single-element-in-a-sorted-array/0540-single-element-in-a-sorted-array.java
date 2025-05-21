class Solution {
    public int singleNonDuplicate(int[] nums) {

        // Since each element appears exactly twice ,at even index and its next odd index
        // we must get same element (or at odd index and it previous even index we must get same elements)
        // if they are same left side has no problem , move to right, but if otherwise, then move to left
        // do it for 0 to n-2, buz we are doing n and n+1, so it may exceed the bounday

        // TC : O(logN)
        // SC : O(1)
        
        if(nums.length==1) return nums[0];

        int low = 0;
        int high = nums.length-2;

        while(low<=high){

            int mid = high + (low - high)/2;

            // if even check odd index, but if odd index, check even index, and if no problem move to right
            if(nums[mid] == nums[mid ^ 1] ){
                low = mid+1;
            }else{
                high = mid-1;
            }
        }

        return nums[low];  // return the element
    }
}