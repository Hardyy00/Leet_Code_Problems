//{ Driver Code Starts
import java.util.*;
import java.lang.*;
import java.io.*;

class GFG {
    
    
	public static void main (String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine().trim());
		while(t-->0){
		    long n = Long.parseLong(br.readLine().trim());
		    String inputLine[] = br.readLine().trim().split(" ");
		    long[] arr = new long[(int)n];
		    for(int i=0; i<n; i++)arr[i]=Long.parseLong(inputLine[i]);
		    System.out.println(new Solution().getMaxArea(arr, n));
		}
	}
}




// } Driver Code Ends


class Solution
{
    //Function to find largest rectangular area possible in a given histogram.
    public static long getMaxArea(long hist[], long n) 
    {
        Deque<Integer> stack = new ArrayDeque<>();
        long maxArea = Long.MIN_VALUE;
        
        for(int i=0;i<hist.length;i++){
            
            while(!stack.isEmpty() && hist[stack.peek()]>=hist[i]){
                
                int currBlock = stack.pop();
                int len = stack.isEmpty() ? i : (i-stack.peek()-1);
                maxArea = Math.max(maxArea,len*hist[currBlock]);
                
                // System.out.println(maxArea);
                
            }
            
            stack.push(i);
            
            
            
        }
        
        while(!stack.isEmpty()){
            
            int currBlock = stack.pop();
            int len = stack.isEmpty() ? hist.length : (hist.length-stack.peek()-1);
            maxArea = Math.max(maxArea,len*hist[currBlock]);
        }
        
        
        return maxArea;
        
        
    }
        
}



