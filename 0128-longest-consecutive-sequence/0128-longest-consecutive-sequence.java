class Solution {
    public int longestConsecutive(int[] nums) {

        return optimalSolution(nums);
        
    }

    private int optimalSolution(int[] nums){

        // Start iterating over the elements from the start of the sequence (start of sequence is when set does not
        //  contains num-1) and on tarversing remove those elements, so we can skip over those in the future

        // TC : O(2N)
        // sc : O(N)

        Set<Integer> set =new HashSet<>();

        for(int i : nums) set.add(i);

        int maxi = 0;

        for(int i : nums){


            if(!set.contains(i)) continue;  // element already traversed so skip over it

            if(!set.contains(i-1)){

                int count = 1;

                set.remove(i);

                while(set.contains(i+1)){
                    set.remove(i+1);
                    i++;
                    count++;
                }

                maxi = Math.max(maxi, count);
            }
        }

        return maxi;
    }

    private int bruteForce(int[] nums){

        // sort the elements first , and then check them,  for cases like this 0 1 1 2 2, skip over the equal elements
        // to not include them in the sequence

        // TC : O(N log N)
        // SC : O(1)
        if(nums.length==0) return 0;

        Arrays.sort(nums);

        int maxi = 0;
        int count =1;

        int n = nums.length;

        for(int i=1;i<n;i++){
            
            // skip over equal elements
            if(nums[i]==nums[i-1]) continue;
            if(nums[i]==1+nums[i-1]) count++;
            else{

                maxi = Math.max(maxi,count);
                count = 1;
            }
        }

        maxi = Math.max(maxi,count);

        return maxi;
    }
}