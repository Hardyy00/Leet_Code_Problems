class Solution {
    class Pair{
        int first = -1;
        int second = -1;
    }
    public int countPalindromicSubsequence(String s) {

        // for a palindrome  of length 3, aba, the first and last character must be the same
        // between them can be any number, so the number of palindrome must be equal to 
        // the number of character between the first and last occurence of the a
        // we need only unqiue character other we will make duplicates
        // to do that , we keep track of very character at each index
        // like frequency of a at index 0, frequency of b at index 1 etc
        // and the finding number of unqiue character between the range and adding 
        // it to the answer

        // TC : O(N * 26)
        // SC : O(26) + O(26 * N)

        int n = s.length();
        Pair[] alphs = new Pair[26];

        for(int i=0;i<n;i++){
            // to get the first and last positon of a character
            if(alphs[s.charAt(i)-'a'] == null) alphs[s.charAt(i)-'a'] = new Pair();

            if(alphs[s.charAt(i)-'a'].first == -1) alphs[s.charAt(i)-'a'].first = i;
            else alphs[s.charAt(i)-'a'].second = i;
        }

        /// frequency of each character at each index  
        int[][] fre = new int[26][n];

        fre[s.charAt(0)-'a'][0]++;

        for(int k=1;k<n;k++){

            // to update the frequencies
            for(int i=0;i<26;i++){
                
                // if we are at current character in the string , incrment the frequency
                if((char)(97+i) == s.charAt(k)){
                    fre[i][k] = 1 + fre[i][k-1];
                }else{
                    fre[i][k] = fre[i][k-1];  // otherwise tje frequency remains the same
                }
            }
        }

        int ans = 0;

        for(Pair p : alphs) {

            if(p == null) continue;
            if(p.second == -1 || (p.second - p.first == 1)) continue;


            int  uniques = 0;

            for(int i=0;i<26;i++){

                // find frequency in the range
                int tot = fre[i][p.second-1];
                int pre = fre[i][p.first];

                // if the character exists in the range the it a unique character
                if(tot-pre >0) uniques++;
            }

            ans += uniques;   
        }

        return ans;

        
    }
}