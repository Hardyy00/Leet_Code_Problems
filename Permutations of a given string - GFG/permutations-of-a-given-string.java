//{ Driver Code Starts
import java.util.*;
import java.lang.*;
import java.io.*;
class GFG
{
	public static void main(String[] args) throws IOException
	{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine().trim());
        while(t-->0)
        {
            String S = br.readLine().trim();
            Solution obj = new Solution();
            List<String> ans = obj.find_permutation(S);
            for( int i = 0; i < ans.size(); i++)
            {
                System.out.print(ans.get(i)+" ");
            }
            System.out.println();
                        
        }
	}
}


// } Driver Code Ends


class Solution {
    public List<String> find_permutation(String s) {
        
        List<String> ans = new ArrayList<>();
        
        
        
        if(s.length()==1){
            ans.add(s);
            return ans;
        }
            
        
        char[] arr = s.toCharArray();
        Arrays.sort(arr);
        
        boolean[] visit = new boolean[arr.length];
        StringBuilder build = new StringBuilder();
        
        givePermutations(0,arr,build,visit,ans);
        
        return ans;
    }
    
    private static void givePermutations(int index,char[] arr,StringBuilder build,boolean[] visit,List<String> ans){
        
        if(index==arr.length){
            
            ans.add(build.toString());
            return;
        }
        
        
        for(int i=0;i<arr.length;i++){
            
            if(visit[i])
                continue;
            
            if(i>0 && !visit[i-1] && arr[i]==arr[i-1])
                continue;
                
            visit[i] = true;
            build.append(arr[i]);
            
            givePermutations(index+1,arr,build,visit,ans);
            
            build.deleteCharAt(build.length()-1);
            visit[i] = false;
            
        }
    }
}