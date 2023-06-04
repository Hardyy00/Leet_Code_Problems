//{ Driver Code Starts
//Initial Template for Java

import java.io.*;
import java.util.*;

class GFG
{
    public static void main(String args[])throws IOException
    {
        BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out=new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine().trim());
        while(t-- > 0)
        {
            String s;
            s = in.readLine().trim();
            
            Solution ob = new Solution();
            
            out.println(ob.reverseEqn(s));    
        }
        out.close();
    }
}
// } Driver Code Ends


//User function Template for Java
class Solution
{
   
    String reverseEqn(String s)
    {
        StringBuilder build = new StringBuilder();
        int mul = 1;
        int val = 0;
        
        for(int i=s.length()-1;i>=0;i--){
            
            char ch = s.charAt(i);
            
            if(Character.isDigit(ch)){
                val +=(mul*(ch-'0'));
                mul*=10;
            }else{
                build.append(val);
                val = 0;
                mul = 1;
                build.append(ch);
            }
        }
        
        build.append(val);
        
        
        return build.toString();
    }
}