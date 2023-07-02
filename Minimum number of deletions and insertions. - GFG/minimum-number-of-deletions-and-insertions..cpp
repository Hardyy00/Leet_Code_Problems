//{ Driver Code Starts
#include <bits/stdc++.h>
using namespace std;

// } Driver Code Ends
class Solution{
		

	public:
	int minOperations(string str1, string str2) 
	{ 
	   int n = str1.size();
	   int m = str2.size();
	   
	   vector<vector<int>> dp(n , vector<int>(m , -1));
	   
	   //int maxLCS =  lcs(n-1,m-1,str1,str2,dp);
	   
	   //return n+m -(2* maxLCS);
	   
	   //int maxLCS =  lcs2(n,m,str1,str2);
	   
	   //return n+m -(2* maxLCS);
	   
	   int maxLCS =  lcs3(n,m,str1,str2);
	   
	   return n+m -(2* maxLCS);
	    
	} 
	
	int lcs3(int n, int m, string &s1, string &s2){
	    
	    // Space Optimise
	    // TC : O(N*M)
	    // SC : O(M)
	    
	    vector<int> pre(m+1 , 0);
	    vector<int> curr(m+1 , 0);
	    
	    for(int i=1;i<=n;i++){
	        
	        for(int j=1;j<=m;j++){
	            
	            if(s1[i-1]==s2[j-1]) curr[j] = 1 + pre[j-1];
	            else curr[j] = max( pre[j] , curr[j-1]);
	        }
	        
	        pre = curr;
	    }
	    
	    return pre[m];
	}
	
	int lcs2(int n, int m, string &s1, string &s2){
	    
	    // Tabulation
	    // TC : O(N*M)
	    // SC : O(N*M)
	    
	    vector<vector<int>> dp(n+1, vector<int>(m+1 , 0));
	    
	    for(int i=1;i<=n;i++){
	        
	        for(int j=1;j<=m;j++){
	            
	            if(s1[i-1]==s2[j-1]) dp[i][j] = 1 + dp[i-1][j-1];
	            else dp[i][j] = max( dp[i-1][j] , dp[i][j-1]);
	        }
	    }
	    
	    return dp[n][m];
	}
	
	int lcs(int index1, int index2, string &s1, string &s2, vector<vector<int>> &dp){
	    
	    // Memoization
	    // TC : O(N*M)
	    // SC : O(N*M) + O(N+M)
	    
	    
	    
	    if(index1<0 || index2<0) return 0;
	    
	    if(dp[index1][index2]!=-1) return dp[index1][index2];
	    
	    
	    if(s1[index1]==s2[index2]) return 1 + lcs(index1-1, index2-1,s1,s2,dp);
	    
	    
	    return dp[index1][index2] = max( lcs(index1-1,index2, s1,s2, dp) , lcs(index1, index2-1, s1,s2,dp));
	}
};

//{ Driver Code Starts.
int main() 
{
   	
   
   	int t;
    cin >> t;
    while (t--)
    {
        string s1, s2;
        cin >> s1 >> s2;

	    Solution ob;
	    cout << ob.minOperations(s1, s2) << "\n";
	     
    }
    return 0;
}


// } Driver Code Ends