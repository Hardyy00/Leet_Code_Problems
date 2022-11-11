//{ Driver Code Starts
//Initial Template for Java

import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine().trim());
        while (tc-- > 0) {
            String[] inputLine;
            inputLine = br.readLine().trim().split(" ");
            int n = Integer.parseInt(inputLine[0]);
            int m = Integer.parseInt(inputLine[1]);
            int[][] arr = new int[n][m];
            inputLine = br.readLine().trim().split(" ");
        
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    arr[i][j] = Integer.parseInt(inputLine[i * m + j]);
                }
            }
            int ans = new Solution().rowWithMax1s(arr, n, m);
            System.out.println(ans);
        }
    }
}

// } Driver Code Ends


//User function Template for Java

class Solution {
    int rowWithMax1s(int arr[][], int n, int m) {
        
        int maxRowIndex = -1;
        int max1Count = -1;
        
        for(int i=0;i<n;i++){
            
            int count1= findNumberOf1(arr[i]);
            if(count1 > max1Count){
                maxRowIndex = i;
                max1Count = count1;
            }
        }
        
        return maxRowIndex;
        
        
    }
    
    int findNumberOf1(int[] arr){
        
        int indexOf1 = -1;
        
        int lower = 0;
        int higher = arr.length-1;
        while(lower<=higher){
            
            int mid = lower + (higher-lower)/2;
            
            if(arr[mid]<1){
                lower = mid +1;
            }else{
                indexOf1 = mid;
                higher = mid -1;
            }
        }
        
        if(indexOf1 != -1){
            return arr.length - indexOf1;
        }
        
        return indexOf1;
    }
}