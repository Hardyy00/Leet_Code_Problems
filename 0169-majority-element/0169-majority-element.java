class Solution {
    public int majorityElement(int[] nums) {

        // TC : O(N)
        // SC : O(1)

        // since a the answer's frequency is > n/2 , if we try to cut the element
        // with every other element, we will still get the 1 element in minimum

        int answer = -1;
        int fre = 0;

        for(int val : nums){

            // take the number as the answer
            if(fre==0){
                answer = val;
                fre++;
            }else if(val != answer){
                
                // cut it with every other number
                fre--;
            }else{

                fre++;  // increment the frequency
            }
        }

        return answer;
        
    }
}