class Solution {
    public void sortColors(int[] nums) {

        // Dutch national flag algo

        // try to sort the 0 and 2, 1 will already be sorted then.For that maintain 3 pointers
        // 1 for 2 , 1 for 0 and 1 for traversing the whole array , on finding 2 swap it to right
        // on finding 0 swap it to left (and move both pointers)

        // TC :O(N)
        // SC : O(1)
        
        int i =0;
        int j= 0;
        int k = nums.length-1;

        while(j<=k){

            // on finding 2 , move it to right side and decrement the kth pointer , not j cuz we also have to see
            // what did we bring from kth element
            if(nums[j]==2){
                int temp = nums[k];
                nums[k] = nums[j];
                nums[j] = temp;
                k--;
            }else if(nums[j]==0){

                // on finding 0, move it to the ith pointer
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;

                i++;  // after inserting 0, move the ith pointer
                j++;  // there will definitely be a 1, now at jth pointer, so just move from it
            }else {
                j++;  // cuz 1 is at jth pointer, just move away
            }
        }
    }
}