//{ Driver Code Starts
#include<bits/stdc++.h>
using namespace std;

// } Driver Code Ends
class Solution{
    public:
    
    int longestCommonSubstr (string S1, string S2, int n, int m)
    {
    //   lcs(S1,S2);
       
       lcs2(S1,S2);
    }
    
    int lcs2(string &s1,string &s2){
        
        // Space Optimised
        // TC : O(N*M)
        // SC : O(M)
        
        int n = s1.size() , m = s2.size();
        
        vector<int> pre(m+1,0);
        vector<int> curr(m+1,0);
        
        int maxLen = 0;
        
        for(int i=1;i<=n;i++){
            
            for(int j=1;j<=m;j++){
                
                if(s1[i-1]==s2[j-1]){
                    
                    curr[j] = 1 + pre[j-1];
                    
                    maxLen = max(maxLen, curr[j]);
                    
                }else{
                    curr[j] = 0;
                }
            }
            
            pre = curr;
            
        }
        
        return maxLen;
    }
    
    int lcs(string &s1,string &s2){
        
        // Tabulation
        // TC : O(N*M)
        // SC : O(N*M)
        
        int n = s1.size() , m = s2.size();
        
        vector<vector<int>> dp(n+1, vector<int>(m+1 , 0));
        
        int maxLen = 0;
        
        for(int i=1;i<=n;i++){
            
            for(int j=1;j<=m;j++){
                
                if(s1[i-1]==s2[j-1]){
                    
                    dp[i][j] = 1 + dp[i-1][j-1];
                    
                    maxLen = max(maxLen, dp[i][j]);
                    
                }
            }
            
        }
        
        return maxLen;
    }
};

//{ Driver Code Starts.

int main()
{
    int t; cin >> t;
    while (t--)
    {
        int n, m; cin >> n >> m;
        string s1, s2;
        cin >> s1 >> s2;
        Solution ob;

        cout << ob.longestCommonSubstr (s1, s2, n, m) << endl;
    }
}
// Contributed By: Pranay Bansal

// } Driver Code Ends