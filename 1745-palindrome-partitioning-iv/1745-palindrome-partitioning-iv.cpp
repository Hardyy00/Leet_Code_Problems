class Solution {
public:
    bool checkPartitioning(string s) {

        int n = s.size();

        vector<vector<bool>> isPalindrome(n, vector<bool>(n , false));

        // PRECOMputing , for every substring i to j, is palindrome or not (so that this call becomes of
        // constant time in recursion )
        findPalindromes(n,s, isPalindrome);


        // vector<vector<int>> dp(n,vector<int>(4, -1));

        // we are making 3 partitions (3 parts , with one partition at the most end)
        // return solve(0,3,s,dp,isPalindrome);

        return solve2(n, 3, s, isPalindrome);
        
    }

    bool solve2(int n, int k, string &s, vector<vector<bool>> &isPalindrome){

        // Tabulation
        // TC : O(N*3) + o(n*n)
        // SC : O(N*4) + O(N*N)

        vector<vector<bool>> dp(n+1, vector<bool>(4,false));

        dp[n][0] = true;

        for(int i=n-1;i>=0;i--){

            for(int j=1;j<=k;j++){

                bool ans = false;

                for(int index=i;index<n;index++){

                    if(isPalindrome[i][index]){

                        bool part = dp[index+1][j-1];

                        ans = ans || part;
                    }
                }

                dp[i][j] = ans;
            }
        }

        return dp[0][k];
    }

    bool solve(int index, int k, string &s, vector<vector<int>> &dp , vector<vector<bool>> &isPalindrome){

        // Memoization
        // TC : O(N*3) + O(N*N)
        // SC : O(N*N) + O(N*4) + O(N)

        if(index==s.size()){
            return k==0;
        }

        // we have made more then k partition , so return false
        if(k<=0) return false;

        if(dp[index][k]!=-1) return dp[index][k];

        bool ans = false;

        for(int i=index; i<s.size();i++){
            
            // is the substring is palindrome,only then make a partition
            if(isPalindrome[index][i]){

                bool part = solve(i+1, k-1,s,dp,isPalindrome);

                ans = ans || part;
            }
        }

        return dp[index][k] = ans;
    }

    void findPalindromes(int n, string &s, vector<vector<bool>> &dp){

        // TC : O(N*N)
        // SC : O(1)

        for(int i=0;i<n;i++){

            for(int j=i;j>=0;j--){

                if(i-j<=1) dp[j][i] = s[i]==s[j];
                
                else if(s[i]==s[j]) dp[j][i] = dp[j+1][i-1];
            }
        }
    }
};