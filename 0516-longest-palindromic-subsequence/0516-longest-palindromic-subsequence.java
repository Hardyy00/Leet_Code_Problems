class Solution {
    public int longestPalindromeSubseq(String s) {

        String s2 = new StringBuilder(s).reverse().toString();

        int n = s.length();

        // int[][] dp = new int[n][n];

        // for(int[] row : dp) Arrays.fill(row,-1);

        // return lcs(n-1,n-1,s,s2,dp);

        // return lcs2(n,s,s2);

        return lcs3(n,s,s2);
        
    }

    int lcs3(int n,String s1,String s2){

        // Space Optimised
        // TC : O(N*N)
        // SC : O(N)

        int[] pre = new int[n+1];
        int[] curr = new int[n+1];

        for(int i=1;i<=n;i++){

            for(int j=1;j<=n;j++){

                if(s1.charAt(i-1)==s2.charAt(j-1)) curr[j] = 1+pre[j-1];
                else curr[j] = Math.max(pre[j] , curr[j-1]);
            }

            int[] temp = pre;
            pre = curr;
            curr = temp;
        }

        return pre[n];
    }

    int lcs2(int n,String s1,String s2){

        // Tabulation
        // TC : O(N*N)
        // SC : O(N*N)

        int[][] dp = new int[n+1][n+1];

        for(int i=1;i<=n;i++){

            for(int j=1;j<=n;j++){

                if(s1.charAt(i-1)==s2.charAt(j-1)) dp[i][j] = 1+dp[i-1][j-1];
                else dp[i][j] = Math.max(dp[i-1][j] , dp[i][j-1]);
            }
        }

        return dp[n][n];
    }

    int lcs(int index1,int index2,String s1,String s2,int[][] dp){

        // Memoization
        // TC : O(N*N)
        // SC : O(N*N) + O(N+N)

        if(index1<0 || index2<0) return 0;

        if(dp[index1][index2]!=-1) return dp[index1][index2];


        if(s1.charAt(index1)==s2.charAt(index2)) return 1+ lcs(index1-1,index2-1,s1,s2,dp);

        
        return dp[index1][index2] = Math.max(lcs(index1-1,index2,s1,s2,dp), lcs(index1,index2-1,s1,s2,dp));

        
    }
}