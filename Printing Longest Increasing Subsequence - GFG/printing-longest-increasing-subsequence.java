//{ Driver Code Starts
//Initial Template for Java

import java.util.*;
import java.lang.*;
import java.io.*;

class GFG{
    public static void main(String args[]) throws IOException{
        Scanner sc=new Scanner(System.in);
        int t=sc.nextInt();
        while(t-->0){
            int N=sc.nextInt();
            int []arr=new int[N];
            for(int i=0;i<N;i++){
                arr[i]=sc.nextInt();
            }
            Solution obj=new Solution();
            ArrayList<Integer>ans=obj.longestIncreasingSubsequence(N, arr);
            for(int i=0;i<ans.size();i++){
                System.out.print(ans.get(i)+" ");
            }
            System.out.println();
        }
    }
}
// } Driver Code Ends


//User function Template for Java

class Solution{
    public ArrayList<Integer> longestIncreasingSubsequence(int n, int arr[]){
        
        int[] dp = new int[n];
        int[] previousIndexes = new int[n]; 
    
        Arrays.fill(dp , 1);
        
        int lis = 1;
        int lastIndex = 0;
        
        for(int i=0;i<n;i++){
            
            previousIndexes[i] = i;
            
            for(int j=0;j<i;j++){
                
                if(arr[i]>arr[j] && 1+dp[j] > dp[i]){
                    
                    dp[i] = 1 + dp[j];
                    previousIndexes[i] = j;
                    
                }
            }  
            
            if(dp[i]>lis){
                lis = dp[i];
                lastIndex = i;
            }
        }
        
        ArrayList<Integer> list = new ArrayList<>();
        
        while(true){
            list.add(arr[lastIndex]);
            
            if(lastIndex==previousIndexes[lastIndex]) break;
            
            lastIndex = previousIndexes[lastIndex];
            
        }
        
        Collections.reverse(list);
        
        return list;
    }
}
