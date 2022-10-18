class Solution {
    public int findPeakElement(int[] nums) {

        int lower = 0;
        int higher = nums.length - 1;

        while (lower <= higher) {

            int mid = lower + (higher - lower) / 2;
            double compareBefore = mid - 1 < 0 ? Double.NEGATIVE_INFINITY : nums[mid - 1];
            double compareAfter = mid + 1 >= nums.length ? Double.NEGATIVE_INFINITY : nums[mid + 1];

            if (nums[mid] <= compareBefore) {
                higher = mid - 1;
            } else if (nums[mid] <= compareAfter) {
                lower = mid + 1;
            } else if (nums[mid] > compareBefore && nums[mid] > compareAfter) {
                return mid;
            }
        }

        return -1;
    }
}