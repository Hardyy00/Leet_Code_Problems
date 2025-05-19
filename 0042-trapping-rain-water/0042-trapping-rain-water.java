class Solution {
    public int trap(int[] height) { 

        return bestSolution(height);
        
    }

    private int bestSolution(int[] height){ 
        
        // for each block i only need to know the minimum of the maximum  left and right boundary
        // no matter where there poistion are,since i only work with minimum, so increasing and decreasing
        // only on finding minimum on any end.

        // TC : O(N)
        // SC : O(1)

        int sum =0;
        int leftMaximum = Integer.MIN_VALUE;
        int rightMaximum = Integer.MIN_VALUE;

        int water = 0;
        int n= height.length;
        int i=0,j=n-1;

        while(i<j){

            leftMaximum = Math.max(leftMaximum, height[i]);
            rightMaximum = Math.max(rightMaximum, height[j]);

            water += leftMaximum < rightMaximum ? (leftMaximum - height[i++]) : (rightMaximum - height[j--]);
        }

        return water;
    }

    private int betterSolution(int[] height){
        // Approach : We will we able to fill a water for a particular level, till the height of the 
        // of the boundary, boundary refers to the maximum left and maximum right, and water will fill till
        // the height of minimum among those maximum, so calc leftMaximum and rightMaximum for every element
        // Instead of maintaining both leftMaximum and rightMaximum, only calculate one,
        // and calculate one in running. 

        // TC : O(N)
        // SC : O(N)

        int n = height.length;

        int[] rightMaximum = new int[n];

        rightMaximum[n-1] = height[n-1];

        for(int i=n-2;i>=0;i--){
            rightMaximum[i] = Math.max(rightMaximum[i+1], height[i]);
        }

        int leftMaximum = 0;
        int water = 0;

        for(int i=0;i<n;i++){

            leftMaximum = Math.max(leftMaximum,height[i]);

            water += Math.min(leftMaximum, rightMaximum[i])- height[i];
        }

        return water;
    }
}