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

    public int shortestPathBinaryMatrix(int[][] grid) {
        
        int n = grid.length;
        if(grid[0][0]==1 || grid[n-1][n-1]==1 ) return -1;

        if(n==1) return 1;

        int[] ra = {-1,-1,-1,0,1,1,1,0};
        int[] ca = {-1,0,1,1,1,0,-1,-1};

        Queue<Pair> queue = new LinkedList<>();

        int[][] dis = new int[n][n];

        for(int[] row : dis) Arrays.fill(row, (int)1e8);

        dis[0][0] = 0;

        queue.offer(new Pair(0,0,1));

        while(!queue.isEmpty()){

            int r = queue.peek().r;
            int c = queue.peek().c;
            int d = queue.peek().dis;

            queue.poll();

            for(int i=0;i<8;i++){
                int nr = r + ra[i];
                int nc = c + ca[i];

                if(nr>=0 && nr<n && nc>=0 && nc<n && grid[nr][nc]==0 && d+1 < dis[nr][nc]){

                    dis[nr][nc] = d+1;

                    if(nr==n-1 && nc==n-1) return dis[nr][nc];
                    
                    queue.offer(new Pair(nr,nc, dis[nr][nc] ));
                }
            }
        }

        return -1;
    }
}