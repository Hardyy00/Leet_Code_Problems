class Solution {
    public int minCut(String s) {

        // -1 because we at making a partition in the last, where there is no need to
        // return solve(0,s)-1;

        int n = s.length();
        // to get of the palindromes in the range, since we have call to find palindrome b/w
        // 0 to 5 , 1 to 7, or 0 to n-1, etc, we have the matrix of n*n , i can vary from 0 to n-1
        // and j is similary

        boolean[][] palindromeDP = new boolean[n][n];       // TC : O(N^2) (MAKES THE isPalindrome call
                                                            // in the recursion of constant time)

        fillPalindromes(palindromeDP, s, n);

        // int[] dp = new int[n];
        // Arrays.fill(dp, -1);
        
        // return solve2(0, s,dp,palindromeDP) -1 ;

        return solve3(n, s, palindromeDP)-1;

        

    }

    int solve3(int n, String s, boolean[][] isPalindrome){

        // Tabulation
        // TC : O(N*N) + O(N*N)
        // SC : O(N*N) + O(N)

        int[] dp = new int[n+1];

        for(int i=n-1;i>=0;i--){

            int mini = Integer.MAX_VALUE;

            for(int j=i;j<n;j++){

                if(isPalindrome[i][j]){
                    int parts = 1+dp[j+1];

                    mini = Math.min(mini, parts);
                }
            }
            
            dp[i] = mini; 
        }

        return dp[0];
    }

    int solve2(int index, String s, int[] dp, boolean[][] isPalindrome){

        // Memoization
        // TC : O(N*N) + O(N*N) (isPalindrome is of constant time, AND fillPalindromes take N^2)
        // SC : O(N*N) + O(N) + O(N)

        if(index==s.length()) return 0;

        if(dp[index]!=-1) return dp[index];

        int mini = Integer.MAX_VALUE;

        for(int i=index;i<s.length();i++){
            
            if(isPalindrome[index][i]){
                int parts = 1 + solve2(i+1, s, dp, isPalindrome);
                mini = Math.min(mini, parts);
            }

        }

        return dp[index] = mini;
    }

    void fillPalindromes(boolean[][] palindromeDP, String s, int n){

        // Precomputing all the palindromes there are in the string
        // Tc : O(N^2)
        // SC : O(1)

        for(int i=0;i<n;i++){

            for(int j=i;j>=0;j--){

                // single characater is always palindrome
                if(i-j+1==1) palindromeDP[i][j] = true;
                else if(i-j+1==2 && s.charAt(i)==s.charAt(j)) palindromeDP[j][i] = true;

                else if(s.charAt(i)==s.charAt(j) && palindromeDP[j+1][i-1]) palindromeDP[j][i] = true;
            }
        }
    }

    int solve(int index, String s){

        // using front partition
        // Recursion
        // TC : O(Exponential)
        // SC : O(N)

        if(index==s.length()) return 0;

        int mini =  Integer.MAX_VALUE;
        String temp = "";

        for(int i=index;i<s.length();i++){

            temp += s.charAt(i);
            if(isPalindrome(temp)){

                int parts = 1+ solve(i+1,s);
                mini = Math.min(mini, parts);
            }
        }

        return mini;
    }

    boolean isPalindrome(String s){

        int i=0;
        int j = s.length()-1;

        while(i<j){

            if(s.charAt(i)!=s.charAt(j)) return false;

            i++;
            j--;
        }

        return true;
    }
}