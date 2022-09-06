
class Solution {
    public int searchInsert(int[] nums, int target) {
                
        int p =-1;
                    
        for(int i=0; i<nums.length; i++){

            if(target<=nums[i]){
                return i;
            }else if(target > nums[i]){
                p = i+1; 
            }

        }
        
         return p;
   
    }
    
}