class Solution {
    public int rob(int[] nums) {

        if(nums.length==1) return nums[0];

        // the answers must not contain index n-1 &&  and index 0 together
        // so excluding one element when the other is present
        // and take the maxmium of both of them

        // TC : O(N) && SC : O(N)
        
        // contains 1 to n-1
        int[] arr1 = new int[nums.length-1];
        // contains 0 to n-2
        int[] arr2 = new int[nums.length-1];

        for(int i=0;i<nums.length;i++){

            if(i>0) arr1[i-1] = nums[i];

            if(i!=nums.length-1) arr2[i]= nums[i];
        }

        int max1 = houseRob(arr1);
        int max2 = houseRob(arr2);

        return Math.max(max1,max2);
    }

    int houseRob(int[] arr){

        // Tc : O(N) && SC : O(1)
        int pre1 = arr[0];
        int pre2 = 0;

        for(int i=1;i<arr.length;i++){

            int pick = arr[i] + pre2;
            int notPick = pre1;
            
            int curr = Math.max(pick,notPick);

            pre2 = pre1;
            pre1 = curr;
        }

        return pre1;
    }
}