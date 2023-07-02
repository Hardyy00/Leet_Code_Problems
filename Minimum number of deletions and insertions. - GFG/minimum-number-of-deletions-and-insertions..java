//{ Driver Code Starts
//Initial Template for Java
import java.io.*;
import java.util.*;
class GfG
{
    public static void main(String args[])
        {
            Scanner sc = new Scanner(System.in);
            int t = sc.nextInt();
            while(t-->0)
                {
                    String s1 = sc.next();
                    String s2 = sc.next();
                    Solution ob = new Solution();
                    System.out.println(ob.minOperations(s1,s2));
                }
        }
}    
// } Driver Code Ends


//User function Template for Java

class Solution
{
	public int minOperations(String str1, String str2) 
	{ 
	    int n = str1.length();
	    int m = str2.length();
	    
	    
	   // int[][] dp = new int[n][m];
	    
	   // for(int[] row : dp) Arrays.fill(row, -1);
	    
	   // int lcs = lcs(n-1,m-1,str1,str2,dp);
	    
	   // return n + m - (2*lcs);
	   
	    int lcs = lcs2(n,m,str1,str2);
	    
	    return n + m - (2*lcs);
	   
	   
	} 
	
	int lcs2(int n, int m, String s1, String s2){
	    
	    // Tabulation
	    // TC : O(N*M)
	    // SC : O
	    int[][] dp = new int[n+1][m+1];
	    
	    for(int i=1;i<=n;i++){
	        
	        for(int j=1;j<=m;j++){
	            
	            if(s1.charAt(i-1)==s2.charAt(j-1)) dp[i][j] = 1 + dp[i-1][j-1];
	            else dp[i][j] = Math.max( dp[i-1][j] , dp[i][j-1]);
	        }
	    }
	    
	    return dp[n][m];
	}
	
	int lcs(int index1, int index2, String s1, String s2,int[][] dp){
	    
	    // IDEA : if i can perserve the longest commmom subsequence, then apart tfrom thatsubsequence i have to make insertions
	    // and deletions . Deletions will be str1.length - lcs and insertions will be str2.length - lcs, to make str1 equal to str2
	    
	    // Memoization
	    // TC : O(N*M)
	    // SC : O(N*M) + O(N+M)
	    
	    if(index1< 0 || index2<0) return 0;
	    
	    if(dp[index1][index2]!=-1) return dp[index1][index2];
	    
	    if(s1.charAt(index1)==s2.charAt(index2)) return 1+lcs(index1-1,index2-1,s1,s2,dp);
	    
	    
	    return dp[index1][index2]= Math.max( lcs(index1-1,index2,s1,s2,dp) , lcs(index1,index2-1,s1,s2,dp));
	}
}