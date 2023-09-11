class Solution {
    public List<List<Integer>> groupThePeople(int[] group) {

        HashMap<Integer,List<Integer>> map = new HashMap<>();
        List<List<Integer>> ans = new ArrayList<>();

        for(int i=0;i<group.length;i++){
            
            if(map.containsKey(group[i])){
                map.get(group[i]).add(i);
            }else{
                List<Integer>  list = new ArrayList<>();
                list.add(i);
                map.put(group[i],list);
            }

            if(map.get(group[i]).size()>=group[i]){
                ans.add(new ArrayList<>(map.get(group[i])));
                map.get(group[i]).clear();
            }

        }

        return ans;
        
    }
}