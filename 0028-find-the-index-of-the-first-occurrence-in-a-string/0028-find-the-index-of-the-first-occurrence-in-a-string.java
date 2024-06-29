class Solution {
    public int strStr(String haystack, String needle) {

        
        // solve(haystack, needle);

        return solve2(haystack, needle);
        
    }

    private int solve2(String haystack, String needle){

        if(needle.length() > haystack.length()){
            return -1;
        }

        int[] lps = buildLPS(needle);

        int i=0, j=0;

        while(i < haystack.length() && j < needle.length()){

            if(haystack.charAt(i)==needle.charAt(j)){
                i++;
                j++;
            }

            if(j==needle.length()){
                return i-needle.length();
            }

            if(i < haystack.length() && j < needle.length() && haystack.charAt(i)!=needle.charAt(j) ){

                if(j > 0){
                    j = lps[j-1];
                }else{
                    i++;
                }

            }
        }

        return -1;
    }

    private int[] buildLPS(String s){

        int n= s.length();
        int[] lps = new int[n];
        int i=1, len =0;

        while(i < n){

            if(s.charAt(i)==s.charAt(len)){
                len++;
                lps[i] = len;
                i++;
            }
            
            if(i < n && s.charAt(i) != s.charAt(len)){

                if(len > 0){
                    len = lps[len-1];
                }else{
                    i++;
                }
            }
        }

        return lps;
    }

    private int solve(String haystack, String needle){

        // using kmp
        // TC : O(n + M)
        //SC : O(N + M)

         String s = needle + "$" + haystack;
        int n= s.length();
        int[] lps = new int[n];

        int len = 0, i =1 ;

        while(i < n){

            if(s.charAt(i)==s.charAt(len)){

                len++;
                lps[i] = len;
                i++;
            }



            if(i < n && s.charAt(i)!=s.charAt(len)){

                if(len > 0){
                    len = lps[len-1];
                }else{
                    i++;
                }
            }
        }

        for(i=0;i<n;i++){

            if(lps[i]==needle.length()){
                return i - 2 * needle.length();
            }
        }

        return -1;

    }
}