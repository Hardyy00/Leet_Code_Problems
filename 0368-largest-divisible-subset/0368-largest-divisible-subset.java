class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {

        // subequence is in a particular order, but subset can choose an element in any order

        // Tabulation
        // TC : O(N^2) + O(N Log N)
        // SC : O(N)

        // Intution : using the longest increasing subsequence, at every index , we find the length of
        // longest divisible with nums[i] as it last element
        // if we sort all the numbers, then if nums[i] divides num[j] && nums[j] divides nums[k]
        // nums[i] automatically divides nums[k], because the previous elements are all smaller
        // therefore we only have to check with only previous number, if that number divides it
        // than all the elements , that divide that number also divide this current number
        // and then always maintaining the previous index to get the previous element of the subset

        Arrays.sort(nums);

        int n = nums.length;
        int[] dp = new int[n];
        int[] preIndexes = new int[n];

        Arrays.fill(dp, 1);

        int maxLen = 0;
        int lastIndex = 0;

        for(int i=0;i<n;i++){
            
            preIndexes[i] = i;
            for(int j=0;j<i;j++){

                if((nums[i] % nums[j] ==0 ) && 1 + dp[j] > dp[i]){

                    dp[i] = 1+ dp[j];
                    preIndexes[i] = j;
                }
            }

            if(dp[i] > maxLen){
                maxLen = dp[i];
                lastIndex = i;
            }
        }


        List<Integer> list = new ArrayList<>();

        while(true){

            list.add(nums[lastIndex]);

            if(lastIndex == preIndexes[lastIndex]) break;
            lastIndex = preIndexes[lastIndex];
        }

        Collections.reverse(list);

        return list;
        
    }
}