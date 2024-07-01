class Solution {
    public List<String> stringMatching(String[] words) {

        List<String> ans = new ArrayList<>();

        int n = words.length;

        for(int i=0;i<n;i++){

            int[] lps = generateLPS(words[i]);

            for(int j=0;j<n ;j++){

                if(i==j){
                    continue;
                }



                if(matchPattern(lps,words[i], words[j])){

                    ans.add(words[i]);
                    break;
                }
            }
        }

        return ans;
        
    }

    private boolean matchPattern(int[] lps, String pattern, String text){

        int i=0, j= 0;
        int m = pattern.length(), n = text.length();

        while(i < n && j < m){

            if(text.charAt(i)==pattern.charAt(j)){
                i++;
                j++;
            }

            if(j==m){
                return true;
            }

            if(i < n && text.charAt(i) != pattern.charAt(j)){

                if(j > 0){
                    j = lps[j-1];
                }else{
                    i++;
                }
            }
        }

        return false;
    }

    private int[] generateLPS(String word){

        int n = word.length();
        int[] lps = new int[n];
        int i = 1, len = 0;

        while( i < n){

            if(word.charAt(i)==word.charAt(len)){
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

