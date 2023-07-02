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
	    
	    
	    int[][] dp = new int[n][m];
	    
	    for(int[] row : dp) Arrays.fill(row, -1);
	    
	    int lcs = lcs(n-1,m-1,str1,str2,dp);
	    
	    return n + m - (2*lcs);
	} 
	
	int lcs(int index1, int index2, String s1, String s2,int[][] dp){
	    
	    if(index1< 0 || index2<0) return 0;
	    
	    if(dp[index1][index2]!=-1) return dp[index1][index2];
	    
	    if(s1.charAt(index1)==s2.charAt(index2)) return 1+lcs(index1-1,index2-1,s1,s2,dp);
	    
	    
	    return dp[index1][index2]= Math.max( lcs(index1-1,index2,s1,s2,dp) , lcs(index1,index2-1,s1,s2,dp));
	}
}