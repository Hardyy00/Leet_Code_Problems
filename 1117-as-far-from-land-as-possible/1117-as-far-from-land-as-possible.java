class Pair{

    int row;
    int col;

    public Pair(int row , int col){

        this.row = row;
        this.col = col;
    }
}
class Solution {
    public int maxDistance(int[][] grid) {
        
        int n = grid.length;
        int[][] mat = new int[n][n];

        for(int[] row : mat) Arrays.fill(row, (int) 1e6);

        boolean zero = false;
        Queue<Pair> q = new LinkedList<>();

        for(int i=0;i<n;i++){

            for(int j=0;j<n;j++){

                if(grid[i][j] == 1){

                    q.offer(new Pair(i,j));
                    mat[i][j] = 0;
                }else{

                    zero = true;
                }


            }
        }

        if(q.isEmpty() || !zero ) return -1;

        int[] ra = {-1,0, 1, 0};

        int[] ca = {0, 1,0,-1};

        while(!q.isEmpty()){

            int row = q.peek().row;
            int col = q.peek().col;

            q.poll();

            for(int i=0;i<4;i++){

                int nr = row + ra[i];
                int nc = col + ca[i];

                if(nr>=0 && nr< n && nc>=0 && nc< n && grid[nr][nc]==0 && mat[row][col] + 1 < mat[nr][nc]){
                    
                    mat[nr][nc] = mat[row][col] + 1;
                    q.offer(new Pair(nr, nc));

                }
            }

        }

        int maxi = 0;

        for(int i=0;i<n;i++) for(int j=0;j<n;j++) maxi = Math.max(maxi, mat[i][j]);

        return maxi;
    }
}