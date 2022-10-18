class Solution {
    public boolean isPerfectSquare(int num) {

        long lower = 1;
        long higher = num;
        long ans = 0;
        while (lower <= higher) {

            long mid = lower + (higher - lower) / 2;

            if (mid * mid <= num) {
                ans = mid;
                lower = mid + 1;
            } else {
                higher = mid - 1;
            }
        }

        return (ans * ans) == num;
    }
}