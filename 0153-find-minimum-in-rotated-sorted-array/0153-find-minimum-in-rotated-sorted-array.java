class Solution {
    public int findMin(int[] nums) {
        
        if(nums.length==2)
            return Math.min(nums[0] , nums[1]);
        
        int minElement = nums[0];
        
        int lower = 0;
        int higher = nums.length-1;
        
        while(lower<=higher){
            int mid = lower + (higher-lower)/2;
            
            if(minElement < nums[mid])
                lower = mid+1;
            else{
                minElement = Math.min(minElement , nums[mid]);
                higher = mid-1;
            }
        }
        
        return minElement;
        
    }
}