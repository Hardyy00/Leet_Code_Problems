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
            int m = sc.nextInt();
            int[][] grid = new int[n][m];

            for (int i = 0; i < n; i++) {

                for (int j = 0; j < m; j++) {
                    grid[i][j] = sc.nextInt();
                }
            }
            int[] source = new int[2];
            for (int i = 0; i < 2; i++) {
                int x = sc.nextInt();
                source[i] = x;
            }
            int[] dest = new int[2];
            for (int i = 0; i < 2; i++) {
                int x = sc.nextInt();
                dest[i] = x;
            }
            Solution ob = new Solution();
            int ans = ob.shortestPath(grid, source, dest);
            System.out.println(ans);
        }
    }
}
// } Driver Code Ends


class Pair{
    
    int r;
    int c;
    int dis;
    
    public Pair(int r, int c, int dis){
        
        this.r = r;
        this.c = c;
        this.dis = dis;
    }
}

class Solution {
    
    int[] ra = {-1,0,1,0};
    int[] ca = {0,1,0,-1};

    int shortestPath(int[][] grid, int[] source, int[] destination) {
        
        // TC : O(N*M) + O(N*M*4)
        // SC : O(N*M) + O()
        
        int sr = source[0];
        int sc = source[1];
        
        int er = destination[0];
        int ec = destination[1];
        
        if(grid[sr][sc]==0 || grid[er][ec]==0) return -1;

        Queue<Pair> q = new LinkedList<>();
        
        int n = grid.length;
        int m = grid[0].length;
        
        int[][] dis = new int[n][m];
        
        for(int[] row : dis) Arrays.fill(row, (int)1e8);
        
        
        dis[sr][sc] = 0;
        q.offer(new Pair( sr , sc , 0));
        
        while(!q.isEmpty()){
            
            int r = q.peek().r;
            int c = q.peek().c;
            int d = q.peek().dis;
            
            q.poll();
            
            if(r==er && c==ec) return d;
            
            for(int i=0;i<4;i++){
                
                int nr = r + ra[i];
                int nc = c + ca[i];
                
                if(nr>=0 && nr<n && nc>=0 && nc<m && grid[nr][nc]==1 && d+1 < dis[nr][nc]){
                    
                    dis[nr][nc] = d+1;
                    q.offer(new Pair(nr,nc,dis[nr][nc]));
                }
            }
        }
        
        return -1;
        
    }
}
