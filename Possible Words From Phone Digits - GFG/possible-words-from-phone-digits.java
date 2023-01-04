//{ Driver Code Starts
//Initial Template for Java

import java.util.*;
import java.io.*;
import java.lang.*;

class PhoneDigit
{
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();//testcases
        
        
        while(t-- > 0)
        {
            int n = sc.nextInt(); // input size of array
            int arr[] = new int[n]; //input the elements of array that are keys to be pressed
            
            for(int i = 0; i < n; i++)
               arr[i] = sc.nextInt();//input the elements of array that are keys to be pressed
            ArrayList <String> res = new Solution().possibleWords(arr, n);
            for (String i : res) System.out.print (i + " ");
             System.out.println();
              
        }
    }
}

// } Driver Code Ends


//User function Template for Java

class Solution
{
    //Function to find list of all words possible by pressing given numbers.
    static ArrayList <String> possibleWords(int a[], int n)
    {
        
        ArrayList<String> ans = new ArrayList<>();
        
        StringBuilder s = new StringBuilder();
        
        combinations(0,s,ans,a);
        
        return ans;
        
    }
    
    private static void combinations(int index,StringBuilder s,ArrayList<String> ans,int[] a){
        
        if(index==a.length){
            ans.add(s.toString());
            return;
        }
        
        char start = 'a';
        if(a[index]<=7){
            start += (a[index]-2)*3;
        }else if(a[index]==8)
            start = 't';
        else
            start = 'w';
        
        int end = 3;
        
        if(a[index]==7 || a[index]==9)
            end = 4;
            
        for(int i=0;i<end;i++){
            

            s.append((char)(start+i));
            combinations(index+1,s,ans,a);
            s.deleteCharAt(s.length()-1);
            
        }
    }
}


