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
    public int[][] updateMatrix(int[][] mat) {

        // Applying BFS
        // TC : O(M*N) + O(M*N*4)
        // SC : O(N*M)

        // for a 0 , visit all neighbor 1, and assign them the distance from the current node
        
        int[] ra = {-1,0,1,0};
        int[] ca = {0,1,0,-1};

        int m = mat.length;
        int n = mat[0].length;

        int[][] ans = new int[m][n];    

        for(int[] row: ans) Arrays.fill(row, -1);   // initially no distance has been recorded

        Queue<Pair> queue = new LinkedList<>();

        for(int i=0;i<m;i++){

            for(int j=0;j<n;j++){

                if(mat[i][j]==0){

                    queue.offer(new Pair(i,j,0));  // if found zero, then store it coordinates, and the distance
                    // of it from the nearest zero i.e 0
                    ans[i][j] = 0;  // assign the distance in answer matrix
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

                // visit a cell, only if it is one , and is not visited i.e ans[nr][nc] is still -1
                if(nr>=0 && nr < m && nc>=0 && nc<n && mat[nr][nc]==1 && ans[nr][nc]==-1){

                    queue.offer(new Pair(nr,nc,dis+1));  // push the coordinates and increment the distance from 0
                    ans[nr][nc] = dis+1;    // store the answer as visted
                }
            }
        }

        return ans;

    }
}