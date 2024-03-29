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
        
        // return knapsack2(n,w,val,wt);
        
        // return knapsack3(n,w,val,wt);
        
        return knapsack4(n,w,val,wt);
        
        
    }
    
    static int knapsack4(int n, int w, int[] values, int[] weights){

        // Space Optimised      (single array)
        // TC : O(N*W) && SC : O(W)

        // as for every row we are using the previous row, but for  calculation of every element we are , using either the element value or element's left side value
        // pre[w-weight[index]] (value is coming from left side of the current index), so if we start calculation from right side, it won't affect, cuz we are using the
        // previous values and not the current values, and since solving from right side doesn't affect the answers of previous index, so for  the calculation
        // they won't be overwritten , hence a single array can be used for calculation , if solved from right to left side, (but in left to right side, the prvious
        // answers would be overwritten ,so they will give a wrong answer)

        int[] pre = new int[w+1];

        for(int i=weights[0];i<=w;i++){     // only for calls of weight >= weight[0] return value[0]
            pre[i] = values[0];
        }

        for(int index=1; index<n;index++){

            for(int weight = w; weight>=0;weight--){

                int take = 0;
                if(weights[index]<=weight){
                    take = pre[weight-weights[index]] + values[index];
                }

                int notTake = pre[weight];

                pre[weight] = Math.max(take,notTake);
            }
        }

        return pre[w];

    }
    
    static int knapsack3(int n, int w, int[] values, int[] weights){

        // Space Optimised
        // TC : O(N*W) && SC : O(W)

        int[] pre = new int[w+1];

        for(int i=weights[0];i<=w;i++){     // only for calls of weight >= weight[0] return value[0]
            pre[i] = values[0];
        }

        for(int index=1; index<n;index++){
            int[] curr = new int[w+1];

            for(int weight = 0; weight<=w;weight++){

                int take = 0;
                if(weights[index]<=weight){
                    take = pre[weight-weights[index]] + values[index];
                }

                int notTake = pre[weight];

                curr[weight] = Math.max(take,notTake);
            }

            pre = curr;
        }

        return pre[w];

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


