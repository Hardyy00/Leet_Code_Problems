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
        
        // int[][] dp = new int[n][sum+1];
        
        // for(int[] row : dp) Arrays.fill(row,-1);
        
        // return findTargetSubset1(n-1,sum,arr,dp)==1;
        
        return findTargetSubset3(n,sum,arr);
        
    }
    
    static boolean findTargetSubset3(int n, int sum, int[] arr){
        
        boolean[] pre = new boolean[sum+1];
        
        
        pre[0] = true;
        
        
        
        if(arr[0]<=sum) pre[arr[0]] = true;
        
        
        for(int i=1;i<n;i++){
            boolean[] help = new boolean[sum+1];
            help[0] = true;
            
            for(int target=1;target<=sum;target++){
                
                boolean pick = false;
                if(arr[i]<=target){
                    pick = pre[target-arr[i]];
                }
                
                boolean notPick = pre[target];
                
                help[target] = pick || notPick;
            }
            
            pre = help;
        }
        
        return pre[sum];
    }
    
    static boolean findTargetSubset2(int n, int sum, int[] arr){
        
        boolean[][] dp = new boolean[n][sum+1];
        
        for(int i=0;i<n;i++) dp[i][0] =true;
        
        if(arr[0]<=sum)
            dp[0][arr[0]] = true;
        
        
        for(int i=1;i<n;i++){
            
            for(int target=1;target<=sum;target++){
                
                boolean pick = false;
                if(arr[i]<=target){
                    pick = dp[i-1][target-arr[i]];
                }
                
                boolean notPick = dp[i-1][target];
                
                dp[i][target] = pick || notPick;
            }
        }
        
        return dp[n-1][sum];
    }
    
    static int findTargetSubset1(int index, int target, int[] arr, int[][] dp){
        
        // TC : O(N*target) && SC : O(N*target) + O(N)
        
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