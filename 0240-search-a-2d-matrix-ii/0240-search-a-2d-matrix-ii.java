class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        
        int row = 0;
		int column = matrix[0].length-1;
		
		while(row<matrix.length && column>-1) {
			
			if(target < matrix[row][column]) {
				column--;
			}else if(target > matrix[row][column]) {
				row++;
			}else {
				return true;
			}
		}
		
		return false;
        
        
        
    }
    
}