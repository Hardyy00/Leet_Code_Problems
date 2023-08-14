class Solution {
    public int findKthLargest(int[] nums, int k) {
        
        return solve(nums, k);
        
    }

    private int solve(int[] nums, int k){

        Arrays.sort(nums);

        return nums[nums.length-k];

    }
}