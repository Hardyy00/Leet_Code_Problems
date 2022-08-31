import java.util.Scanner;
import java.util.ArrayList;

class Solution {
    public int[] twoSum(int[] nums, int target) {

        int arrLength = nums.length;
        int p1 = 0;
        int p2 = 0;

        for (int i = 0; i < arrLength; i++) {

            p1 = i;
            int sumToCompare = target - nums[i];

            if (i != (arrLength - 1)) {

                for (int j = i + 1; j < arrLength; j++) {
                    if (sumToCompare == nums[j]) {
                        p2 = j;
                        break;
                    }
                }

                if (p2 != 0) {
                    break;
                }

            } else {
                continue;
            }
        }
        
        int[] arr = new int[2];
        arr[0] = p1;
        arr[1] = p2;
        return arr;
    }
}