//{ Driver Code Starts
//Initial Template for Java

import java.io.*;
import java.util.*;
import java.lang.*;

class GFG {
    public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(br.readLine());
		while(test-- > 0) {
			int N = Integer.parseInt(br.readLine());
			int [] A = new int[N];
			String [] str = br.readLine().trim().split(" ");
			for(int i = 0; i < N; i++)
				A[i] = Integer.parseInt(str[i]);
			Solution ob = new Solution();
			System.out.println(ob.equalSum(A, N));
		}
    }
}
// } Driver Code Ends


//User function Template for Java

class Solution{
	int equalSum(int [] a, int n) {
		
		int[] prefix = getPrefix(a);
		int[] suffix = getSuffix(a);
		
		for(int i=0;i<n;i++){
		    
		    if(prefix[i]==suffix[i]) return i+1;
		}
		
		return -1;
	}
	
	int[] getPrefix(int[] arr){
	    
	    int[] prefix = new int[arr.length];
	    
	    for(int i=1;i<arr.length;i++){
	        prefix[i] = prefix[i-1]+arr[i-1];
	    }
	    
	    return prefix;
	}
	
	int[] getSuffix(int[] arr){
	    
	    int[] suffix = new int[arr.length];
	    for(int i=arr.length-2;i>=0;i--){
	        suffix[i] = suffix[i+1]+arr[i+1];
	    }
	    
	    return suffix;
	}
}