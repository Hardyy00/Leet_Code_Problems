class Solution {
    public List<List<String>> solveNQueens(int n) {

        char[][] board = new char[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                board[i][j] = '.';
            }
        }

        int[] rowCheck = new int[n];
        int[] upperDiagonalCheck = new int[2*n-1];
        int[] lowerDiagonalCheck = new int[2*n-1];
        List<List<String>> ans = new ArrayList<>();

        placeQueens(0,n,board,rowCheck,upperDiagonalCheck,lowerDiagonalCheck,ans);

        return ans;
        
    }

    private static void placeQueens(int col,int n,char[][] board,int[] rowCheck,int[] upperDiagonalCheck,int[] lowerDiagonalCheck,List<List<String>> ans){


        if(col==n){
            ans.add(convertIntoList(board));
            return;
        }

        for(int row=0;row<n;row++){

            if(rowCheck[row]==1 || upperDiagonalCheck[n-1+col-row]==1 || lowerDiagonalCheck[row+col]==1)
                continue;

            rowCheck[row]++;
            upperDiagonalCheck[n-1+col-row]++;
            lowerDiagonalCheck[row+col]++;
            board[row][col] = 'Q';
            placeQueens(col+1,n,board,rowCheck,upperDiagonalCheck,lowerDiagonalCheck,ans);
            rowCheck[row]--;
            upperDiagonalCheck[n-1+col-row]--;
            lowerDiagonalCheck[row+col]--;
            board[row][col] = '.';

        }
    } 

    private static List<String> convertIntoList(char[][] board){

        List<String> list = new ArrayList<>();

        for(int i=0;i<board.length;i++){
            
            String s = new String(board[i]);
            list.add(s);
        }

        return list;
    }
}