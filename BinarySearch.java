class Solution {
    public int search(int[] nums, int target) {

        int lower = 0;
        int higher = nums.length - 1;

        while (lower <= higher) {

            int mid = lower + (higher - lower) / 2;

            if (nums[mid] < target) {
                lower = mid + 1;
            } else if (nums[mid] > target) {
                higher = mid - 1;
            } else {
                return mid;
            }
        }

        return -1;

    }
}