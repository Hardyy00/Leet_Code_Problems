class Solution {

    int[] ra = {-1,0,1,0};
    int[] ca = {0,1,0,-1};

    public void solve(char[][] board) {

        // finding those 'O' , which cannot be made X, such are those O which are at the boundary
        // and all the O which are connected to O at the boundary
        // hence from each boundary O, visiting all the connected O, and marking them, so that they
        // wont become X, 

        // There are 4 boundaries , but 2 are parallel and 2 are parallel , hence only 2 loop are required

        // TC : O(N) + O(M) + o(M*N*4)  
        // SC : O(M*N) + o(M*N)   

        int m = board.length;
        int n = board[0].length;

        boolean[][] visit = new boolean[m][n];  // to mark the boundary connected O

        for(int i=0;i<n;i++){
            
            // first row boundary
            if(board[0][i]=='O' && !visit[0][i]){   // only call for the O , which is not visited
                dfs(0,i,board,visit);
            }

            // last row  boundary
            if(board[m-1][i]=='O' && !visit[m-1][i]){
                dfs(m-1,i,board,visit);
            }
        }

        for(int i=0;i<m;i++){

            // first column boundary
            if(board[i][0]=='O' && !visit[i][0]){

                dfs(i,0,board,visit);
            }

            // last column boundary
            if(board[i][n-1]=='O' && !visit[i][n-1]){

                dfs(i,n-1,board,visit);
            }
        }


       for(int i=0;i<m;i++){

           for(int j=0;j<n;j++){
            
               // 'O' which are not visited are surrounded by 'X', hence make them 'X'
               if(board[i][j]=='O' && !visit[i][j]) board[i][j]='X';
           }
       }
        
    }

    private void dfs(int r, int c , char[][] board, boolean[][] visit){

        int m = board.length;
        int n = board[0].length;

        visit[r][c] = true;

        for(int i=0;i<4;i++){

            int nr = r + ra[i];
            int nc = c + ca[i];

            if(nr>=0 && nr<m && nc>=0 && nc<n && board[nr][nc]=='O' && !visit[nr][nc]){

                dfs(nr,nc,board,visit);
            }
        } 
    }
}