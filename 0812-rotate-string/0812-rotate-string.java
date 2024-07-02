class Solution {
    public boolean rotateString(String s, String goal) {

        // return solve(s, goal);

        return solve2(s, goal);
        
        
    }

    private boolean solve2(String s, String goal){

        if(s.length() != goal.length()){
            return false;
        }

        if(s.equals(goal)){
            return true;
        }

        String text = goal.repeat(2);

        int[] lps = generateLPS(s);

        int n = text.length(), m = s.length();

        int i=0, j=0;

        while(i < n && j < m){

            if(text.charAt(i)== s.charAt(j)){
                i++;
                j++;
            }

            if(j==m){
                return true;
            }

            if(i < n && text.charAt(i) != s.charAt(j) ){

                if(j > 0){
                    j= lps[j-1];
                }else{
                    i++;
                }
            }
        }

        return false;


    }

    private boolean solve1(String s, String goal){

        // a reverse string , cdeab and original string abcde, clearing the suffix ab matches with prefix
        // ab in original string (so some part of suffix will match with some prefix)
        // and for the rest , reverse both strings , bcedc and edcba , here also some suffix of goal 
        // matches with prefix of original , hence if any index lps[i] + reverseLps[i]== goal.length

        // return true


        // TC : O(N + M)
        // SC : O(N + M)

        if(s.length() != goal.length()){
            return false;
        }

        if(s.equals(goal)){
            return true;
        }


        int[] lps = generateLPS(s + "#" + goal);

        int[] reverseLPS = generateLPS(reverse(s) + "#" + reverse(goal));

        for(int i=0;i<lps.length;i++){

            if(lps[i] + reverseLPS[i]==s.length()){
                return true;
            }
        }


        return false;
    }

    private int[] generateLPS(String s){

        int n = s.length();
        int[] lps = new int[n];

        int i=1, len = 0;

        while(i < n){

            if(s.charAt(i)==s.charAt(len)){
                len++;
                lps[i]= len;
                i++;
            }else {

                if(len > 0){
                    len = lps[len-1];
                }else{
                    i++;
                }
            }
        }

        return lps;
    }

    private String reverse(String s){

        int n= s.length();
        char[] ch = s.toCharArray();

        int i=0, j= n-1;

        while(i < j){

            char temp = ch[i];
            ch[i] = ch[j];
            ch[j] = temp;
            i++;
            j--;
        }

        return new String(ch);
    }


}