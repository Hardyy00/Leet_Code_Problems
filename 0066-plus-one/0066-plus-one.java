class Solution {
    public int[] plusOne(int[] digits) {

        int n = digits.length;

        if( digits[digits.length-1] < 9) {

            int[] ans= new int[n];

            for(int i=0;i<n;i++) ans[i] = digits[i];

            ans[n-1]++;

            return ans;
        }else{

            int pos = n-1;

            while(pos > -1 && digits[pos] == 9) pos--;

            if(pos==-1) {

                int[] ans = new int[n+1];

                ans[0] = 1;

                return ans;
            }else{

                int[] ans = new int[n];
                for(int i=0;i<=pos;i++){

                    ans[i] = digits[i];
                }

                ans[pos]++;

                return ans;
            }
        }
        
    }
}