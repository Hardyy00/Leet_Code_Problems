class Solution {

    public void rotate(int[] nums, int k) {

        int length = nums.length;
        k = k % length;

        rotateAlgo(nums, length - k, length - 1);

        rotateAlgo(nums, 0, length - k - 1);

        rotateAlgo(nums, 0, length - 1);

    }

    private static void rotateAlgo(int[] arr, int i, int j) {

        while (i < j) {

            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;

            i++;
            j--;
        }
    }
}