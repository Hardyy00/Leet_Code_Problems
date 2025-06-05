class Solution {
    public int numDistinct(String s, String t) {

        int n = s.length();
        int m = t.length();

        // int[][] dp = new int[n][m];

        // for(int[] row : dp) Arrays.fill(row,-1);

        // return distinctSubsequences1(n-1 , m-1, s, t, dp);

        // return distinctSubsequences2(n , m, s, t);

        // return distinctSubsequences3(n , m, s, t);

        return distinctSubsequences4(n , m, s, t);
        
    }

    int distinctSubsequences4(int n, int m, String s1, String s2){

        // Space Optimised
        // TC : O(N*M)
        // SC : O(M) (1 array)

        int[] pre = new int[m+1];

        // for any value of index1 , if index2==0 (index2<0) return 1
        pre[0]= 1;

        for(int i=1;i<=n;i++){

            for(int j=m;j>=1;j--){
                
                int match = 0;
                if(s1.charAt(i-1)==s2.charAt(j-1)){
                    match = pre[j-1];
                }

                int notMatch = pre[j];

                pre[j] = match + notMatch;
            }

        }

        return pre[m];
    }

    int distinctSubsequences3(int n, int m, String s1, String s2){

        // Space Optimised
        // TC : O(N*M)
        // SC : O(M)

        int[] pre = new int[m+1];
        int[] curr = new int[m+1];

        // for any value of index1 , if index2==0 (index2<0) return 1
        pre[0]= curr[0] = 1;

        for(int i=1;i<=n;i++){


            for(int j=1;j<=m;j++){
                
                int match = 0;
                if(s1.charAt(i-1)==s2.charAt(j-1)){
                    match = pre[j-1];
                }

                int notMatch = pre[j];

                curr[j] = match + notMatch;
            }

            int[] temp = curr;
            curr = pre;
            pre = temp;
        }

        return pre[m];
    }

    int distinctSubsequences2(int n, int m, String s1, String s2){

        // Tabulation
        // TC : O(N*M)
        // SC : O(N*M)

        int[][] dp = new int[n+1][m+1];

        // for any value of index1 , if index2==0 (index2<0) return 1
        for(int i=0;i<=n;i++) dp[i][0] = 1;

        for(int i=1;i<=n;i++){

            for(int j=1;j<=m;j++){
                
                int match = 0;
                if(s1.charAt(i-1)==s2.charAt(j-1)){
                    match = dp[i-1][j-1];
                }

                int notMatch = dp[i-1][j];

                dp[i][j] = match + notMatch;
            }
        }

        return dp[n][m];
    }

    int distinctSubsequences1(int index1, int index2, String s1, String s2, int[][] dp){

        // Memoization
        // TC : O(N*M)
        // SC : O(N*M) + O(N+M)

        // if index2<0 , then we have finished the string2 , that means we have found it
        if(index2< 0) return 1;

        // if index1 < 0 , that means , we finishe the string1 but wasn't able to find the string2
        if(index1 < 0) return 0;

        if(dp[index1][index2]!=-1) return dp[index1][index2];


        int count1 = 0;
        // IF the current characters match, then reduce the string2 and try to find the rest of the string 2
        // , in rest of the string1
        if(s1.charAt(index1)==s2.charAt(index2)){
            count1 = distinctSubsequences1(index1-1, index2-1,s1,s2,dp);
        }

        // even if the characters are equal , do not take them , instead try to in the string2 in rest of
        // the remaining string 1
        // this happpens even if the characters do not match , so the only option we have is to find the 
        //string2 in rest of the string1
        int count2 = distinctSubsequences1(index1-1, index2, s1, s2, dp);

        return dp[index1][index2] = count1 + count2;

    }
}