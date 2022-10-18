
public class Solution extends GuessGame {
    public int guessNumber(int n) {

        int lower = 1;
        int higher = n;

        while (lower <= higher) {

            int mid = lower + (higher - lower) / 2;

            if (guess(mid) == -1) {
                higher = mid - 1;
            } else if (guess(mid) == 1) {
                lower = mid + 1;
            } else {
                return mid;
            }
        }

        return -1;

    }
}