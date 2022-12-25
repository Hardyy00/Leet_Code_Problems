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
            int N=sc.nextInt();
			ArrayList<Integer> arr = new ArrayList<>();
			for(int i = 0; i < N ; i++){
			    arr.add(sc.nextInt());
			}
            Solution ob = new Solution();
         
            ArrayList<Integer> ans = ob.subsetSums(arr,N);
            Collections.sort(ans);
            for(int sum : ans){
                System.out.print(sum+" ");
            }
            System.out.println();
        }  
    }
}

// } Driver Code Ends

//User function Template for Java//User function Template for Java
class Solution{
    ArrayList<Integer> subsetSums(ArrayList<Integer> arr, int N){
        
        Collections.sort(arr);
        ArrayList<Integer> list = new ArrayList<>();
        fill(arr,list,0,0);
        
        return list;
    }
    
    private static void fill(ArrayList<Integer> arr ,ArrayList<Integer> list, int sum , int index){
        
        if(index>=arr.size()){
            
            list.add(sum);
            return;
        }
        
        
        // piick the element
        sum += arr.get(index);
        fill(arr,list,sum,index+1);
        sum-=arr.get(index);
        
        
        //do not pick the element
         fill(arr,list,sum,index+1);
        

    }
}