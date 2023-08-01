class Solution {
    public List<List<Integer>> combine(int n, int k) {

        List<List<Integer>> ans = new ArrayList<>();

        List<Integer> list = new ArrayList<>();

        fill(list,ans,1,n,k);

        return ans;
        
    }

    private static void fill(List<Integer> list , List<List<Integer>> ans, int ele, int n , int k){

        if(list.size()>k || ele>n){
            if(list.size()==k)
                ans.add(new ArrayList<>(list));

            return;
        }

        // pick the element
        list.add(ele);
        fill(list,ans,ele+1,n,k);
        list.remove(list.size()-1);

        // do not pick the element
        fill(list,ans,ele+1,n,k);
    }


}