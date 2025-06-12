class Solution {

    int[] ra = {-1,0,1,0};
    int[] ca = {0,1,0,-1};
    public int numEnclaves(int[][] grid) {

        // TC : O(M) + O(N) + O(M*N*4) + O(M*N) == O(M*N)(loops + dfs + loop)
        // SC : O(M*N) + O(M*N)   (visit + recursion stack)


        // from the 1's at boundary, visit all the 1's that are connected to it, in this way
        // we fill the number of land from which we can cross to boundary, and the rest of the 1
        // which are not visited , will be our answer

        int m = grid.length;
        int n = grid[0].length;

        boolean[][] visit = new boolean[m][n];


        // go through all boundary , and call dfs for the 1's at the boundaries
        for(int i=0;i<n;i++){

            // first row
            if(grid[0][i]==1 && !visit[0][i]){
                dfs(0,i,grid,visit);
            }

            // last row
            if(grid[m-1][i]==1 && !visit[m-1][i]){
                dfs(m-1,i,grid,visit);
            }
        }

        for(int i=0;i<m;i++){

            // first column
            if(grid[i][0]==1 && !visit[i][0]){

                dfs(i,0,grid,visit);
            }

            // last column
            if(grid[i][n-1]==1 && !visit[i][n-1]){
                dfs(i,n-1,grid,visit);
            }
        }


        int count = 0;      // count all the lands there were not visited

        for(int i=0;i<m;i++){

            for(int j=0;j<n;j++){

                if(grid[i][j]==1 && !visit[i][j]) count++;
            }
        }

        return count;
    }

    private void dfs(int r, int c, int[][] grid, boolean[][] visit){

        int m = grid.length;
        int n = grid[0].length;

        visit[r][c] = true;

        for(int i=0;i<4;i++){

            int nr = r + ra[i];
            int nc = c + ca[i];

            if(nr>=0 && nr<m && nc>=0 && nc<n && grid[nr][nc]==1 && !visit[nr][nc]){

                dfs(nr,nc,grid,visit);
            }
        }
    }
}