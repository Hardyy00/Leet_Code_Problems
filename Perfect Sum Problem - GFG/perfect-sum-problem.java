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
	    
	    int[][] dp= new int[n][sum+1];
	    
	    for(int[] row : dp) Arrays.fill(row,-1);
	    return countSubsetsWithSum2(n-1,arr,sum,dp);
	} 
	
	int countSubsetsWithSum2(int index, int[] arr, int target, int[][] dp){

        if(dp[index][target]!=-1) return dp[index][target];

        if(index==0){

            if(arr[0]==0 && target==0) return 2;
            if(arr[0]==target || target==0) return 1;

            return 0;
        }

        int take = 0;
        if(arr[index]<=target){
            take = countSubsetsWithSum2(index-1,arr,target-arr[index],dp);
        }

        int notTake = countSubsetsWithSum2(index-1,arr,target,dp);

        return dp[index][target] = (take + notTake)%mod;

    }

}