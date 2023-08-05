//{ Driver Code Starts
// Initial Template for Java

import java.util.*;
import java.lang.*;
import java.io.*;

// Position this line where user code will be pasted.

class GFG {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- > 0) {
            int n = sc.nextInt();
            int[] a = new int[n];

            for (int i = 0; i < n; i++) {
                a[i] = sc.nextInt();
            }

            int start = sc.nextInt();
            int end = sc.nextInt();

            Solution ob = new Solution();
            int ans = ob.minimumMultiplications(a, start, end);
            System.out.println(ans);
        }
    }
}
// } Driver Code Ends


class Pair{
    
    int mul;
    int step;
    
    
    public Pair(int mul, int step){
        
        this.mul = mul;
        this.step = step;
    }
}

class Solution {
    int minimumMultiplications(int[] arr, int start, int end) {
        
        int n = arr.length;
        
        int[] dis = new int[(int)1e5];
        Arrays.fill(dis, (int)1e8);
        Queue<Pair> q = new LinkedList<>();
        
        dis[start] = 0;
        q.offer(new Pair(start,0));
        
        while(!q.isEmpty()){
            
            int mul = q.peek().mul;
            int steps = q.peek().step;
            
            q.poll();
            
            if(mul==end) return steps;
            
            for(int i=0;i<n;i++){
                
                int temp = (int)(((long)mul*arr[i])%(int)1e5);
                
                if(steps+1 < dis[temp]){
                    
                    dis[temp] = steps+1;
                    
                    q.offer(new Pair(temp, dis[temp]));
                }
                
            }
        }
        
        return -1;
    }
}
