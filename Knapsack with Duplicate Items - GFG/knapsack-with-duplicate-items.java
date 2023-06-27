//{ Driver Code Starts
//Initial Template for Java

import java.io.*;
import java.util.*;

class GFG{
    public static void main(String args[])throws IOException
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0){
            String line1[] = in.readLine().trim().split("\\s+");
            int N = Integer.parseInt(line1[0]);
            int W = Integer.parseInt(line1[1]);
            String line2[] = in.readLine().trim().split("\\s+");
            int val[] = new int[N];
            for(int i = 0;i < N;i++)
                val[i] = Integer.parseInt(line2[i]);
            String line3[] = in.readLine().trim().split("\\s+");
            int wt[] = new int[N];
            for(int i = 0;i < N;i++)
                wt[i] = Integer.parseInt(line3[i]);
                
            Solution ob = new Solution();
            System.out.println(ob.knapSack(N, W, val, wt));
        }
    }
}
// } Driver Code Ends


//User function Template for Java

class Solution{
    static int knapSack(int n, int w, int val[], int wt[])
    {
    
        // int[][] dp = new int[n][w+1];
        
        // for(int[] row : dp) Arrays.fill(row,-1);
        
        // return knapsack(n-1,w,val,wt,dp);
        
        return knapsack2(n,w,val,wt);
     }
     
     static int knapsack2(int n , int target,int[] val, int[] weight){

        // Tabulation
        // TC : O(N*TARGET)
        // SC : O(N*TARGET)


        int[][] dp = new int[n][target+1];

        //before i=weight[0] , it will yield 0 only
        for(int i=weight[0];i<=target;i++){
            dp[0][i] = (i / weight[0]) * val[0];
        }

        for(int i=1;i<n;i++){

            for(int tar=0;tar<=target;tar++){

                int notTake = dp[i-1][tar];

                int take = 0;
                if(weight[i]<=tar){
                    take = val[i] + dp[i][tar-weight[i]];
                }

                dp[i][tar] = Math.max(take, notTake);
            }
        }

        return dp[n-1][target];


    }
     
     static int knapsack(int index, int target, int[] val, int[] weight, int[][] dp){
        
        if(dp[index][target]!=-1) return dp[index][target];
        
        if(index==0){
            
            return (target/weight[0]) * val[0];
        }
        
        
        int notTake = knapsack(index-1,target,val,weight,dp);
        
        int take = 0;
        
        if(weight[index]<=target){
            take = val[index] + knapsack(index, target-weight[index], val, weight, dp);
        }
        
        return dp[index][target] = Math.max(take , notTake);
     }
     

}