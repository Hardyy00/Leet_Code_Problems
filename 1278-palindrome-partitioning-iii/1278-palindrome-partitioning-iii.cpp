class Solution {

    int maxValue = 1e6;
public:
    int palindromePartition(string s, int k) {

        // brute force : 1. simply do a recursion with , with a palindrome  function with tell
        // how many character to insert in string from index to i, to make it palindrom
        // TC : (Exponential) & SC : O(N)

        // 2. do a dp solution nut with same palindrome function
        // TC : O(N*K*N*N) (N*N for palindrome function in every recursion call)
        // sc : o(n*k) + o(k)

        int n = s.size();

        // for precomputing , for which ranges , we need to insert, how many characters
        vector<vector<int>> changeToPalindrome(n,vector<int>(n, 0));

        fillPalindrome(n, s, changeToPalindrome);

        // vector<vector<int>> dp(n, vector<int>(k+1, -1));

        // return solve2(0 , k, s, dp, changeToPalindrome);

        return solve3(n,k,s,changeToPalindrome);
        
    }


    int solve3(int n,int k, string &s, vector<vector<int>> &changeToPalindrome){

        // Tabulation
        // TC : O(N*K*N) + O(N*N) (for palindrome precomputtations)
        // SC : O(N*N) + O(N*K)  (N*N For changeToPalindrome matrix)

        vector<vector<int>> dp(n+1, vector<int>(k+1, 0));

        dp[n][0] = 0;

        // base cases || when k is anything but 0, and index == n  , return maxValue 
        for(int i=1;i<=k;i++){

            dp[n][i] = maxValue;
        }

        // when index < n && k==0 , return maxValue
        for(int i=0;i<n;i++){
            dp[i][0] = maxValue;
        }


        for(int i=n-1;i>=0;i--){

            for(int j=1;j<=k;j++){

                int mini =INT_MAX;


                for(int index=i;index<n;index++){

                    int chars = changeToPalindrome[i][index] + dp[index+1][j-1];

                    mini = min(mini, chars);
                }

                dp[i][j] = mini;
            }
        }

        return dp[0][k];
    }

    int solve2(int index, int k,string &s, vector<vector<int>> &dp, vector<vector<int>> &changeToPalindrome){

        // Memoization
        // TC : O(N*K*N) + O(N*N)
        // SC : O(N*N) + O(N*K) + O(N)

        // we have partitioned
        if(index==s.size()){
            
            // we have divided into k partitions , since in the last we are also partitioning , we take k==0
            if(k==0) return 0;

            return maxValue;
        }
        
        // if partitions are still remaining, then return a maxValue so that it won't get considered
        if(k<=0) return maxValue;

        if(dp[index][k]!=-1) return dp[index][k];

        int mini = INT_MAX; 

        for(int i=index;i<s.size();i++){

            // characters required to make substring from index to i, palindrom  + recursion call
            int chars = changeToPalindrome[index][i] + solve2(i+1, k-1,s, dp, changeToPalindrome);

            // decrease k since you have made a partition
            mini = min(mini,chars);

        }

        return dp[index][k] = mini;

    }

    void fillPalindrome(int n ,string &s, vector<vector<int>> &dp){

        // TC : O(N*N)
        // SC : O(1)

        // IF substring length is 0 or 1, if if characters are equal then 0 , otherwise change 1 character
        // if length >=1 , then dp[j][i] = dp[j+][i-1] (basically changes to be made in smaller substring)
        // if the ith and jth characters are not equal then increment by 1 also

        for(int i=0;i<n;i++){

            for(int j=i;j>=0;j--){

                if(i-j<=1){
                    dp[j][i] = !(s[i]==s[j]); // if equal then zero changes required else 1
                    continue;
                }

                dp[j][i] = dp[j+1][i-1];        // characters to change till j , i

                if(s[i]!=s[j]) dp[j][i]++;      // if ith  and jth char. are not same
            }
        }
    }
};