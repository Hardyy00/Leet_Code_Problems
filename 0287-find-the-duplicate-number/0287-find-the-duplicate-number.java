class Solution {
    public int findDuplicate(int[] nums) {
        
        // return bruteForce(nums);
        // return optimalApproach(nums);

        return mostOptimalApproach(nums);
    }


    private int mostOptimalApproach(int[] nums){

        // since there is a duplicate number hence , a cycle must exist in the array
        // so using floydd cycle detection to first find that meeting point. and after it find the starting point of
        // cycle

        // we are moving in the array , according to the index values, cuz there are repeating at some point
        // so they allow us to come back in the array

        // TC : O(N)
        // SC : O(1)

        int slow = nums[0]; 


        int fast = nums[0];

        while(true){

            slow = nums[slow];  // move by 1
            fast = nums[nums[fast]];  // move by 2

            if(slow==fast) break;
        }

        slow = nums[0];  // reset the slow

        /// find the starting of the cycle
        while(slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }

        return slow;
    }

    private int optimalApproach(int[] nums){

        // Try to put number in their position, like 1 to 1st position , 2 to 2nd position, etc
        // if we are trying to put a number to its postion and it is already present there , then its
        // a duplicate number

        // TC : O(2N) at most all the element will be swapped on a single element and one for traversing the whole
        /// array

        // SC : O(1)

        int n= nums.length;
        int i=0;

        while(i<n){

            // if the number is at its position then continue
            if(nums[i]==i+1){
                i++;
            }else{

                // if the number , is already present at its swapping place then its a duplicate number
                if(nums[nums[i]-1]==nums[i]) return nums[i];

                else{
                    int temp = nums[nums[i]-1];  // else swap the numbers
                    nums[nums[i]-1] = nums[i];
                    nums[i] = temp;
                }
            }
        }

        return -1;
    }

    private int bruteForce(int[] nums){

        // Brute Force : run two loops, to check if two elements are equal or not

        // TC : O(N^2)
        // SC : O(1)


        int n = nums.length;

        for(int i=0;i<n;i++){

            for(int j=i+1;j<n;j++) {

                if(nums[i] == nums[j]) return nums[i];
            }
        }

        return -1;
    }
}