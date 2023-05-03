class Solution {
    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        
        HashSet<Integer> set1 =addIt(nums1);
        HashSet<Integer> set2 =addIt(nums2);
        
        List<List<Integer>> ans = new ArrayList<>();
        
        ans.add(makeList(set1,set2));
        ans.add(makeList(set2,set1));
        
        return ans;

        
    }
    
    private static HashSet<Integer> addIt(int[] nums){
        
        HashSet<Integer> set = new HashSet<>();
        for(int i : nums) set.add(i);
        return set;
    }
    
    private static List<Integer> makeList(HashSet<Integer> set1,HashSet<Integer> set2){
        
        List<Integer> list =new ArrayList<>();
        
        for(int i : set1){
            
            if(!set2.contains(i)) list.add(i);
        }
        
        return list;
    }
}