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
        
        int[][] dp = new int[n][n+1];
        for(int[] row : dp) Arrays.fill(row,-1);
        
        return cutTheRod1(n-1,n,price,dp);
    }
    
    static int cutTheRod1(int index, int rodLength,int[] prices, int[][] dp){


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