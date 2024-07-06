class Solution {
    public boolean repeatedSubstringPattern(String s) {
        
        // return solve(s);

        return solve2(s);
    }

    private boolean solve2(String s){

        // using kmp, lps[n-1], gives the length of the string apart from pattern length (if it exists)
        // to get pattern length, n - lps[n-1]
        // if lps[n-1] == 0, return false, and if pattern length divides the whole string return true

        // TC : O(N)
        // SC : O(N)

        int n = s.length();
        int[] lps = genLPS(s);


        int remLen = n - lps[n-1];

        if(lps[n-1] == 0){
            return false;
        }

        return remLen > 0 &&  n % remLen == 0;



    }

    private int[] genLPS(String s){

        int n= s.length();
        int[] lps = new int[n];
        int i=1,len = 0;

        while(i < n){

            if(s.charAt(i)==s.charAt(len)){
                len++;
                lps[i] = len;
                i++;
            }else{

                if(len > 0){
                    len = lps[len-1];
                }else{
                    i++;
                }
            }
        }

        return lps;
    }

    private boolean solve(String s){

        // TC : O(2N) very very near to 2N , as the loop is breaking if the pattern doesn't match
        // SC : O(1)

        // checkign for all possible substring length sice a substring can atmost be of length
        // len/2 (cuz len/2 + len/2 = len , i.e atleast two substring are required

        for(int i=1;i<=s.length()/2;i++){

            boolean allEqual = true;  // if all substrings are equal , then pattern exists 
            int a = 0, b = i;

            // if the the string length is a multiple of len for which we are finding substrings
            if(s.length()%i==0){
                while(b<s.length()){

                    if(s.charAt(a) != s.charAt(b)){ 
                        allEqual = false;
                        break;
                    }
                    a++;
                    b++;
                }

                if ( allEqual ) return true;
            }
        }

        return false;
    }
}