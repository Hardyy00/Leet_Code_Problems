//{ Driver Code Starts
//Initial Template for Java
import java.io.*;
import java.util.*;

class GFG {
    public static void main(String args[]) throws IOException {
        BufferedReader read =
            new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(read.readLine());
        while (t-- > 0) {
            String S = read.readLine();

            Solution ob = new Solution();
            System.out.println(ob.findMaxLen(S));
        }
    }
}


// } Driver Code Ends


//User function Template for Java
class Solution {
    static int findMaxLen(String s) {
       
       Deque<Integer> stack = new ArrayDeque<>();
       
       for(int i=0;i<s.length();i++){
           
           char ch = s.charAt(i);
           
           if(stack.isEmpty() || ch=='(') stack.push(i);
           else if(s.charAt(stack.peek())=='(') stack.pop();
           else stack.push(i);
       }
       
        int maxLen = 0;
    int len = s.length();
    
    while(!stack.isEmpty()){
        
        int pop = stack.pop();
        maxLen = Math.max(maxLen,len-pop-1);
        len = pop;
    }
    
    maxLen = Math.max(maxLen,len);
    
    return maxLen;
    }
    
   
}