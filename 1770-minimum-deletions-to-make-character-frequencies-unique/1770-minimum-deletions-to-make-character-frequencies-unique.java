class Solution {
    public int minDeletions(String s) {
        
        if(s.length() == 1) return 0;

        int[] fre = new int[26];
        int diff = 0;

        for(int i=0;i<s.length();i++){

            char ch = s.charAt(i);
            fre[ch-'a']++;

            if(fre[ch-'a'] == 1) diff++;
        }

        Arrays.sort(fre);

        int lastIndex = 26 - diff;
        int ans = 0;

        // System.out.println(Arrays.toString(fre) + " " + lastIndex + " " + diff);

        for(int i=24;i>=lastIndex;i--){

            if(fre[i] >= fre[i+1] && fre[i+1] != 0){
                int freToBe = fre[i+1] -1;
                int del = fre[i] - freToBe;
                fre[i] = freToBe;
                ans += del;
            }else if( fre[i+1] == 0){
                ans += fre[i];
                fre[i] = 0;
            }

            // System.out.println(i + " " + ans);
        }

        return ans;
    
    }
}