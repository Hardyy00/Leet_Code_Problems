//{ Driver Code Starts
//Initial Template for Java

import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out=new PrintWriter(System.out);
        int tc = Integer.parseInt(br.readLine().trim());
        while (tc-- > 0) {
            String[] inputLine;
            int n = Integer.parseInt(br.readLine().trim());
            int[] arr = new int[n];
            inputLine = br.readLine().trim().split(" ");
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(inputLine[i]);
            }

            new Solution().convert(arr, n);
            for (int i = 0; i < n; i++) {
                out.print(arr[i] + " ");
            }
            out.println();
        }
        out.close();
    }
}

// } Driver Code Ends


//User function Template for Java

class Solution {
    void convert(int[] arr, int n) {
       
        int[] copy = Arrays.copyOfRange(arr,0,arr.length);
        
        Arrays.sort(copy);
        
        for(int i=0;i<n;i++){
            arr[i] = search(arr[i],copy);
        }
        
    }
    
    int search(int target,int[] arr){
        
        int lower = 0;
        int higher = arr.length-1;
        
        while(lower<=higher){
            
            int mid = lower + (higher-lower)/2;
            
            if(arr[mid]>target)
                higher = mid-1;
            else if(arr[mid] < target)
                lower = mid+1;
            else
                return mid;
        }
        
        return -1;
    }
}