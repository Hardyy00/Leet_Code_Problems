//{ Driver Code Starts
//Initial Template for Java

import java.io.*;
import java.util.*;
import java.lang.*;

class GFG implements Runnable{
    public void run()
    {
        //everything that needs to be written in main() function should be here in run() method
        
        //we use try in order to catch IOExceptions becuase of using BufferedReader Class
    	try {
	        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	        int t = Integer.parseInt(in.readLine());
	        while(t-- > 0) {
	            int N = Integer.parseInt(in.readLine());
	            int cost[][] = new int[N][3];
	            for(int i = 0;i < N;i++) {
	                String a[] = in.readLine().trim().split("\\s+");
	                for(int j = 0;j < 3;j++)
	                    cost[i][j] = Integer.parseInt(a[j]);
	            }
	            
	            Solution ob = new Solution();
	            System.out.println(ob.minCost(N, cost));
	        }
        }
    	catch(Exception e) {
    		System.out.println("IOException");
    	}
    }
    public static void main(String[] args) throws IOException{
        //here increasing the stack size to increase the recursion limit
        //here 1 << 26 will create the stack size to 64MB that should be enough for problems
		new Thread(null, new GFG(), "whatever", 1<<26).start();
	}
}
// } Driver Code Ends


//User function Template for Java

class Solution{
    static int minCost(int n, int cost[][]) {
        
        int[][] dp = new int[n][3];
        
        for(int[] row : dp) Arrays.fill(row, -1);
        
        int min = Integer.MAX_VALUE;
        
        for(int i=0;i<3;i++){
            
            min= Math.min(min,cost[0][i] + solve(1, i, cost, dp, n));
        }
        
        return min;
        // code here
    }
    
    private static int solve(int row, int preCol, int[][] mat, int[][] dp, int n){

		if(row >=n) return 0; 

		
		if(row == n-1){

			int min = Integer.MAX_VALUE;
			
			for(int i=0;i<3;i++){

				if(i!=preCol) min = Math.min(min, mat[row][i]);
			}

			return min;
		}

		if(dp[row][preCol]!=-1) return dp[row][preCol];


		int min = Integer.MAX_VALUE;

		for(int i=0;i<3;i++){

			if(i!=preCol) min = Math.min(min, mat[row][i] + solve(row+1,i, mat,dp,n));
		}

		return dp[row][preCol] = min;
	}
}