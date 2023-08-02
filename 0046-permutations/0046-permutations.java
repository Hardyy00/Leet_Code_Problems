class Solution {
    public List<List<Integer>> permute(int[] nums) {
        
        List<List<Integer>> ans = new ArrayList<>();
        boolean[] visit = new boolean[nums.length];
        List<Integer> list = new ArrayList<>();

        getPermutations(nums,list,ans,visit);

        return ans;

    }

    private static void getPermutations(int[] nums,List<Integer> list,List<List<Integer>> ans,
    boolean[] visit){

        // System.out.println("List : " + list + " Visit : " + Arrays.toString(visit));

        if(list.size()>=nums.length){
            if(list.size()==nums.length){
                ans.add(new ArrayList<>(list));
            }
            return;
        }

        for(int i=0;i<nums.length;i++){

            if(visit[i])
                continue;

            list.add(nums[i]);
            visit[i] = true;
            getPermutations(nums,list,ans,visit);
            list.remove(list.size()-1);
            visit[i] = false;
        }
    }
}