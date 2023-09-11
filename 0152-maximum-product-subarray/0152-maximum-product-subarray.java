class Solution {
    public int maxProduct(int[] nums) {

        int n = nums.length;
        int max = Integer.MIN_VALUE;

        int prefixProduct = 1;
        int suffixProduct = 1;

        for(int i=0;i<n;i++){

            
            prefixProduct *= nums[i];

            suffixProduct *= nums[n-1-i];

            max = Math.max(max, Math.max(prefixProduct, suffixProduct));

            if(prefixProduct == 0) prefixProduct = 1;
            if(suffixProduct == 0) suffixProduct = 1;
        }

        return max;
        
    }
}