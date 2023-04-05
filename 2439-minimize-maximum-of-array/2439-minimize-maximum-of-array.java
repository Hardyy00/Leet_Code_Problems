class Solution {
    public int minimizeArrayValue(int[] nums){
        
        int result = nums[0];
        long tot = result;

        for(int i=1;i<nums.length;i++){
            
            tot +=(long) nums[i];
            result =(int) Math.max(result,Math.ceil((double)tot/(i+1)));
        }
        
        return result;
    }
}