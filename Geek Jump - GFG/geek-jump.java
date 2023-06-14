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
            int res=obj.minimumEnergy(arr,N);
            System.out.println(res);
            
        }
    }
}


// } Driver Code Ends


//User function Template for Java

class Solution{
    public int minimumEnergy(int arr[],int n){
       
       int pre1 = 0;
       int pre2 = 0;
       
       for(int i=1;i<n;i++){
           
           int moveOne = pre1+ Math.abs(arr[i]-arr[i-1]);
           
           int moveTwo = Integer.MAX_VALUE;
           
           if(i>1) moveTwo = pre2 + Math.abs(arr[i]-arr[i-2]);
           
           pre2 = pre1;
           pre1 = Math.min(moveOne,moveTwo);
       }
       
       return pre1;
    }
}