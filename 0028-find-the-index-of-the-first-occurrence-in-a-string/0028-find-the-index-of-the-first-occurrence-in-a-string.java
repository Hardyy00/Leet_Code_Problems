class Solution {
    public int strStr(String haystack, String needle) {

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