//{ Driver Code Starts
//Initial Template for Java

import java.util.*;
import java.lang.*;
import java.io.*;
class GFG
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        while(T-->0)
        {
            int n = Integer.parseInt(br.readLine().trim());
            String s = br.readLine().trim();
            String[] s1 = s.split(" ");
            int[] nums = new int[n];
            for(int i = 0; i < s1.length; i++)
                nums[i] = Integer.parseInt(s1[i]);
            Solution ob = new Solution();
            int ans = ob.LongestBitonicSequence(nums);
            System.out.println(ans);           
        }
    }
}

// } Driver Code Ends


//User function Template for Java

class Solution
{
    public int LongestBitonicSequence(int[] arr)
    {
       int n = arr.length;
       
       int[] dp1 = new int[n];
       int[] dp2 = new int[n];
       
       for(int i=0;i<n;i++){
           
           dp1[i] = 1;
           
           for(int j=0;j<i;j++){
               
               
               if(arr[i]>arr[j] && 1 + dp1[j] > dp1[i]){
                   
                   dp1[i] = 1+dp1[j];
               }
           }
       }
       
       
       int maxLen = 0;
       
       for(int i=n-1;i>=0;i--){
        
           dp2[i] = 1;

           for(int j=n-1;j>i;j--){
               
               if(arr[i] > arr[j] && 1+dp2[j] > dp2[i]){
                   
                   dp2[i] = 1 + dp2[j];
               }
           }
           
           maxLen = Math.max(maxLen , dp1[i] + dp2[i] -1);
       }
       
       
       
       return maxLen;
    }
    
}