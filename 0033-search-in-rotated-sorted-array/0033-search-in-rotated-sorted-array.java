class Solution {
    public int search(int[] nums, int target) {
        
        
        int low = 0;
        int high = nums.length-1;
        
        while(low<=high){
            
            int mid = high + (low - high)/2;
            
            if(nums[mid]==target) return mid;
            
            // check if left side is sorted
            else if(nums[low]<=nums[mid]){
                
                // checking if the element exist within the left side
                // if it exist then shifting the search space to left else to right
                if(target>=nums[low] && target<=nums[mid]){
                    high = mid-1;
                }else{
                    low = mid +1;
                }
            }else{
                
                // for example like 6 7 1 2 3 4 5 . for finding 1 , 1<=2 || 1>=6 (number might be greater than 6)
                // if(target>=nums[low] || target<=nums[mid])
                
                // checking if the element exist on the sorted right side
                if(target>=nums[mid] && target<=nums[high]){
                    low = mid+1;
                }else{
                    high = mid - 1;
                }
            }
            
        }
        
        return -1;
        
        
        
    }
}