//{ Driver Code Starts
//Initial Template for Java

import java.util.*;

public class GFG 
{
    public static void main(String args[]) 
    {
        Scanner sc = new Scanner(System.in);
        
        int t = sc.nextInt();
        while (t-- > 0) 
        {
            int n = sc.nextInt();
            int arr[] = new int[n];
            for (int i = 0;i < n;i++)
            {
                arr[i] = sc.nextInt();
            }
            
            Solution sol = new Solution();
            System.out.println(sol.findOnce(arr, n));
        }
    }
}
// } Driver Code Ends


//User function Template for Java

class Solution {
    public int findOnce(int[] nums,int n) {
        if(nums.length==1)
            return nums[0];
        if(nums[0]!=nums[1])
            return nums[0];
        else if(nums[nums.length-1]!=nums[nums.length-2])
            return nums[nums.length-1];

        int start = 0;
        int end = nums.length-1;
        
        while(start<=end){

            int mid = end + (start-end)/2;
            if(nums[mid]==nums[mid+1]){
                if(mid%2==0)
                    start= mid+1;
                else
                    end = mid-1;
            }else if(nums[mid]==nums[mid-1]){

                if((mid-1)%2==0)
                    start = mid+1;
                else
                    end = mid-1;
            }else{
                return nums[mid];
            }

        }

        return -1;
        
    }
}