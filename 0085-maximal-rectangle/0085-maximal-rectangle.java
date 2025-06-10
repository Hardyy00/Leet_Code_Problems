class Solution {
    public int maximalRectangle(char[][] matrix) {

        // TC : O(R*(C +C ) SC : O(C) // O(N) for stack
        int maxArea = getMaximumBarArea(matrix[0]);

        // consider everyrow as consisting of blocks
        for(int i=1;i<matrix.length;i++){

            for(int j=0;j<matrix[0].length;j++){

                if(matrix[i][j]!='0'){
                    matrix[i][j] += matrix[i-1][j]-'0';
                }
            }

            maxArea = Math.max(maxArea,getMaximumBarArea(matrix[i]));
        }

        return maxArea;
        
    }

    private static int getMaximumBarArea(char[] arr){

        // TC : O(N)
        // SC : O(N)

        Deque<Integer> stack = new ArrayDeque<>();  
        int maxArea = Integer.MIN_VALUE;

        for(int i=0;i<arr.length;i++){

            while(!stack.isEmpty() && arr[stack.peek()]>=arr[i]){

                int currBar = stack.pop();
                int ar = arr[currBar]-'0';
                int area =  stack.isEmpty() ? i*ar : (i-stack.peek()-1)*ar;
                maxArea = Math.max(maxArea,area);
            }

            stack.push(i);
        }

        while(!stack.isEmpty()){
            
            int currBar = stack.pop();
            int ar = arr[currBar]-'0';
            int area = stack.isEmpty() ? arr.length*ar : (arr.length-stack.peek()-1)*ar;
            maxArea = Math.max(maxArea,area);
        }

        return maxArea;
    }
}