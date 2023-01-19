//{ Driver Code Starts
import java.io.*;
import java.util.*;
class GfG
{
    public static void main(String args[])throws IOException
        {
            Scanner sc = new Scanner(System.in);
            int t = sc.nextInt();
            while(t-->0)
                {
                    int k = sc.nextInt();
                    String str = sc.next();
                    Solution obj = new Solution();
                    System.out.println(obj.findMaximumNum(str, k));
                }
        }
}
// } Driver Code Ends




class Solution
{
    static String maxString = ""; 
    //Function to find the largest number after k swaps.
    public static String findMaximumNum(String str, int k)
        {
            char[] arr = str.toCharArray();
            maxString = str;
            findMax(0,k,arr);
            
            return maxString;
        }
        
        
        private static void findMax(int index,int k,char[] arr){
            
            if(index==arr.length || k==0){
                String val = new String(arr);
                maxString = maxString.compareTo(val) > 0 ? maxString : val;
                return;
            }
            
            boolean wasFound = false;
            
            for(int i=index+1;i<arr.length;i++){
                
                if(arr[i]>arr[index]){
                    wasFound = true;
                    swap(index,i,arr);
                    findMax(index+1,k-1,arr);
                    swap(index,i,arr);
                    
                }
            }
            
            if(!wasFound){
                findMax(index+1,k,arr);
            }
            
           
            
        }
        
        
        
        
        private static void swap(int k,int i,char[] arr){
            
            char temp = arr[k];
            arr[k] = arr[i];
            arr[i] = temp;
            
        }
}