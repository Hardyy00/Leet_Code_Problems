class Solution {
    public int[] searchRange(int[] nums, int target) {
        
        int minIndex = binarySearch(nums, target , true);
        int maxIndex = -1;
        if(minIndex!=-1){
            maxIndex = binarySearch(nums, target , false);
        }
        
        int[] ans = {minIndex , maxIndex};
        
        return ans;
    }
    
    private static int binarySearch(int[] arr , int target , boolean toFindMin){
        
        int lower = 0;
        int higher = arr.length-1;
        
        int index = -1;
        while(lower<=higher){
            
            int mid = lower + (higher-lower)/2;
            
            if(toFindMin){
                
                if(arr[mid]<target){
                    lower = mid +1;
                }else{
                    if(arr[mid] == target)
                        index = mid;
                    
                    higher = mid -1;
                }
                
            }else{
                
                if(arr[mid]<=target){
                    
                    if(arr[mid]==target){
                        index = mid;
                    }
                    
                    lower = mid + 1;
                }else{
                    higher = mid -1;
                }
                
            }
        }
        
        return index;
    }
}
