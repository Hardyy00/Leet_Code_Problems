class Solution {
    public List<List<Integer>> permute(int[] nums) {

        int n= nums.length;
        boolean[] visit = new boolean[n];

        List<List<Integer>> answer = new ArrayList<>();

        List<Integer> list = new ArrayList<>();

        recursion(0,visit,list,answer, nums);

        return answer;
    }

    private void recursion(int index, boolean[] visit, List<Integer> list, List<List<Integer>> answer, int[] nums){

        // TC : O(n! * n)
        // SC : O(N)

        if(index == nums.length){
            answer.add(new ArrayList<>(list));
            return;
        }

        int n = nums.length;

        for(int i=0;i<n;i++){

            if(visit[i] == true) continue;

            visit[i] = true;
            list.add(nums[i]);
            recursion(index+1,visit,list,answer,nums);
            list.remove(list.size()-1);
            visit[i] = false;
        }
    }
}