//{ Driver Code Starts
//Initial Template for Java

import java.io.*;
import java.util.*;

class GFG
{
    public static void main(String args[])throws IOException
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0){
            int N = Integer.parseInt(in.readLine());
            String input_line[] = in.readLine().trim().split("\\s+");
            int arr[]= new int[N];
            for(int i = 0; i < N; i++)
                arr[i] = Integer.parseInt(input_line[i]);
            
            Solution ob = new Solution();
            System.out.println(ob.matrixMultiplication(N, arr));
        }
    }
}

// } Driver Code Ends


//User function Template for Java

class Solution{
    static int matrixMultiplication(int n, int arr[])
    {
        
        // since for a matrix dimensions are at i and i-1 , so we start with 1
        // return solve(1, n-1, arr);
        
        // int[][] dp = new int[n][n];
        
        // for(int[] row : dp) Arrays.fill(row,-1);
        
        // return solve2(1,n-1,arr, dp);
        
        return solve3(n, arr);
    }
    
    static int solve3(int n, int[] arr){
        
        int[][] dp = new int[n][n];
        
        for(int start=n-1;start>=1;start--){
            
            // so that we do not come across base case when i==j , we do end = start+1, 
            for(int end=start+1;end<n;end++){
                
                int mini = Integer.MAX_VALUE;
                for(int i=start;i<=end-1;i++){
                    
                    int multi = arr[start-1] * arr[i] * arr[end] + dp[start][i] + dp[i+1][end];
                    
                    mini = Math.min(mini, multi);
                    
                }
                
                dp[start][end] = mini;
            }
        }
        
        return dp[1][n-1];
    }
    
    static int solve2(int start, int end, int[] arr, int[][] dp ){
            
        // Memoizaion
        // TC : O(N*N*N)    (*N CUZ we have loop)
        // SC : O(N*N) + O(N)
        if(start==end) return 0;
        
        if(dp[start][end]!=-1) return dp[start][end];
        
        
        int mini = Integer.MAX_VALUE;
        
        for(int i=start;i<=end-1;i++){
            
            
            int multi = arr[start-1] * arr[i] * arr[end] + solve2(start,i, arr, dp) + solve2(i+1, end, arr, dp);
            
            mini = Math.min(mini, multi);
        }
        
        return dp[start][end] = mini;
    }
    
    static int solve(int start ,int end , int[] arr){
        
        // Applying partition DP
        // Recursion
        // TC : Exponential
        // SC : O(N)
        
        // ONLY one matrix is remaining , in this case, so it will have 0 multiplication
        if(start==end) return 0;
            
        int mini = Integer.MAX_VALUE;
        
        // we partition is every time, and find the min multiplication from both the end
        // end-1, cuz we need atleast 1 matrix in another partition
        for(int i=start;i<=end-1;i++){
            
            
            // when we partition, suppose we partition like this, A   BCD , then and A'S dimensions are 10 * 20
            // and after solveing BCD in any way we will have a dimension of 20 * D'S Columns (cuz at a patirtion columns are same as row)
            // and therefore we calculate that before even going inside the partition
            
            //  start's rows * partition point * end matrix's column || partition at i,k and k+1, j
            int multi = arr[start-1] * arr[i] * arr[end] + solve(start, i, arr) + solve(i+1, end, arr);  
            
            mini = Math.min(mini, multi);
        }
        
        
        return mini;
    }
}