class Solution {
    public int numberOfSubstrings(String s) {

        int[] fre = new int[3];

        int si = 0, ei =0;

        int count = 0;

        while(ei < s.length()){

            char ch = s.charAt(ei);

            fre[ch-'a']++;

            while(fre[s.charAt(si)-'a'] > 1){
                fre[s.charAt(si)-'a']--;
                si++;
            }

            // while(fre[0] > 1 || fre[1] > 1 || fre[2] > 1){


            //     if(fre[s.charAt(si)-'a']==1){
            //         break;
            //     }

            //     fre[s.charAt(si)-'a']--;
            //     si++;
            // }

            if(fre[0]>=1 && fre[1] >=1 && fre[2] >=1){

                count += si+1;
            }

            ei++;
        }

        return count;
    }
}