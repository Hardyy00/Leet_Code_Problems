class Solution {
    public int search(int[] nums, int target) {

        // SInce the array is sorted and then rotated so if seeing from middle a portion must
        // be sorted, in that sorted portion check if the target can lie in that range
        // if yes, then move to that place, else move to other space

        // TC : O(logN)
        // SC : O(1)
        
        int low = 0;
        int high = nums.length-1;

        while(low<=high){

            int mid = high + (low-high)/2;


            if(nums[mid]==target) return mid;

            if(nums[low] <= nums[mid]){

                if(target>=nums[low] && target<=nums[mid]){
                    high =mid-1;
                }else{
                    low = mid+1;
                }
            }else if(nums[mid]<=nums[high]){

                if(target>=nums[mid] && target<=nums[high]){
                    low = mid+1;

                }else{
                    high = mid-1;
                }
            }
        }

        return -1;
    }
}