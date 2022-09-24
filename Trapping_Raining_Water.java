class Solution {
    public int trap(int[] height) {

        return trappingWater(height);

    }

    private static int trappingWater(int[] arr) {

        int[] left = new int[arr.length];
        left[0] = arr[0];

        for (int i = 1; i < arr.length; i++) {

            left[i] = Math.max(left[i - 1], arr[i]);
        }

        int[] right = new int[arr.length];

        right[arr.length - 1] = arr[arr.length - 1];

        for (int i = arr.length - 2; i >= 0; i--) {

            right[i] = Math.max(arr[i], right[i + 1]);
        }

        int water = 0;
        for (int i = 0; i < arr.length; i++) {

            water += Math.min(left[i], right[i]) - arr[i];
        }

        return water;
    }
}