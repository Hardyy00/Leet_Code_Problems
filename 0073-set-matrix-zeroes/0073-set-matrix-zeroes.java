class Solution {
    public void setZeroes(int[][] matrix) {
        // solve(matrix);
        // solve2(matrix);

        setMatrix3(matrix);

    }

    private void setMatrix3(int[][] matrix){

        // Use the first row and first column to store which rows and column do i have to set to zero
        // if matrix[i][0] == 0, then set row i to 0, if matrix[0][i] == 0, set column i to zero

        // handle the cases of mat[0][0], cuz it will make whole row0 and col0 to zero (destroy our storage)
        // and use col 0, to set only rows, hence to keep track of col0, use a separate variable 

        // TC : O(N*M) + O(N*M) + O(N*M) + (N+M)
        // SC : O(1)

        int col0 = 0;

        int n= matrix.length;
        int m = matrix[0].length;

        for(int i=0;i<n;i++){

            for(int j=0;j<m;j++){

                if(matrix[i][j]==0){

                    matrix[i][0] = 0;

                    if(j==0) col0=1;
                    else{
                        matrix[0][j] = 0;
                    }
                }
            }
        }

        

        for(int i=1;i<m;i++){

            if(matrix[0][i]==0){
                for(int j=0;j<n;j++){
                    matrix[j][i] = 0;
                }
            }
        }

        for(int i=1;i<n;i++){

            if(matrix[i][0]==0){

                for(int j=0;j<m;j++) matrix[i][j] = 0;
            }
        }

        if(matrix[0][0]==0) for(int i=0;i<m;i++) matrix[0][i] = 0;
        
        if(col0 == 1) for(int i=0;i<n;i++) matrix[i][0] = 0;
    }

    private void solve2(int[][] matrix){

        // Store for which row and which column, i have to change to zeros
        // After it change them separately, this will avoid the redundancy(in case all of the elements are equal) 

        // TC : O(N*M)+ O(N*M) + O(N*M)
        // SC : O(N+M)

        int n= matrix.length;
        int m= matrix[0].length;


        int[] row = new int[n];
        int[] col = new int[m];

        for(int i=0;i<n;i++){

            for(int j=0;j<m;j++){

                if(matrix[i][j] == 0){
                    row[i] = 1;
                    col[j] = 1;
                }
            }
        }


        for(int i=0;i<n;i++){

            if(row[i]==1){
                for(int j=0;j<m;j++) matrix[i][j] = 0;
            }
        }
        
        for(int i=0;i<m;i++){

            if(col[i]==1){

                for(int j=0;j<n;j++) matrix[j][i] = 0;
            }
        }
    }
    private void solve(int[][] matrix){
        // Brute Force 
        // making a copy matrix , whenever i am finding a 0, in original matix, make every in that row and that column
        // zero

        // TC : O(N*M) *(n+m)
        // SC : O(N*M)
        
        int n = matrix.length;
        int m = matrix[0].length;

        int[][] copy = new int[n][m];

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++) copy[i][j] = matrix[i][j];
        }

        for(int i=0;i<n;i++){

            for(int j=0;j<m;j++){

                if(copy[i][j]==0){
                    setZero(i,j,matrix);
                }
            }
        }
    }

    private void setZero(int row, int col, int[][] mat){

        int n = mat.length;
        int m = mat[0].length;

        for(int i=0;i<m;i++){

            mat[row][i] = 0;
        }

        for(int i=0;i<n;i++){

            mat[i][col] = 0;
        }

    }
}