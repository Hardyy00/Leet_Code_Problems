class Solution {

    static int[] ra = {-1,0,1,0};
    static int[] ca = {0,1,0,-1};

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {

        int m = image.length;
        int n = image[0].length;

        int[][] ans = new int[m][n];
        
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++) ans[i][j] = image[i][j];
        }

        // dfs(sr,sc,color,ans,image, image[sr][sc]);

        dfs(sr,sc, color, image[sr][sc], ans);

        // bfs(sr,sc,color,ans,image);

        return ans;
    }

    private void bfs(int sr, int sc, int color,int[][] ans, int[][] image){

        // TC : O(M*N)
        // SC : O(M*N) + O(M*N) (for ans matrix + queue nearly at max may contains m*n nodes )

        int m = image.length;
        int n = image[0].length;

        // queue contains the nearest (row,column) cells
        Queue<Pair<Integer,Integer>> queue = new LinkedList<>();

        queue.offer(new Pair(sr,sc));
        ans[sr][sc] = color;

        int matchColor = image[sr][sc];     // storing the color for which we will visit cells

        while(!queue.isEmpty()){

            int r = queue.peek().getKey();
            int c = queue.peek().getValue();

            queue.poll();

            for(int i=0;i<4;i++){

                int nr = r + ra[i];
                int nc = c + ca[i];

                // visit only those cell which has same color as the starting cell and are not already colored
                if(nr>=0 && nc>=0 && nr < m && nc < n && image[nr][nc]==matchColor && ans[nr][nc]!=color){
                    // since we inserted them in the queue, we will  definiitely visit them , so mark
                    // them true   
                    queue.offer(new Pair(nr,nc));
                    ans[nr][nc] = color;
                }
            }
        }

    }

    private void dfs(int r, int c, int col, int originalCol, int[][] ans){

        ans[r][c] = col;

        for(int i=0;i<4;i++){

            int nr = r + ra[i], nc = c + ca[i];

            if(nr>=0 && nr < ans.length && nc>=0 && nc < ans[0].length && ans[nr][nc] != col && ans[nr][nc]==originalCol){

                dfs(nr,nc,col, originalCol, ans);
            }
        }
    }

    private void dfs(int r, int c, int color,int[][] ans,int[][] image, int matchColor){

        // TC : O(M*N) + O(M*N *4)       (N^2 for the outer loop  + N^2 * 4 for the overall dfs as we are overall visiting all)
        // the nodes with one
        
        // SC : O(M*N) (for ans matrix) + O(M*N) (approx recusion stack in case of full 1 matrix)
        
        // basically , we are calculting no of components, such that applying dfs to one component does not reach another

        int m = image.length;
        int n = image[0].length;

        ans[r][c] = color;

        for(int i=0;i<4;i++){

            int nr = r + ra[i];
            int nc = c + ca[i];

            // visit only those cell which has same color as the starting cell and are not already colored
            if(nr>=0 && nr<m && nc>=0 && nc<n && image[nr][nc]==matchColor && ans[nr][nc]!=color){

                dfs(nr,nc,color,ans,image, matchColor);
            }
        }
    }
}