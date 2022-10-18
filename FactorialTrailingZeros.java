class Solution {
    public int trailingZeroes(int n) {

        int count = 0;
        int i = 5;

        while (true) {

            int div = n / i;
            count += div;

            if (div == 0)
                break;

            i *= 5;
        }

        return count;

    }
}