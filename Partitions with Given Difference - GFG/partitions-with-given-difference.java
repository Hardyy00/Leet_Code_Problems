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
        
        // int[][] dp = new int[n][target+1];
        
        // for(int[] row : dp) Arrays.fill(row,-1);
        // System.out.println("p");
        
        // return countSubsetsWithTargetSum1(n-1,target,arr,dp);
        // return countSubsetsWithTargetSum2(n,arr,target);
        
        return countSubsetsWithTargetSum3(n,arr,target);
    }
    
    int countSubsetsWithTargetSum3(int n, int[] arr, int target){
        
       int[] pre = new int[target+1];
       
       if(arr[0]==0) pre[0] = 2;
       else pre[0] = 1;
       
       if(arr[0]!=0 && arr[0]<=target) pre[arr[0]] = 1;
       
       for(int i=1;i<n;i++){
           
           int[] curr = new int[target+1];
           for(int tar = 0;tar<=target;tar++){
               
               int notTake = pre[tar];
               
               int take = 0;
               if(arr[i]<=tar){
                   take = pre[tar-arr[i]];
               }
               
               curr[tar] = (take + notTake)%mod;
           }
           
           pre = curr;
       }
       
       return pre[target];
        
    }
    
    int countSubsetsWithTargetSum2(int n, int[] arr, int target){
        
       int[][] dp = new int[n][target+1];
       
       if(arr[0]==0) dp[0][0] = 2;
       else dp[0][0] = 1;
       
       if(arr[0]!=0 && arr[0]<=target) dp[0][arr[0]] = 1;
       
       for(int i=1;i<n;i++){
           
           for(int tar = 0;tar<=target;tar++){
               
               int notTake = dp[i-1][tar];
               
               int take = 0;
               if(arr[i]<=tar){
                   take = dp[i-1][tar-arr[i]];
               }
               
               dp[i][tar] = (take + notTake)%mod;
           }
       }
       
       return dp[n-1][target];
        
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