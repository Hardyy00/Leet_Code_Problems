//{ Driver Code Starts
//Initial Template for Java

import java.util.*;
import java.lang.*;
import java.io.*;


// } Driver Code Ends
//User function Template for Java

class Solution
{
    //Function to perform case-specific sorting of strings.
    public static String caseSort(String str)
    {
        char[] help = str.toCharArray();
        Arrays.sort(help);
        
        int up = -1;
        int low = -1;
        if(help[0]>=65 && help[0]<=90)
            up =0;
        
        for(int i=1;i<help.length;i++){
            
            if(help[i]>=97 && help[i]<=122){
                low = i;
                break;
            }
        }
        
        StringBuilder build = new StringBuilder();
        
        for(int i=0;i<str.length();i++){
            
            if(str.charAt(i)>=97 && str.charAt(i)<=122){
                build.append(help[low]);
                low++;
            }else{
                build.append(help[up]);
                up++;
            }
        }
        
        return build.toString();
    }
}

//{ Driver Code Starts.

class GFG {
	public static void main (String[] args) {
		
    	try {
    	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	    PrintWriter out=new PrintWriter(System.out);
    	    String[] words = br.readLine().split("\\s+");
    	    int numTestCases = Integer.parseInt(words[0]);
    	    
    	    for (int tIdx = 0; tIdx < numTestCases; tIdx++) {
    	        words = br.readLine().split("\\s+");
    	        int size = Integer.parseInt(words[0]);
    	        String str = br.readLine();
    	        String sortedStr = new Solution().caseSort(str);
    	        out.println(sortedStr);
    	    }
    	    out.close();
    	}
    	catch (IOException e) {
    	    System.out.println(e);
    	}
	}
}
// } Driver Code Ends