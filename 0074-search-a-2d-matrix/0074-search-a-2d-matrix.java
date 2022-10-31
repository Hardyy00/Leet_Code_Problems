class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        
        boolean doesExist = false;
        
        for(int i=0;i<matrix.length;i++){
            
            if(target>=matrix[i][0] && target<=matrix[i][matrix[0].length-1]){
                return searchTarget(matrix[i] , target);
            }
        }
        
        return false;
        
    }
    
    private static boolean searchTarget(int[] arr , int target){
        
        int lower = 0;
        int higher = arr.length-1;
        
        while(lower<=higher){
            
            int mid = lower + (higher-lower)/2;
            
            if(arr[mid] < target){
                lower = mid+1;
            }else if(arr[mid] > target){
                higher = mid -1;
            }else{
                return true;
            }
        }
        
        return false;
    }
}