//{ Driver Code Starts
//Initial Template for Java

import java.io.*;
import java.util.*;

class GFG {
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine().trim()); //Inputting the testcases
        while(t-->0){
            String S = br.readLine();
            Solution ob = new Solution();
            System.out.println(ob.rremove(S));
        }
    }
}
// } Driver Code Ends


//User function Template for Java

class Solution{
    String rremove(String s) {
        
        if(s.length()<=1)
            return s;
       
       StringBuilder ans = new StringBuilder();
       
       boolean found = false;
       
       if(s.charAt(0)!=s.charAt(1))
            ans.append(s.charAt(0));
        
        
        for(int i=1;i<s.length();i++){
            
            char curr = s.charAt(i);
            
            if(i<s.length()-1 && curr==s.charAt(i+1)){
                found = true;
                i++;
            }
            else if(curr!=s.charAt(i-1))
                ans.append(curr);
        }
        
        
        s = ans.toString();
        
        if(found)
            return rremove(s);
        
        return s;
       
       
    }
    
}