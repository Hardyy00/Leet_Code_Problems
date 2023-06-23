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
            int n=sc.nextInt();
            int d=sc.nextInt();
            
            int []arr=new int[n];
            for(int i=0;i<n;i++){
                arr[i]=sc.nextInt();
            }
            
            Solution obj=new Solution();
            int res=obj.countPartitions(n, d, arr);
            System.out.println(res);
        }
    }
}
// } Driver Code Ends


//User function Template for Java

//Back-end complete function Template for Java

class Solution{
    
    int mod = (int)1e9 + 7;
    public int countPartitions(int n, int d, int arr[]){
        
        int sum= 0;
        
        for(int i : arr) sum += i;
        
        if((sum+d)%2!=0) return 0;
        
        int target = (sum + d)/2;
        
        int[][] dp = new int[n][target+1];
        
        for(int[] row : dp) Arrays.fill(row,-1);
        // System.out.println("p");
        
        return countSubsetsWithTargetSum1(n-1,target,arr,dp);
    }
    
    int countSubsetsWithTargetSum1(int index, int target, int[] arr, int[][] dp){
        
        if(dp[index][target]!=-1) return dp[index][target];
        if(index==0){
            
            if(arr[0]==0 && target==0) return 2;
            if(arr[0]==target || target==0 ) return 1;
            
            return 0;
        }
        
        
        int notTake = countSubsetsWithTargetSum1(index-1,target,arr,dp);
        
        int take = 0;
        if(arr[index]<=target){
            take = countSubsetsWithTargetSum1(index-1,target-arr[index],arr,dp);
        }
        
        return dp[index][target]= (take + notTake)%mod;
    }
    
}