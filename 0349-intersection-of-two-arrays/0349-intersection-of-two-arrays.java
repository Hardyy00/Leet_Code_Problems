class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        
        return solve(nums1, nums2);
    }

    int[] solve(int[] nums1, int[] nums2){

        // TC : O(N) + O(M)
        // SC : O(N)    

        Map<Integer,Integer> map = new HashMap<>();

        for(int i : nums1){
            
            // unique elements exist now
            map.put(i,1);
        }

        int count = 0;

        for(int i : nums2){

            // unique intersection
            if(map.containsKey(i) && map.get(i)==1){
                map.put(i,2);
                count++;
            }
        }

        int[] ans = new int[count];
        int in =  0;

        for(int key : map.keySet()){

            if(map.get(key)==2) ans[in++] = key;
        }

        return ans;
    }
}