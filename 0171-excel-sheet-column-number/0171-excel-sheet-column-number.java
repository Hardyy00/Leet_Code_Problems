class Solution {
    public int titleToNumber(String columnTitle) {

        // suppose the case : ZAY
        // for the y calculate the index => y % 26 = 25
        // how we have AY, the A part must have repeated for 26 times, and the Y was added
        // number = (A % 26) * 26  + Y % 26
        /// how we have ZAY , the Z part must have repeat for about (Z % 26 => 26) * 26 * 26
        // times 

        // hence on going from right to left the power of 26 increases

        // TC : O(N)
        // SC : O(1)
    

        int power = 1;

        int ans = 0;

        for(int i=columnTitle.length()-1;i>=0;i--){

            int code = code(columnTitle.charAt(i));
            int index = code % 26 == 0 ? 26 : code % 26;


            ans += index * power;
            power *= 26;
        }

        return ans;
    }

    private int code(char ch){
        return ch-'A' + 1;
    }
}