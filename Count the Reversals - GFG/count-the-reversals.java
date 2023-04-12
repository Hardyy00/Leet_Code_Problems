//{ Driver Code Starts
//Initial Template for Java

import java.io.*;
import java.util.*;
class GfG
{
    public static void main (String[] args)
    {
        
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        
        while(t-- > 0)
        {
            String s = sc.next ();
    		System.out.println (new Sol().countRev (s));
        }
        
    }
}
// Contributed By: Pranay Bansal

// } Driver Code Ends


//User function Template for Java

class Sol
{
    int countRev (String s)
    {
        Deque<Character> stack = new ArrayDeque<>();
        
        for(int i=0;i<s.length();i++) {
            
            char ch = s.charAt(i);
            
            if(ch=='{') stack.push(ch);
            else if(!stack.isEmpty() && stack.peek()=='{') stack.pop();
            else stack.push(ch);
            
        }   
        
        
        int open = 0, close = 0;
        
        while(!stack.isEmpty()){
            
            if(stack.pop()=='{') open++;
            else close++;
        }
        
        if(((open+close)&1)==1) return -1;
        
        int count = (open+1)/2 + (close+1)/2;
        
        return count;
    }
}