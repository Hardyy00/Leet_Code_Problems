class Solution {
    public int[] twoSum(int[] numbers, int target) {

        int firstIndex = -1;
        int lastIndex = -1;

        for (int i = 0; i < numbers.length; i++) {

            firstIndex = i;
            lastIndex = binarySearch(numbers, target - numbers[i], target);

            if (lastIndex != -1)
                break;
        }

        lastIndex += firstIndex == lastIndex ? 1 : 0;
        int[] ans = { firstIndex + 1, lastIndex + 1 };

        return ans;

    }

    private static int binarySearch(int[] arr, int ele, int target) {

        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {

            int mid = low + (high - low) / 2;

            if (arr[mid] < ele) {
                low = mid + 1;
            } else if (arr[mid] > ele) {
                high = mid - 1;
            } else {
                return mid;
            }
        }

        return -1;
    }

}