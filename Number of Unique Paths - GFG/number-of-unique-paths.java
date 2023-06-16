//{ Driver Code Starts
//Initial Template for Java

/*package whatever //do not write package name here */

import java.io.*;
import java.util.*;

class GFG {
	public static void main (String[] args) {
	    
	    //taking input using Scanner class
		Scanner sc=new Scanner(System.in);
		
		//taking total testcases
		int t=sc.nextInt();
		while(t-->0)
		{
		    //taking dimensions of the matrix
		    int a=sc.nextInt();
		    int b=sc.nextInt();
		    Solution ob = new Solution();
		    //calling method NumberOfPath()
		    System.out.println(ob.NumberOfPath(a,b));
		}
	}
}
// } Driver Code Ends


//User function Template for Java

class Solution
{
    //Function to find total number of unique paths.
    public static int NumberOfPath(int a, int b) 
    {
        int[][] dp = new int[a][b];
        
        return moveOnGrid(a-1,b-1,dp);
    }
    
    static int moveOnGrid(int m, int n,int[][] dp){

        if(m<0 || n < 0) return 0;
        if(dp[m][n]!=0) return dp[m][n];
        if(m==0 && n==0) return 1;

        int left = moveOnGrid(m,n-1,dp);
        int up = moveOnGrid(m-1,n,dp);

        return dp[m][n] = left + up;
    }
}