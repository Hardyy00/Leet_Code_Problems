class Solution {
    public boolean rotateString(String s, String goal) {

        if(s.length() != goal.length()){
            return false;
        }

        if(s.equals(goal)){
            return true;
        }


        int[] lps = generateLPS(s + "#" + goal);

        int[] reverseLPS = generateLPS(reverse(s) + "#" + reverse(goal));

        // System.out.println(Arrays.toString(lps));
        // System.out.println(Arrays.toString(reverseLPS));

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