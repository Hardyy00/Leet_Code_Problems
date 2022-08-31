
class Solution {
    public int romanToInt(String s) {

        String romanNumber = s;

        int integer = 0;
        int length = romanNumber.length();

        for (int i = 0; i < length; i++) {

            char numeral = romanNumber.charAt(i);
            int current = getInteger(numeral);

            if (i < (length - 1)) {
                char nextNumeral = romanNumber.charAt(i + 1);

                int next = getInteger(nextNumeral);

                if (current < next)
                    integer += -current;
                else
                    integer += current;
            } else {
                integer += current;
            }

        }

        return integer;

    }

    private int getInteger(char numeral) {

        switch (numeral) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
        }
        return -1;
    }
}