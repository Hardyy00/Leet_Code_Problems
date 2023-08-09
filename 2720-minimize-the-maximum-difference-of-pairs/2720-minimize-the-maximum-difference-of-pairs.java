class Solution {
    public int minimizeMax(int[] nums, int p) {
        
        Arrays.sort(nums);

        int maxDiff = nums[nums.length-1] - nums[0];

        int low = 0;
        int high = maxDiff;

        int ans = 0;

        while(low<=high){

            int mid = high + (low - high)/2;

            if(isPossible(mid,p,nums)){
                ans = mid;
                high = mid-1;
            }else{
                low = mid+1;
            }
        }

        return ans;
    }

    private boolean isPossible(int mid, int p, int[] nums){

        int count = 0;

        for(int i=1;i<nums.length;i++){

            if(nums[i]-nums[i-1] <= mid){
                count++;
                i++;
            }
        }

        return count >= p;
    }
}