//{ Driver Code Starts
import java.io.*;
import java.util.*;

class GFG
{
    public static void main(String args[])throws IOException
    {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(read.readLine());
        while(t-- > 0)
        {
            int N = Integer.parseInt(read.readLine());
            String input_line[] = read.readLine().trim().split("\\s+");
            int arr[]= new int[N];
            for(int i = 0; i < N; i++)
                arr[i] = Integer.parseInt(input_line[i]);
            int sum = Integer.parseInt(read.readLine());

            Solution ob = new Solution();
            if(ob.isSubsetSum(N, arr, sum))
            System.out.println(1);
            else
            System.out.println(0);

            
        }
    }
}

// } Driver Code Ends


//User function Template for Java

class Solution{


    static Boolean isSubsetSum(int n, int arr[], int sum){
        
        int[][] dp = new int[n][sum+1];
        
        for(int[] row : dp) Arrays.fill(row,-1);
        
        return findTargetSubset1(n-1,sum,arr,dp)==1;
    }
    
    static int findTargetSubset1(int index, int target, int[] arr, int[][] dp){
        
        if(target==0) return 1;
        if(dp[index][target]!=-1) return dp[index][target];
        
        if(index==0) return arr[0]==target ? 1 : 0;
        
        
        if(arr[index]<=target){
            
            if(findTargetSubset1(index-1,target-arr[index],arr,dp)==1) return 1;
        }
        
        if(findTargetSubset1(index-1,target,arr,dp)==1) return 1;
        
        return dp[index][target]=0;
    }
}