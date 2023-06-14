//{ Driver Code Starts
//Initial Template for Java

import java.util.*;
import java.lang.*;
import java.io.*;

class GFG{
    public static void main(String args[]) throws IOException{
        Scanner sc=new Scanner(System.in);
        int t=sc.nextInt();
        while(t-->0){
            int N=sc.nextInt();
            int []arr=new int[N];
            for(int i=0;i<N;i++){
                arr[i]=sc.nextInt();
                
            }
            Solution obj=new Solution();
            int res=obj.minimumEnergy(arr,N);
            System.out.println(res);
            
        }
    }
}


// } Driver Code Ends


//User function Template for Java

class Solution{
    public int minimumEnergy(int arr[],int n){
        
        int[] dp = new int[n];
        
        Arrays.fill(dp,-1);
        
        return geekJump(n-1,arr,dp);
        
    }
    
    int geekJump(int n,int[] arr,int[] dp){
        
        if(n==0) return 0;
        
        if(dp[n]!=-1) return dp[n];
        
        
        int moveOne = geekJump(n-1,arr,dp) + Math.abs(arr[n]-arr[n-1]);
        int moveTwo = Integer.MAX_VALUE;
        
        if(n>1) moveTwo = geekJump(n-2,arr,dp) + Math.abs(arr[n]-arr[n-2]);
        
        return dp[n] = Math.min(moveOne,moveTwo);
        
    }
}