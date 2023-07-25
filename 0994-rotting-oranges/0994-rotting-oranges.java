class Solution {

    static int[] ra = {-1,0,1,0};
    static int[] ca = {0,1,0,-1};

    static class Pair{

        int r;  // row
        int c;  // column
        int t;  // time at which an orange is rotten

        public Pair(int r, int c, int t){
            this.r = r;
            this.c = c;
            this.t = t;
        }
    }


    public int orangesRotting(int[][] grid) {

        // TC : O(N*M) + O(N*M*4)
        // SC : O(N*M) + O(N*M) (visit matrix + queue)

        int m = grid.length;
        int n = grid[0].length;

        boolean[][] visit = new boolean[m][n];

        Queue<Pair> queue = new LinkedList<>();

        int fresh = 0;

        for(int i=0;i<m;i++){

            for(int j=0;j<n;j++){

                if(grid[i][j]==2){
                    
                    // get all already rotten oranges, and they are rotten at time 0
                    queue.offer(new Pair(i,j,0));

                    visit[i][j] = true;     // we have visitted them
                        
                }else if(grid[i][j]==1){
                    fresh++;
                }
            }
        }


        int max = 0;

        while(!queue.isEmpty()){

            int r = queue.peek().r;
            int c = queue.peek().c;
            int t = queue.peek().t;

            queue.poll();

            max = Math.max(max,t);      // maximum time of rotting oranges

            for(int i=0;i<4;i++){

                int nr = r + ra[i];
                int nc = c + ca[i];

                // visit only if an orange is freah and not visited
                if(nr>=0 && nc>=0 && nr < m && nc < n && !visit[nr][nc] && grid[nr][nc]==1){

                    visit[nr][nc] = true;

                    // we are rotting it at +1 minute from the current rotten orange hence t +1
                    // cause current orange is rotten at tth minute
                    queue.offer(new Pair(nr,nc,t+1));

                    fresh--; // the fresh orange is no longer fresh
                }
            }
        
        }

        // // if still a fresh orange is left , after rotting oranges, then return -1
        // for(int i=0;i<m;i++){

        //     for(int j=0;j<n;j++){

        //         if(grid[i][j]==1 && !visit[i][j]) return -1;
        //     }
        // }

        if(fresh!=0) return -1;     // not all the oranges are made rotton, so some are still fresh 

        return max;
        
    }
}