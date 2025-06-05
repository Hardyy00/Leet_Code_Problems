class Solution {
    public int minDistance(String word1, String word2) {
        

        int n = word1.length();
        int m = word2.length();

        // return editDistance(n-1, m-1, word1, word2);

        // int[][] dp = new int[n][m];

        // for(int[] row : dp) Arrays.fill(row, -1);

        // return editDistance2(n-1, m-1, word1, word2, dp);

        // return editDistance3(n, m, word1, word2);

        return editDistance4(n, m, word1, word2);
    }

    int editDistance4(int n, int m , String s1, String s2){

        // Space Optimisation
        // TC : O(N*M)
        // SC : O(M)

        int[] pre = new int[m+1];
        int[] curr = new int[m+1];

        for(int i=0;i<=m;i++) pre[i] = i;
        
        pre[0] = 0;


        for(int i=1;i<=n;i++){
            
            curr[0] = i;

            for(int j=1;j<=m;j++){

                if(s1.charAt(i-1)==s2.charAt(j-1)) curr[j] = pre[j-1];

                else{

                    int insert = 1 + curr[j-1];

                    int remove = 1 + pre[j];
                    
                    int replace = 1 + pre[j-1];

                    curr[j] = Math.min(insert , Math.min(remove , replace));
                }
            }

            int[] temp = pre;
            pre = curr;
            curr = temp;
        }

        return pre[m];
    }

    int editDistance3(int n, int m , String s1, String s2){

        // Tabulation
        // TC : O(N*M)
        // SC : O(N*M)

        int[][] dp = new int[n+1][m+1];

        // BASE CASES
        // whenver the index1 becomes zero , return the remaining chacaters of string 2 (since its 1-based
        // indexing return i ) (because we have to insert the remaining characters)
        for(int i=0;i<=m;i++) dp[0][i] = i;
        
        // whenver the index2 becomes zero , return the remaining chacaters of string 1 (since its 1-based
        // indexing return i ) (because we have to dellete the remaining characters)
        for(int i=0;i<=n;i++) dp[i][0] = i;


        for(int i=1;i<=n;i++){

            for(int j=1;j<=m;j++){

                if(s1.charAt(i-1)==s2.charAt(j-1)) dp[i][j] = dp[i-1][j-1];
                else{

                    int insert = 1 + dp[i][j-1];

                    int remove = 1 + dp[i-1][j];
                    
                    int replace = 1 + dp[i-1][j-1];

                    dp[i][j] = Math.min(insert , Math.min(remove , replace));
                }
            }
        }

        return dp[n][m];
    }

    int editDistance2(int index1, int index2, String s1, String s2,int[][] dp){

        // Memoization
        // TC : O(N*M)
        // SC : O(N*M) + O(N+M)

        
        if(index1<0) return index2+1;

        if(index2<0) return index1+1;

        if(dp[index1][index2]!=-1) return dp[index1][index2];

        if(s1.charAt(index1)==s2.charAt(index2)) return editDistance2(index1-1, index2-1, s1,s2, dp);

        else{

            int insert = 1 + editDistance2(index1, index2-1, s1,s2, dp);

            int remove = 1 + editDistance2(index1-1 , index2, s1 ,s2, dp);

            int replace = 1 + editDistance2(index1-1 , index2-1 ,s1, s2, dp);

            return dp[index1][index2] = Math.min(insert , Math.min(remove, replace));
        }
    }

    int editDistance(int index1, int index2, String s1, String s2){

        // Recursion
        // TC : O(3^N * 3*M) (just say exponential)
        // SC : O(N+M)

        // if the string 1 , becomes empty just insert remaining charcters of string2 , to make it equal
         // to string 2
        if(index1<0) return index2+1;

        // if string 2 is finished, that means i was able to make some part of the string 1 equal to 
        //string2 , so now just delete all the remaining characters of string 1
        if(index2<0) return index1+1;

         // if characters match , no need to do anything , just shorten that string
        if(s1.charAt(index1)==s2.charAt(index2)) return editDistance(index1-1, index2-1, s1,s2);

        else{

            // since you are inserting a character in string1 , i remains at the same postion
            // cuz you inserted in the front , and since the inserted character and the string 2
            // character matched , index2 move back by 1, and index1 will remain same
            int insert = 1 + editDistance(index1, index2-1, s1,s2);

            // on remove a string1 character , move index1 backward
            int remove = 1 + editDistance(index1-1 , index2, s1 ,s2);

            // on replacing a character in s1, move both the indexes , since we matched the current characters
            int replace = 1 + editDistance(index1-1 , index2-1 ,s1, s2);

            return Math.min(insert , Math.min(remove, replace));
        }
    }
}