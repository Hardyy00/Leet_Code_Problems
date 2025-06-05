class Solution {
    public int longestCommonSubsequence(String text1, String text2) {

        int n = text1.length();
        int m = text2.length();
        // int[][] dp = new int[n][m];

        // for(int[] row : dp) Arrays.fill(row,-1);

        // return lca(n-1,m-1,text1,text2,dp);

        // return lca2(n,m,text1,text2);

         return lca3(n,m,text1,text2);
        
    }

    int lca3(int n, int m, String s1,String s2){

        // Space Optimised
        // TC : O(N*M)
        // SC : O(M)

        int[] pre = new int[m+1];
        int[] curr = new int[m+1];


        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){

                if(s1.charAt(i-1)==s2.charAt(j-1)) curr[j] = 1+ pre[j-1];
                // not match
                else curr[j] = Math.max(pre[j] , curr[j-1]);
            }

            int[] temp = curr;
            curr = pre;
            pre = temp;
        }

        return pre[m];
    }

    int lca2(int n, int m,String s1, String s2){

        // Tabulation
        // TC : O(N*M)
        // SC : O(N*M)
        
        // using "shifting of index" techinque (considering index -1 case to be at index 0
        // and index n-1 to be at n)
        int[][] dp = new int[n+1][m+1];

        // base cases
        // for(int i=0;i<=n;i++) dp[i][0] = 0;
        // for(int j=0;j<=m;j++) dp[0][j] = 0;

        for(int i=1;i<=n;i++){

            for(int j=1;j<=m;j++){
                
                // match
                if(s1.charAt(i-1)==s2.charAt(j-1)) dp[i][j] = 1+ dp[i-1][j-1];
                // not match
                else dp[i][j] = Math.max(dp[i-1][j] , dp[i][j-1]);
            }
        }

        return dp[n][m];
    }

    int lca(int index1, int index2, String s1, String s2, int[][] dp){

        // Memoization
        // TC : O(N*M)
        // SC : O(N*M) + O(N+M) (recursion stack)

        if(index1<0 || index2<0) return 0;

        if(dp[index1][index2]!=-1) return dp[index1][index2];


        // Match
        if(s1.charAt(index1)==s2.charAt(index2)) return 1 + lca(index1-1,index2-1,s1,s2,dp);

        // not match, then place one and move another
        int a = lca(index1-1,index2,s1,s2,dp);
        int b = lca(index1,index2-1,s1,s2,dp);     

        // return subsequence of maximum length
        return dp[index1][index2] = Math.max(a,b);
    }
}