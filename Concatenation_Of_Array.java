class Solution {
    public int[] getConcatenation(int[] nums) {

        int length = nums.length;
        int[] ans = new int[2 * length];

        for (int i = 0, j = 0; i < 2 * length; i++, j++) {

            ans[i] = nums[j];

            if (i == length - 1)
                j = -1;
        }

        return ans;

    }
}