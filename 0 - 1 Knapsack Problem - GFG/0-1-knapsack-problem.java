//{ Driver Code Starts
import java.util.*;
import java.io.*;
import java.lang.*;

class gfg
{
    public static void main(String args[])throws IOException
    {
        //reading input using BufferedReader class
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        
        //reading total testcases
        int t = Integer.parseInt(read.readLine());
        
        while(t-- > 0)
        {
            //reading number of elements and weight
            int n = Integer.parseInt(read.readLine());
            int w = Integer.parseInt(read.readLine());
            
            int val[] = new int[n];
            int wt[] = new int[n];
            
            String st[] = read.readLine().trim().split("\\s+");
            
            //inserting the values
            for(int i = 0; i < n; i++)
              val[i] = Integer.parseInt(st[i]);
             
            String s[] = read.readLine().trim().split("\\s+"); 
            
            //inserting the weigths
            for(int i = 0; i < n; i++)
              wt[i] = Integer.parseInt(s[i]);
              
            //calling method knapSack() of class Knapsack
            System.out.println(new Solution().knapSack(w, wt, val, n));
        }
    }
}




// } Driver Code Ends


class Solution 
{ 
    //Function to return max value that can be put in knapsack of capacity W.
    static int knapSack(int w, int wt[], int val[], int n) 
    { 
         
        // int[][] dp = new int[n][w+1];
        
        // for(int[] row : dp) Arrays.fill(row,-1);
        
        // return knapsack1(n-1,w,val,wt,dp);
        
        return knapsack2(n,w,val,wt);
    }
    
    static int knapsack2(int n, int w, int[] values, int[] weights){

        int[][] dp = new int[n][w+1];
        
        for(int i=weights[0];i<=w;i++){
            dp[0][i] = values[0];
        }

        for(int index=1; index<n;index++){

            for(int weight = 0; weight<=w;weight++){

                int take = 0;
                if(weights[index]<=weight){
                    take = dp[index-1][weight-weights[index]] + values[index];
                }

                int notTake = dp[index-1][weight];

                dp[index][weight] = Math.max(take,notTake);
            }
        }

        return dp[n-1][w];

    }
    
    static int knapsack1(int index, int target,int[] values, int[] weight, int[][] dp){

        if(dp[index][target]!=-1) return dp[index][target];
        if(index==0){
            if(weight[0]<=target) return values[0];
            return 0;
        }

        int take = 0;
        if(weight[index]<=target){
            take = knapsack1(index-1,target-weight[index],values,weight,dp) + values[index];
        }

        int notTake = knapsack1(index-1,target,values,weight,dp);

        return dp[index][target] = Math.max(take,notTake);

    }
    
    
}


