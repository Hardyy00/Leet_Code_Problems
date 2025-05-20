class Solution {
    public void solveSudoku(char[][] board) {
        
        solver(0,0,board, board.length);
    }

    private boolean solver(int row, int col, char[][] board, int n){

        // TC : O(9^(N*N))
        // SC :O(N*m) (recursion stack)

        if(col==n) {
            row++;
            col =0;
        }

        if(row==n){
            return true;
        }

        if(Character.isDigit(board[row][col])){

            if( solver(row,col+1,board,n) ) return true;
        }else{

            for(char ch='1';ch<='9';ch++){

                if(canPut(ch,row,col,board)){
                    board[row][col] = ch;
                    
                    if( solver(row,col+1,board,n)) return true;

                    board[row][col] = '.';
                }
            }
        }

        return false;
    }

    private boolean canPut(char ch, int row, int col,char[][] board){


        int sr = 3*(row/3);
        int sc = 3*(col/3);

        for(int i=0;i<9;i++){

            if(board[row][i] == ch) return false;

            if(board[i][col] == ch) return false;

            if(board[sr + i/3][sc + i%3] == ch) return false;
        }

        return true;
    }
}