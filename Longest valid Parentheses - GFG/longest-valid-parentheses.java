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
            String S = in.readLine();
            
            Solution ob = new Solution();
            System.out.println(ob.maxLength(S));
        }
    }
}
// } Driver Code Ends


//User function Template for Java

class Solution{
    static int maxLength(String s){
        
        Deque<Integer> stack = new ArrayDeque<>();
        
        for(int i=0;i<s.length();i++){
            
            char ch = s.charAt(i);
            if(!stack.isEmpty() && ch==')' &&s.charAt(stack.peek())=='(') stack.pop();
            else stack.push(i);
        }
        
        if(stack.isEmpty()) return s.length();
        
        int maxLen = 0;
        int last = s.length();
        while(!stack.isEmpty()){
            int popped = stack.pop();
            maxLen = Math.max(maxLen,last-popped-1);
            last = popped;
        }
        
        maxLen = Math.max(maxLen,last);
        
        
        
        return maxLen;
    }
}