class Solution {
    public boolean checkPartitioning(String s) {

        int n = s.length();
        
        boolean[][] isPalindrome = new boolean[n][n];

        findPalindrome(n, s, isPalindrome);

        int[][] dp = new int[n][4];

        for(int[] row : dp) Arrays.fill(row, -1);

        return solve(0,3, s, dp, isPalindrome)==1;
        
    }

    int solve(int index, int k, String s, int[][] dp, boolean[][] isPalindrome){

        // Memoization
        // TC : O(N*4) + O(N*N)
        // SC : O(N*N) + O(N*4) + O(N)

        if(index==s.length()){

            return k==0 ? 1 : 0;
        }

        if(k<=0) return 0;

        if(dp[index][k]!=-1) return dp[index][k];

        int ans = 0;

        for(int i=index; i<s.length();i++){

            if(isPalindrome[index][i]){

                int part = solve(i+1, k-1,s, dp, isPalindrome);

                if(part==1) ans = 1;
            }
        }

        return dp[index][k] = ans;
    }

    

    void findPalindrome(int n, String s, boolean[][] dp){

        // TC : O(N*N)
        // SC : O(1)

        for(int i=0;i<n;i++){

            for(int j=i;j>=0;j--){

                if(i-j<=1) dp[j][i] = s.charAt(i)==s.charAt(j);

                else if(s.charAt(i)==s.charAt(j)) dp[j][i] = dp[j+1][i-1];
            }
        }
    }
}