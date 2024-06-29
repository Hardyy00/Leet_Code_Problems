class Solution {
    public int minimumOperations(int[] nums) {

        int op = 0;

        for(int i : nums){

            op += Math.min( ((i / 3) + 1) *3 - i, Math.abs((i / 3 )* 3  - i));
        }

        return op;
        
    }
}