//{ Driver Code Starts
import java.io.*;
import java.util.*;

class RodCutting {

    public static void main(String args[])throws IOException {
        BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out=new PrintWriter(System.out);
        
        int t = Integer.parseInt(in.readLine().trim());
        while (t-- > 0) {
            int n = Integer.parseInt(in.readLine().trim());
            String s[]=in.readLine().trim().split(" ");
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(s[i]);

            Solution ob = new Solution();
            out.println(ob.cutRod(arr, n));
        }
        out.close();
    }
}

// } Driver Code Ends


class Solution{
    public int cutRod(int price[], int n) {
        
        // int[][] dp = new int[n][n+1];
        // for(int[] row : dp) Arrays.fill(row,-1);
        
        // return cutTheRod1(n-1,n,price,dp);
        
         return cutTheRod2(n,price);
        
        
    }
    
     static int cutTheRod2(int n, int[] prices){
         
         // Tabulation
         // TC : O(N*N) 
         // SC : O(N*N)

        int[][] dp = new int[n][n+1];

        for(int i=0;i<=n;i++){
            dp[0][i] = i*prices[0];
        }

        for(int i=1;i<n;i++){

            for(int len= 0; len<=n;len++){

                int notCut = dp[i-1][len];

                int cut = 0;

                if(i+1<=len){
                    cut = prices[i] + dp[i][len-(i+1)];
                }

                dp[i][len] = Math.max(cut,notCut);

            }
        }

        return dp[n-1][n];
    }
    
    static int cutTheRod1(int index, int rodLength,int[] prices, int[][] dp){
        
        
        // Memoization
        // TC : O(N*N)
        // sc : O(N*N) + O(N)


       if(dp[index][rodLength]!=-1) return dp[index][rodLength];
        if(index==0){

            return rodLength*prices[0];
        }

        int notCut = cutTheRod1(index-1,rodLength,prices,dp);

        int cut = 0;

        if(index+1<=rodLength){
            cut = prices[index] + cutTheRod1(index,rodLength-(index+1),prices,dp);
        }


        return dp[index][rodLength]= Math.max(cut, notCut);
    }
}