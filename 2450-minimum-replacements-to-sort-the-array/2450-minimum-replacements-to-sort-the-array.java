class Solution {
    public long minimumReplacement(int[] nums) {

        int n = nums.length;
        long op = 0;

        int last = nums[n-1];

        for(int i=n-2;i>=0;i--){

            if(nums[i] > last){

                int dividingTimes = nums[i] / last;

                if(nums[i]% last !=0 ) dividingTimes++;

                op += (long)dividingTimes -1;
                last = nums[i]/dividingTimes;
            }else{
                last =  nums[i];
            }
        }

        return op;
    }
}