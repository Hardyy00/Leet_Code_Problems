class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {

        Map<Integer, Integer> map1 =new HashMap<>(), map2 = new HashMap<>();

        for(int i : nums1){

            map1.put(i, map1.getOrDefault(i, 0) + 1);
        }

         for(int i : nums2){

            map2.put(i, map2.getOrDefault(i, 0) + 1);
        }

        List<Integer> ls = new ArrayList<>();

        for(Map.Entry<Integer,Integer> set : map1.entrySet()){

            int key = set.getKey(), val = Math.min(set.getValue(), map2.getOrDefault(key,0));

            for(int i=0;i<val;i++){

                ls.add(key);
            }
        }

        int[] ans = new int[ls.size()];

        for(int i=0;i<ans.length;i++){
            ans[i] = ls.get(i);
        }

        return ans;
        
    }
}