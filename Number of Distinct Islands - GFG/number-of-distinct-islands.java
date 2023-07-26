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

            Solution ob = new Solution();
            int ans = ob.countDistinctIslands(grid);
            System.out.println(ans);
        }
    }
}
// } Driver Code Ends


// User function Template for Java

class Solution {
    
    int[] ra = {-1,0,1,0};
    int[] ca = {0,1,0,-1};
    
    private void dfs(int r, int c, int[][] grid, boolean[][] visit, ArrayList<String> list, int baseRow, int baseCol){
        
        int n = grid.length;
        int m = grid[0].length;
        
        visit[r][c]= true;
        
        list.add(Integer.toString(r-baseRow) + " " + Integer.toString(c-baseCol));
        
        for(int i=0;i<4;i++){
            
            int nr = r + ra[i];
            int nc = c + ca[i];
            
            if(nr>=0 && nr < n && nc>=0 && nc < m && grid[nr][nc]==1 && !visit[nr][nc]){
                
                dfs(nr,nc,grid,visit,list,baseRow,baseCol);
            }
        }
    }

    int countDistinctIslands(int[][] grid) {
        
        int n = grid.length;
        int m = grid[0].length;
        
        boolean[][] visit = new boolean[n][m];
        
        Set<ArrayList<String>> set = new HashSet<>();
        
        for(int i=0;i<n;i++){
            
            for(int j=0;j<m;j++){
                
                if(grid[i][j]==1 && !visit[i][j]){
                
                    ArrayList<String> list = new ArrayList<>();
                    
                    dfs(i,j,grid,visit,list,i,j);
                    
                    set.add(list);
                }
            }
        }
        
        return set.size();
        
        
    }
}
