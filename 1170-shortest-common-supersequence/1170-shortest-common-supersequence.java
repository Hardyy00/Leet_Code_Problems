class Solution {
    public String shortestCommonSupersequence(String str1, String str2) {
        
        return scs(str1,str2);
        
    }

    String scs(String s1, String s2){

        // Idea : since we have to find a supersequence, in which both the strings occur as subsequence
        // we will take the longest common subsequence of both the strings , since that will be common
        // in the supersequence (in order to minimize that) and try to make a supersequence 
        // by taking that into consideration
        // basically just tracing back to the path from which we found the lcs
        // cuz in that path we will be able to find different characters as well as same characters

        // Tabulation
        // TC : O(N*M) + O(N+M)
        // SC : O(N*M)

        int n = s1.length();
        int m = s2.length();

        int[][] dp = new int[n+1][m+1];

        for(int i=1;i<=n;i++){

            for(int j=1;j<=m;j++){

                if(s1.charAt(i-1)==s2.charAt(j-1)) dp[i][j] = 1+ dp[i-1][j-1];

                else dp[i][j] = Math.max(dp[i-1][j] , dp[i][j-1]);
            }
        }


        StringBuilder build = new StringBuilder();

        int i = n , j = m;

        while(i>0 && j>0){

            // if characters are equal then just add it once , and move diagonally
            if(s1.charAt(i-1)==s2.charAt(j-1)){

                build.append(s1.charAt(i-1));
                i--;
                j--;
            
            // if the characters are not equal , then go the maximum length index, if going upward
            // then we will never be able to get the character at current i , so before moving juust add
            // it and move to i--
            }else if(dp[i-1][j] > dp[i][j-1]){
                build.append(s1.charAt(i-1));
                i--;
            }else{
                // on going backward , we won't  be able to find the current character at current j ,
                // so just add it
                build.append(s2.charAt(j-1));
                j--;
            }
        }
    
        // if we finish at an index like , (2,0) , that means s2 length is finished (-1 index) &&
        // s1's  2 characters are remaining so just add them 
        while(i>0){
            build.append(s1.charAt(i-1));
            i--;
        }

        // same case it s2
        while(j>0){
            build.append(s2.charAt(j-1));
            j--;
        }

        // since we moved from backward direction, reverse it
        return build.reverse().toString();
    }


    
}