class Solution {
    public int maxRepeating(String sequence, String word) {

        // concat : word  + "#" + sequence
        // in the lps,
        // the concat words are at the regular intervals of word.length() in the lps array
        // so if lps[i]==m , start checking i +m , indexes , to see if they also have lps[i] =m
        // if yes count them

        // TC : O(N + M)
        // SC : O(N + M)

        String conc = word + "#" + sequence;
        int m = word.length();
        int[] lps = genLPS(conc);
        int i=word.length();

        // System.out.println(Arrays.toString(lps));

        int k = 0;
       while(i < conc.length()){

            if(lps[i] == m){

                int cn = 1;

                while(i + m < conc.length() && lps[i + m] == m){
                    i +=m;
                    cn++;
                }

                k = Math.max(k, cn);
            }

            i++;
       }

       return k;


        
    }

    private int[] genLPS(String s){

        int n= s.length();
        int[] lps= new int[n];
        int i=1, len = 0;

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
}