class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        
        List<List<Integer>> answer =new ArrayList<>();

        List<Integer> list = new ArrayList<>();

        Arrays.sort(candidates);


        recursion(0,target, answer, list, candidates);

        return answer;

    }

    private void recursion(int index, int target, List<List<Integer>> answer,List<Integer> list, int[] candidates){

        // TC : O(2^n * K), where k =  average length of all combinations
        // SC : O(N)

        if(target==0){
            answer.add(new ArrayList<>(list));
            return;
        }

        if(index >= candidates.length) return ;


        for(int i=index;i<candidates.length;i++){

            if(candidates[i] <= target){

                if(i>index && candidates[i] == candidates[i-1]) continue;

                list.add(candidates[i]);
                recursion(i+1,target-candidates[i], answer,list, candidates);
                list.remove(list.size()-1);
            }
        }
    }
}