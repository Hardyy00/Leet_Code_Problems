//{ Driver Code Starts
//Initial Template for Java

import java.io.*;
import java.util.*;


class GFG
{
    public static void main(String args[])throws IOException
    {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(read.readLine());
        while(t-- > 0)
        {
            String S[] = read.readLine().split(" ");
            int R = Integer.parseInt(S[0]);
            int C = Integer.parseInt(S[1]);
            int matrix[][] = new int[R][C];
            int c = 0;
            for(int i = 0; i < R; i++){
                String line[]=read.readLine().trim().split(" ");
                for(int j = 0; j < C; j++){
                    matrix[i][j] = Integer.parseInt(line[j]);
                }
            }
            Solution ob = new Solution();
            int ans = ob.median(matrix, R, C);
            System.out.println(ans);
        }
    }
}

// } Driver Code Ends


//User function Template for Java

class Solution {
    //   REFER IN striverDSA
    int median(int matrix[][], int r, int c) {
        
        int elementsBeforeMedian = (r*c)/2;
        
        int low = 1;
        int high = (int)1e9;
        
        while(low<=high){
            
            int mid = high + (low - high)/2;
            
            int elementBeforeAndEqualToMid = 0;
            
            for(int[] arr : matrix){
                
                elementBeforeAndEqualToMid += countElementBeforeAndEqualToMid(arr,mid);
            }
            
            if(elementBeforeAndEqualToMid<=elementsBeforeMedian){
                low = mid+1;
            }else{
                high = mid - 1;
            }
        }
        
        return low;
    }
    
    private static int countElementBeforeAndEqualToMid(int[] arr,int ele){
        
        int low = 0, high = arr.length-1;
        
        while(low<=high){
            
            int mid = high + (low-high)/2;
            
            if(arr[mid]<=ele){
                low = mid+1;
            }else{
                high = mid-1;
            }
        }
        
        return low;
    }
}