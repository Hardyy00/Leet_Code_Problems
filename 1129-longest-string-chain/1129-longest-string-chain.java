class Solution {
    public int longestStrChain(String[] words) {
        
        // using the concept of lengest increasing sequence
        // we know that every suceessor will be length+1 of current character and the successor will only
        // have 1 diiferent character (if it has more than 1 different characters then it cannot be 
        // a successor)

        // Tabulation
        // TC : O(N^2)*k + O(N * LogN)      (k is average length of all the strings) (*k due to isPredecessors() function)
        // SC : O(N)

        // always calculating the at each index, the longest string chain with the current string 
        // as its last element
        // if it can then including the whole sequence of it predecessor

        // sorting in terms of length
        Arrays.sort(words, new Comparator<String>() {

            @Override
            public int compare(String s1, String s2){
                return s1.length() - s2.length();
            }
        });

        int n = words.length;
        int[] dp = new int[n];

        Arrays.fill(dp, 1);

        int maxLen = 0;

        for(int i=0;i<n;i++){

            for(int j=0;j<i;j++){
                
                // if it can be successor only then comparing it
                if((words[i].length() == words[j].length()+1) && isPredecessor(words[j], words[i])
                    && 1 + dp[j] > dp[i]){
                    
                    dp[i] = 1 + dp[j];
                }
            }


            maxLen = Math.max(maxLen, dp[i]);

        }

        return maxLen;
         
    }

    boolean isPredecessor(String s1, String s2){

        // if(s2.length()-s1.length()!=1) return false;
        // s1 is predecessor and s2 is successor

        // if we have only one different character then its okay, otherwise false
        int i =0 , j=0;
        int diff = 0;

        while(i<s1.length() && j < s2.length()){

            // if the current character is not equal , then going to next character of it
            if(s1.charAt(i)!=s2.charAt(j)){
                j++;
                diff++;
            }else{
                i++;
                j++;
            }

            if(diff>1) return false;
        }

        // if we didnn't find any diff. character, then the remaining character is gonna be an extra
        return true;
    }
}