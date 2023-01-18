//{ Driver Code Starts
//Initial Template for Java

/*package whatever //do not write package name here */

import java.io.*;
import java.util.*;
class Main {
	public static void main (String[] args) {
		Scanner sc=new Scanner(System.in);
		
		int T=sc.nextInt();
		sc.nextLine();
		while(T-->0)
		{
		    
		    Solution ob=new Solution();
		    
		    String S=sc.nextLine();
		    
		    ArrayList<String> arr = ob.permutation(S);
		    for(String s : arr){
		        System.out.print(s+" ");
		    }
		    System.out.println();
		}
		
	}
}

// } Driver Code Ends


//User function Template for Java


class Solution
{
    public ArrayList<String> permutation(String s)
    {
       ArrayList<String> ans = new ArrayList<>();
       char[] arr = s.toCharArray();
       Arrays.sort(arr);
       StringBuilder build = new StringBuilder();
       boolean[] visit = new boolean[s.length()];
       
       findPermutations(build,visit,arr,ans);
       
       return ans;
    }
    
    private static void findPermutations(StringBuilder build,boolean[] visit,char[] arr,ArrayList<String> ans){
        
        if(build.length()==arr.length){
            ans.add(build.toString());
            return;
        }
        
    
        for(int i=0;i<arr.length;i++){
            
            if(visit[i])
                continue;
                
            visit[i] = true;
            build.append(arr[i]);
            findPermutations(build,visit,arr,ans);
            build.deleteCharAt(build.length()-1);
            visit[i] = false;
        }
        
    }
	   
}
