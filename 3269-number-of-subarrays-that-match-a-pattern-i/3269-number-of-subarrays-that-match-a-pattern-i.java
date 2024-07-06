class Solution {
    public int countMatchingSubarrays(int[] nums, int[] pat) {

        StringBuilder pattern = new StringBuilder(), text = new StringBuilder();

        int n = nums.length, m = pat.length;

        for(int i=0;i<m;i++){

            pattern.append(map(pat[i]));
        }

        for(int i=0;i<n-1;i++){

            if(nums[i] < nums[i+1]){
                
                text.append('G');
            }else if(nums[i]==nums[i+1]){
                text.append('E');
            }else{
                text.append('S');
            }
        }
        n--;


        int[] lps = genLPS(pattern);

        // System.out.println(Arrays.toString(lps) + "\n" + text + "\n" + pattern);
        int i=0,j =0;

        int cn =0;

        while(i < n && j < m){

            if(text.charAt(i)==pattern.charAt(j)){
                i++;
                j++;
            }

            if(j==m){
                cn++;
                j = lps[j-1];
                // System.out.println()
            }

            if(i < n  && text.charAt(i) != pattern.charAt(j)){

                if(j > 0){
                    j = lps[j-1];
                }else{
                    i++;
                }
            }
        }
        
        return cn;
    }

    private int[] genLPS(StringBuilder sb){

        int i = 1, len = 0, n = sb.length();

        int[] lps = new int[n];

        while(i < n){

            if(sb.charAt(i)==sb.charAt(len)){
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

    private char map(int num){

        if(num==1){
            return 'G';
        }else if(num == 0){
            return 'E';
        }

        return 'S';
    }
}