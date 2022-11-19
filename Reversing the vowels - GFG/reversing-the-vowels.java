//{ Driver Code Starts
//Initial Template for Java

import java.io.*;
import java.util.*;

class GFG
{
    public static void main(String args[])throws IOException
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-- > 0)
        {
            String s;
            s = sc.next();
           
            Solution ob = new Solution();
            
            System.out.println(ob.modify(s));    
        }
    }
}
// } Driver Code Ends


//User function Template for Java

class Solution {
    String modify(String s) {

        StringBuilder str = new StringBuilder(s);
        int j=str.length()-1;
        for(int i=0;i<str.length();i++){

            if(isVowel((str.charAt(i)+"").toLowerCase()) && i<j){

                while(!isVowel((str.charAt(j)+"").toLowerCase())) {
                    j--;
                }

                char a1 = str.charAt(i);
                str.replace(i,i+1,str.charAt(j)+"");
                str.replace(j,j+1,a1+"");
                j--;
            }

        }

        return str.toString();
    }

    private static boolean isVowel(String s){

        return s.equals("a") || s.equals("e") || s.equals("i") || s.equals("o") || s.equals("u");
    }
}