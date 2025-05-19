class Solution {
    public List<List<Integer>> threeSum(int[] nums) {

        // Best Solution : sort the array, fix i, and the in the right side find two numbers with sum == -num[i]
        // (nums[i] + nums[j] + nums[k] = 0 => nums[j] + nums[k] = -nums[i]) , use two pointers for that
        // to avoid duplicates skip the element such that they are equal to thier previous elements

        // TC : O(N^2)
        // SC : O(1)
        
        List<List<Integer>> answer = new ArrayList<>();

        Arrays.sort(nums);

        int n = nums.length;
        for(int i=0;i<n-2;i++){

            int low = i+1;
            int high = n-1;
            int tar = -nums[i];
            while(low < high){

               int sum = nums[low] + nums[high];

               if(sum > tar) high--;
               else if(sum < tar) low++;
               else {

                   List<Integer> list = new ArrayList<>();
                   list.add(nums[i]);
                   list.add(nums[low]);
                   list.add(nums[high]);

                   answer.add(list);

                   int tempStart = low, tempEnd = high;

                   // skip over equal element
                   while(low < high && nums[low] == nums[tempStart] ) low++;
                   while(high > low && nums[high] == nums[tempEnd]) high--;
               } 
            }

            while(i+1<n && nums[i]==nums[i+1]) i++;  // skip here too
            
        }

        return answer;
    }
}