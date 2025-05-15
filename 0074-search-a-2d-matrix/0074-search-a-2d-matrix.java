class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        
        // return bruteForce(matrix, target);

        // return betterSolution(matrix, target);

        // return optimalSolution(matrix, target);

        return  mostOptimalSolution(matrix, target);
    }

    private boolean mostOptimalSolution(int[][] matrix, int target){

        // Apply binary search on the whole matrix, with low = 0, and high = totalElements-1
        // for the comparison , convert the mid to coordinates, row = mid / cols , col = mid % cols

        // TC : O(log (m *n))
        // SC : O(1)

        int low = 0;
        int high = (matrix.length * matrix[0].length) -1;


        while(low <= high){

            // int mid = (low + high)/2;

            int mid = high + (low - high)/2;

            int row = mid / matrix[0].length;
            int col = mid % matrix[0].length;

            if(matrix[row][col] < target){
                low = mid+1;
            }else if(matrix[row][col] > target){
                high = mid-1;
            }else{

                return true;
            }
        }

        return false;


    }

    private boolean optimalSolution(int[][] matrix, int target){

        // Stand on the top right corner , and check if target is less than current element , if yes then
        // move back in the row, cuz all elements behind are smaller, and if target is greater then move downwards,
        // cuz all the element below are greater

        // TC : O(M+N)
        // SC : O(1)

        int i=0;
        int j=matrix[0].length-1;

        while(j > - 1 && i < matrix.length){

            if(matrix[i][j] == target) return true;
            else if(target < matrix[i][j]) j--;
            else if (target > matrix[i][j]) i++;
        }
 
        return false;
    }

    private boolean betterSolution(int[][] matrix, int target){

        // Apply binary search on every row , since every row is sorted

        // TC : O(m * logN)
        // SC : O(1)

        for(int i=0;i<matrix.length;i++){

            if(binarySearch(matrix,i, target)) return true;
        }

        return false;
    }

    private boolean binarySearch(int[][] matrix, int row, int target){


        int low = 0;
        int high = matrix[0].length-1;

        while(low <= high){

            int mid = high + (low - high)/2;

            if(matrix[row][mid] < target) low = mid+1;
            else if(matrix[row][mid] > target) high = mid-1;
            else return true; 
        }

        return false;
    }

    private boolean bruteForce(int[][] matrix, int target){

        // Brute Force : Traverse the whole matrix 
        // TC : O(m * n)
        // SC : O(1)

        for(int i=0;i<matrix.length;i++){

            for(int j=0;j<matrix[0].length;j++){

                if(matrix[i][j] == target) return true;
            }
        }

        return false;
    }
}