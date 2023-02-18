//{ Driver Code Starts
//Initial Template for Java

import java.io.*;
import java.util.*;

class GFG
{
    public static void main(String args[])throws IOException
    {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out=new PrintWriter(System.out);
        int t = Integer.parseInt(read.readLine());
        while(t-- > 0)
        {
            String input[] = read.readLine().trim().split("\\s+");
            int N = Integer.parseInt(input[0]);
            int M = Integer.parseInt(input[1]);
            String arr = read.readLine().trim();

            Solution ob = new Solution();
            out.println(ob.appleSequence(N, M, arr));
        }
        out.close();
    }
}


// } Driver Code Ends
//User function Template for Java


class Solution{
    public static int appleSequence(int n, int m, String arr){
       
       int st = 0,it = 0;
       int maxLen = Integer.MIN_VALUE;
       int count = 0;
       
       while(it<arr.length()){
           
           if(arr.charAt(it)=='O'){
               count++;
           }
           
           while(count>m && st<=it){
               
               if(arr.charAt(st)=='O')
                    count--;
                    
                st++;
           }
           
           maxLen = Math.max(maxLen, it-st+1);
           it++;
       }
       
       return maxLen;
    }
}


//{ Driver Code Starts.

// } Driver Code Ends