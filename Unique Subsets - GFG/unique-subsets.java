//{ Driver Code Starts
import java.util.*;
import java.lang.*;
import java.io.*;

class GFG {
    
    
	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		int testCases = sc.nextInt();
		for(int t=0;t<testCases;t++){
		    int n = sc.nextInt();
		    int arr[] = new int[n];
		    for(int i=0;i<n;i++){
		        arr[i] = sc.nextInt();
		    }
		    Arrays.sort(arr);
		    ArrayList <ArrayList<Integer>> res = new solve().AllSubsets(arr,n);
		    for (int i = 0; i < res.size (); i++)
		    {
		        System.out.print ("(");
		        for (int j = 0; j < res.get(i).size (); j++)
		        {
		            if (j != res.get(i).size()-1)
		                System.out.print ((res.get(i)).get(j) + " ");
		            else
		                System.out.print ((res.get(i)).get(j));
		        }
		        System.out.print (")");
		      
		    }
		    System.out.println();
		}
	}
}
// } Driver Code Ends


class solve
{
    //Function to find all possible unique subsets.
    public static ArrayList <ArrayList <Integer>> AllSubsets(int arr[], int n)
    {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        ArrayList<Integer> list = new ArrayList<>();
        ans.add(new ArrayList<>());
        
        findSubsets(0,arr,list,ans);
        
        return ans;
    }
    
    private static void findSubsets(int index,int[] arr,ArrayList<Integer> list,ArrayList<ArrayList<Integer>> ans){
        
        
        for(int i=index;i<arr.length;i++){
            
            if(i>index && arr[i]==arr[i-1])
                continue;
            
            list.add(arr[i]);
            ans.add(new ArrayList<>(list));
            findSubsets(i+1,arr,list,ans);
            list.remove(list.size()-1);
            
        }
    }
}
