class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {

        // Iterator over all the possible kth element, but skip duplicate kth elements
        // keep in mid the even inside the each subset order desn't matter. so sort them to avoid duplicates

        // try ask recursion, what can be my ith element .

        // TC : O()
        
        List<Integer> list = new ArrayList<>();

        Arrays.sort(nums);

        List<List<Integer>> answer = new ArrayList<>();

        recursion(0,answer,list,nums);

        return answer;
    }

    private void recursion(int index, List<List<Integer>> answer, List<Integer> list, int[] nums){

        // TC: O(2^N) (since its generatign 2^N subsets)
        // SC : O(N)  

        answer.add(new ArrayList<>(list));

        if(index >= nums.length){
            return ;
        }


        for(int i=index;i<nums.length;i++){

            // skip over duplicates
            if(i>index && nums[i]==nums[i-1]) continue;

            list.add(nums[i]);
            recursion(i+1,answer,list,nums);
            list.remove(list.size()-1);
        }
    }
}