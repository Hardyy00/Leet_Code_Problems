class Solution {
    public void nextPermutation(int[] nums) {

        boolean found = false;
        for (int i = nums.length - 1; i >= 0; i--) {

            int max = i;

            for (int j = nums.length - 1; j > i; j--) {

                if (nums[j] > nums[max]) {
                    max = j;
                    found = true;
                    break;
                }
            }

            if (found) {
                int temp = nums[max];
                nums[max] = nums[i];
                nums[i] = temp;
                reverseArray(nums, i + 1);
                break;
            }

        }
        if (!found) {
            reverseArray(nums, 0);
        }

    }

    private void reverseArray(int[] nums, int i) {

        int j = nums.length - 1;

        while (i < j) {

            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
            i++;
            j--;
        }
    }
}