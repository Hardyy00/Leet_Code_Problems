class Solution {
    public int longestConsecutive(int[] nums) {
        
        if(nums.length==0) return 0;
        if(nums.length==1) return 1;

        Set<Integer> set = new HashSet<>();

        for(int i : nums){
            set.add(i);
        }

        int longestSeq = 0;

        for(int i : nums){
            
            // its current element's previous element exists then its not the starting point
            // of the consecutive sequence
            if(!set.contains(i-1)){

                int len =1;
                // going through ont of the starting point of consecutive sequence
                while(set.contains(i+1)){
                    set.remove(i+1);
                    i++;
                    len++; 
                }

                longestSeq = Math.max(longestSeq,len);
            }
        }

        return longestSeq;
    }

}