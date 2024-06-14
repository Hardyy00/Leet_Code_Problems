class Solution {
    public int minimumDeletions(String s) {

        //dp stores number of chars to remove to make s.substring(0, i) valid

        int n = s.length();
        int[] dp = new int[n+1];
        int countB = 0;

        for(int i=0;i<n;i++){

            if(s.charAt(i)=='a'){

                  //case 1: keep current a. ==> prev chars must be a...a
                //so need to remove all 'b' chars before i, which is bcount
                
                //case 2: remove current a ==> prev chars must be a...ab...b
                //so need to remove current a and whatever makes substring before current i valid which is dp[i];

                dp[i+1] = Math.min(countB, dp[i]+1);
            }else{
                 //since it is always valid to append 'b' if substring before current i is valid, so just copy whatever makes substring before i valid which is dp[i];
                dp[i+1] = dp[i];
                countB++;
            }

        }
        
        return dp[n];
    }
}