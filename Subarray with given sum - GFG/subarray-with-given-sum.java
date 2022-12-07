//{ Driver Code Starts
import java.util.*;
import java.lang.*;
import java.io.*;

class Main{
	static BufferedReader br;
    static PrintWriter ot;
    public static void main(String[] args) throws IOException{
        
        br = new BufferedReader(new InputStreamReader(System.in));
        ot = new PrintWriter(System.out);

        int t = Integer.parseInt(br.readLine());

        while(t-->0){
            
            String s[] = br.readLine().trim().split(" ");
            
            int n = Integer.parseInt(s[0]);
            int k = Integer.parseInt(s[1]);
            int a[] = new int[n];
            s = br.readLine().trim().split(" ");
            for(int i = 0; i < n; i++)
                a[i] = Integer.parseInt(s[i]);
            Solution obj = new Solution();
            ArrayList<Integer> res = obj.subarraySum(a, n, k);
            for(int ii = 0;ii<res.size();ii++)
                ot.print(res.get(ii) + " ");
            ot.println();
        }
        ot.close();
    }

}
// } Driver Code Ends


class Solution
{
    //Function to find a continuous sub-array which adds up to a given number.
    static ArrayList<Integer> subarraySum(int[] arr, int n, int s) 
    {
        ArrayList<Integer> list = new ArrayList<>();
    //   if(arr.length==1){
    //       if(arr[0]==s){
    //           list.add(1);
    //           list.add(1);
    //       }else
    //             list.add(-1);
            
    //         return list;
            
    //   }
       long sum = 0;
       int it = 0;
       int st = 0;
       int start = -1;
       int len = 0;
       
       while(it<arr.length){
           
           sum += (long)arr[it];
           
           while(sum > s && st<=it){
               
               sum -= arr[st];
               st++;
           }
           
           if(sum==s){
               start = st;
               len = it-st+1;
               
               break;
           }
           
           it++;
       }
       
       
       if(start==-1){
            list.add(-1);
       }else{
           int f = start+1;
           int l = start  + len;
           if(f<=l){
               list.add(f);
                list.add(l);
           }else
                list.add(-1);
       }
       
       
       return list;
    }
}