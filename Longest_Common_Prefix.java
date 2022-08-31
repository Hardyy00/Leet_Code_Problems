import java.util.Scanner;

class Solution {
    public String longestCommonPrefix(String[] strs) {

        String answer = "";

        int length = strs[0].length();

        for (int i = 0; i < length; i++) {

            char current = strs[0].charAt(i);
            boolean toAdd = true;

            for (int j = 1; j < strs.length; j++) {

                String temp = strs[j];

                if (i < temp.length()) {

                    if (current != temp.charAt(i)) {
                        toAdd = false;
                        break;
                    }
                } else {
                    toAdd = false;
                    break;
                }

            }

            if (!toAdd) {
                return answer;
            }

            answer += current;
        }

        return answer;

    }
}