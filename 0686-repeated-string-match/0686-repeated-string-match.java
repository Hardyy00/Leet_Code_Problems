class Solution {
    public int repeatedStringMatch(String a, String b) {

        StringBuilder sb =new StringBuilder();

        sb.append(a);
        int count = 1;

        while( sb.length() < b.length()){

            sb.append(a);
            count++;

        }

        if(contains(sb,b)){
            return count;
        }

        sb.append(a);

        if(contains(sb,b)){
            return count+1;
        }

        return -1;

        

        
    }

    private boolean contains(StringBuilder sb, String b){

        int[] lps = buildLPS(b);

        int i=0;
        int j = 0;
        while(i < sb.length() && j < b.length()){

            if(sb.charAt(i)==b.charAt(j)){
                i++;
                j++;
            }

            if(j==b.length()){
                return true;
            }

            if(i < sb.length() && sb.charAt(i) != b.charAt(j)){

                if(j > 0){
                    j = lps[j-1];
                }else{
                    i++;
                }
            }
        }

        return false;
    }

    private int[] buildLPS(String s){

        int n = s.length();
        int[] lps =new int[n];
        int len = 0;

        int i=1;

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