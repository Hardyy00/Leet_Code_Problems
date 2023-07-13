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
        
        // int[][][] dp = new int[n][n][2];
        
        // for(int[][] mat : dp){
            
        //     for(int[] row : mat) Arrays.fill(row, -1);
        // }
        
        // return solve2(0,n-1,1,s,dp);
        
        return solve3(n, s);
    }
    
    static int solve3(int n, String s){
        
        int[][][] dp = new int[n][n][2];
        
        for(int i=0;i<n;i++){
            
            char ch = s.charAt(i);
            for(int j=0;j<=1;j++){
                
                if(j==0 && ch=='F') dp[i][i][0] = 1;
                else if(j==1 && ch=='T') dp[i][i][1] =1;
                
            }
        }
        
        
        for(int i=n-1;i>=0;i--){
            
            for(int j=i+1;j<n;j++){
                
                for(int k=0;k<=1;k++){
                    
                    int ways = 0;
                    for(int par=i+1;par<=j-1;par++){
                        
                        int leftTrue = dp[i][par-1][1];
                        int leftFalse = dp[i][par-1][0];
                        
                        int rightTrue = dp[par+1][j][1];
                        int rightFalse = dp[par+1][j][0];
                        
                        char sign = s.charAt(par);
                        
                        if(sign=='&'){
                            
                            if(k==1){
                                
                                ways += (leftTrue * rightTrue)%mod;
                            }else{
                                
                                ways += (leftFalse * rightFalse)%mod + (leftTrue * rightFalse)%mod + (leftFalse * rightTrue)%mod;
                            }
                            
                        }else if(sign=='|'){
                            
                            if(k==1){
                                ways += (leftTrue * rightTrue)%mod + (leftTrue * rightFalse)%mod + (leftFalse * rightTrue)%mod;
                            }else{
                                
                                ways += (leftFalse * rightFalse)%mod;
                            }
                        }else{
                            
                            
                            if(k==1){
                                ways += (leftTrue * rightFalse)%mod + (leftFalse * rightTrue)%mod;
                            }else{
                                
                                ways += (leftTrue * rightTrue)%mod + (leftFalse * rightFalse)%mod;
                            }
                        }
                        
                    }
                    
                    dp[i][j][k] = ways%mod;
                }
            }
        }
        
        return dp[0][n-1][1];
    }
    
    static int solve2(int i, int j,int isTrue, String s, int[][][] dp){
        
        // Memoization
        // TC : O(N*N*N*2)
        // SC : O(N*N*2) + O(N)
        
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
        
        // FOR T|T&F , we can make a partition at | , &, so if the passed range is 0, 4, loop will run from
        // 1 to e (i+ to j-1) & since each sign is at distance of 2, we will add 2 every time
        
        // to get make true when you have | , you can have True on both sides, true on left & false on right,
        // and false on left and true on right, in this way you can calculate number of ways to get true
        
        // Recursion
        //  TC : Exponential
        // SC : O(N)
        
        if(i>j) return 0;
        
        if(i==j){
                
            // if you wanted to find true, and if it is true , then return 1 else 0
            if(isTrue==1) return s.charAt(i)=='T' ? 1 : 0;
            
            return s.charAt(i)=='F' ? 1 : 0;
        }
        
        int ways = 0;
        
        for(int k=i+1;k<=j-1;k+=2){
            
            // find in how many ways you can make , true and false on left side (for calculations)
            int leftTrue = solve(i,k-1,1,s);
            int leftFalse = solve(i,k-1,0,s);
            
            // find in how many ways you can make , true and false on right side (for calculations)
            int rightTrue = solve(k+1, j, 1,s);
            int rightFalse = solve(k+1, j, 0,s);
            
            char sign = s.charAt(k);
            
            if(sign=='&'){
                
                // if true , then get number of ways to make true
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