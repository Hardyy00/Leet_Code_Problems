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
            int N = Integer.parseInt(in.readLine());
            String S = in.readLine();
            
            Solution ob = new Solution();
            System.out.println(ob.countWays(N, S));
        }
    }
}
// } Driver Code Ends

class Solution{
    
    static int mod = 1003;
    
    static int countWays(int n, String s){
        
        // return solve(0,n-1,1, s);
        
        int[][][] dp = new int[n][n][2];
        
        for(int[][] mat : dp){
            
            for(int[] row : mat) Arrays.fill(row, -1);
        }
        
        return solve2(0,n-1,1,s,dp);
    }
    
    static int solve2(int i, int j,int isTrue, String s, int[][][] dp){
        
        if(i>j) return 0;
        
        if(i==j){
            
            if(isTrue==1) return s.charAt(i)=='T' ? 1 : 0;
            
            return s.charAt(i)=='F' ? 1 : 0;
        }
        
        if(dp[i][j][isTrue]!=-1) return dp[i][j][isTrue];
        
        int ways = 0;
        
        for(int k=i+1;k<=j-1;k+=2){
            
            int leftTrue = solve2(i,k-1,1,s, dp);
            int leftFalse = solve2(i,k-1,0,s, dp);
            
            int rightTrue = solve2(k+1, j, 1,s, dp);
            int rightFalse = solve2(k+1, j, 0,s, dp);
            
            char sign = s.charAt(k);
            
            if(sign=='&'){
                
                if(isTrue==1){
                    ways += (leftTrue * rightTrue)%mod;
                }else{
                    
                    ways += (leftTrue * rightFalse)%mod + (leftFalse * rightTrue)%mod + (leftFalse*rightFalse)%mod;
                }
                
            }else if(sign=='|'){
                
                if(isTrue==1){
                    ways += (leftTrue * rightTrue)%mod + (leftTrue * rightFalse)%mod + (leftFalse * rightTrue)%mod;
                }else{
                    
                    ways += (leftFalse * rightFalse)%mod;
                }
            }else{
                
                // T^T = F , F*F = F , T^F= T , F^T = T
                
                if(isTrue==1){
                    
                    ways += (leftTrue * rightFalse)%mod + (leftFalse * rightTrue)%mod;
                }else{
                    
                    ways += (leftTrue * rightTrue)%mod + (leftFalse * rightFalse)%mod;
                }
            }
            
            
        }
        
        return dp[i][j][isTrue] = ways%mod;
    }
    
    static int solve(int i, int j,int isTrue, String s){
        
        // Recursion
        //  TC : Exponential
        // SC : O(N)
        
        if(i>j) return 0;
        
        if(i==j){
            
            if(isTrue==1) return s.charAt(i)=='T' ? 1 : 0;
            
            return s.charAt(i)=='F' ? 1 : 0;
        }
        
        int ways = 0;
        
        for(int k=i+1;k<=j-1;k+=2){
            
            int leftTrue = solve(i,k-1,1,s);
            int leftFalse = solve(i,k-1,0,s);
            
            int rightTrue = solve(k+1, j, 1,s);
            int rightFalse = solve(k+1, j, 0,s);
            
            char sign = s.charAt(k);
            
            if(sign=='&'){
                
                if(isTrue==1){
                    ways += (leftTrue * rightTrue)%mod;
                }else{
                    
                    ways += (leftTrue * rightFalse)%mod + (leftFalse * rightTrue)%mod + (leftFalse*rightFalse)%mod;
                }
                
            }else if(sign=='|'){
                
                if(isTrue==1){
                    ways += (leftTrue * rightTrue)%mod + (leftTrue * rightFalse)%mod + (leftFalse * rightTrue)%mod;
                }else{
                    
                    ways += (leftFalse * rightFalse)%mod;
                }
            }else{
                
                // T^T = F , F*F = F , T^F= T , F^T = T
                
                if(isTrue==1){
                    
                    ways += (leftTrue * rightFalse)%mod + (leftFalse * rightTrue)%mod;
                }else{
                    
                    ways += (leftTrue * rightTrue)%mod + (leftFalse * rightFalse)%mod;
                }
            }
            
            
        }
        
        return ways;
    }
}