
public class Solution extends VersionControl {
    public int firstBadVersion(int n) {

        int lower = 1;
        int higher = n;

        int badNumber = -1;
        while (lower <= higher) {

            int mid = lower + (higher - lower) / 2;

            if (isBadVersion(mid)) {
                badNumber = mid;
                higher = mid - 1;
            } else {
                lower = mid + 1;
            }

        }

        return badNumber;

    }
}