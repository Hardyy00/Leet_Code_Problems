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
                    int n = sc.nextInt();
                    int sum = sc.nextInt();
                    int arr[] = new int[n];
                    for(int i = 0;i<n;i++)
                    arr[i] = sc.nextInt();
                    
                    Solution ob = new Solution();
                    System.out.println(ob.perfectSum(arr,n,sum));
                }
        }
}    
// } Driver Code Ends


class Solution{
    
    int mod = (int)1e9 + 7;
	public int perfectSum(int arr[],int n, int sum) 
	{ 
	    
	   // int[][] dp= new int[n][sum+1];
	    
	   // for(int[] row : dp) Arrays.fill(row,-1);
	   // return countSubsetsWithSum1(n-1,arr,sum,dp);
	    
	   // return countSubsetsWithSum2(n,arr,sum);
	   
	   return countSubsetsWithSum3(n,arr,sum);
	    
	    
	}
	
	int countSubsetsWithSum3(int n, int[] arr, int sum){

        // Tabulation
        // TC : O(N*SUM) && SC : O(N*SUM)

        int[] pre = new int[sum+1];
//        int[] curr = new int[sum+1];

        if(arr[0]==0) pre[0]=2;       // if num[0] is 0 , then 2 cases exist , including 0 and excluding zero
        else pre[0]= 1;           // only 1 case exist , excluding num[0]

        if(arr[0]!=0 && arr[0]<=sum) pre[arr[0]] =1;

        for(int i=1;i<n;i++){
            int[] curr = new int[sum+1];
            for(int target = 0;target<=sum;target++){

                int notTake = pre[target];

                int take = 0;

                if(arr[i]<=target){
                    take = pre[target-arr[i]];
                }

                curr[target] = (take + notTake)%mod;
            }

            pre = curr;
        }

        return pre[sum];
    }
	
	int countSubsetsWithSum2(int n, int[] nums, int sum){
	    
	    int[][] dp = new int[n][sum+1];
	    
	    if(nums[0]==0) dp[0][0] = 2;
	    else dp[0][0] = 1;
	    
	    if(nums[0]!=0 && nums[0]<=sum) dp[0][nums[0]] = 1;
	    
	    for(int i=1;i<n;i++){
	        
	        for(int target = 0; target<=sum;target++){
	            
	            int notTake = dp[i-1][target];
	            
	            int take = 0;
	            
	            if(nums[i]<=target){
	                take = dp[i-1][target-nums[i]];
	            }
	            
	            dp[i][target] = (take + notTake)%mod;
	        }
	        
	        
	    }
	    
	   return dp[n-1][sum];
	}
	
	
	int countSubsetsWithSum1(int index, int[] arr, int target, int[][] dp){

        if(dp[index][target]!=-1) return dp[index][target];

        if(index==0){

            if(arr[0]==0 && target==0) return 2;
            if(arr[0]==target || target==0) return 1;

            return 0;
        }

        int take = 0;
        if(arr[index]<=target){
            take = countSubsetsWithSum1(index-1,arr,target-arr[index],dp);
        }

        int notTake = countSubsetsWithSum1(index-1,arr,target,dp);

        return dp[index][target] = (take + notTake)%mod;

    }

}