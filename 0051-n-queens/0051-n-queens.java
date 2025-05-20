class Solution {
    public List<List<String>> solveNQueens(int n) {

        // TC : O(N!)  (for first col, n choice, for 2nd n-2 (on average) , similarly n-4 on next)
        // SC : O(N) + O(4n) + O(N*N)
        
        int[] visit = new int[n];
        int[] upperDiagonal = new int[2*n-1];
        int[] lowerDiagonal = new int[2*n-1];

        List<List<String>> answer= new ArrayList<>();
        char[][] board = new char[n][n];

        for(char[] row : board) Arrays.fill(row,'.');


        nQueens(0,visit,upperDiagonal, lowerDiagonal, n, board, answer);

        return answer;
    }

    private void nQueens(int col,int[] visit, int[] upperDiagonal, int[] lowerDiagonal,int n, char[][] board,List<List<String>> answer){


        if(col==n) {

            makeAnswer(board,answer);
            return;
        }

        for(int row=0;row<n;row++){

            if(visit[row] == 0 && upperDiagonal[col-row +n-1] ==0 && lowerDiagonal[row + col]==0){

                visit[row]=1;
                upperDiagonal[col-row + n-1] = 1;
                lowerDiagonal[row + col] = 1;
                board[row][col] = 'Q';
                nQueens(col+1,visit, upperDiagonal, lowerDiagonal, n, board, answer);
                visit[row]=0;
                upperDiagonal[col-row + n-1] = 0;
                lowerDiagonal[row + col] = 0;
                board[row][col] = '.';

            }
        }
    }

    private void makeAnswer(char[][] board, List<List<String>> answer){

        List<String> list =new ArrayList<>();

        for(int i=0;i<board.length;i++){
            String row = new String(board[i]);
            list.add(row);
        }

        answer.add(list);
    }
}