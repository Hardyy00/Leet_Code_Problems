//{ Driver Code Starts
import java.util.*;
import java.lang.*;
import java.io.*;
class GFG
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        while(T-->0)
        {
            String[] s = br.readLine().trim().split(" ");
            int n = Integer.parseInt(s[0]);
            int m = Integer.parseInt(s[1]);
            int[][] grid = new int[n][m];
            for(int i = 0; i < n; i++){
                String[] S = br.readLine().trim().split(" ");
                for(int j = 0; j < m; j++){
                    grid[i][j] = Integer.parseInt(S[j]);
                }
            }
            Solution obj = new Solution();
            int[][] ans = obj.nearest(grid);
            for(int i = 0; i < ans.length; i++){
                for(int j = 0; j < ans[i].length; j++){
                    System.out.print(ans[i][j] + " ");
                }
                System.out.println();
            }
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

class Solution
{
    //Function to find distance of nearest 1 in the grid for each cell.
    public int[][] nearest(int[][] grid)
    {
        
        int[] ra = {-1,0,1,0};
        int[] ca = {0,1,0,-1};
        
        Queue<Pair> queue = new LinkedList<>();
        
        int n = grid.length;
        int m = grid[0].length;
        
        int[][] ans = new int[n][m];
        
        for(int[] row : ans) Arrays.fill(row,-1);
        
        for(int i=0;i<n;i++){
            
            for(int j=0;j<m;j++){
                
                if(grid[i][j]==1){
                    
                    queue.offer(new Pair(i,j,0));
                    ans[i][j] = 0;
                }
            }
        }
        
        while(!queue.isEmpty()){
            
            int r = queue.peek().r;
            int c = queue.peek().c;
            int dis = queue.peek().dis;
            
            queue.poll();
            
            for(int i=0;i<4;i++){
                
                int nr = r + ra[i];
                int nc = c + ca[i];
                
                if(nr>=0 && nr<n && nc>=0 && nc<m && grid[nr][nc]==0 && ans[nr][nc]==-1){
                    
                    queue.offer(new Pair(nr,nc,dis+1));
                    
                    ans[nr][nc] = dis+1;
                }
            }
        }
        
        return ans;
        
    }
}