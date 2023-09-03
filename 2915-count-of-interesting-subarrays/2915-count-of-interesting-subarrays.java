class Solution {
    public long countInterestingSubarrays(List<Integer> nums, int modulo, int k) {

        int n = nums.size();
        
        int pc = 0;
        
        Map<Integer, Integer> fre = new HashMap<>();
        
        fre.put(0, 1);
        
        long ans = 0;
        
        for (int i = 0; i < n; i++) {
            if (nums.get(i) % modulo == k) {
                pc++;
            }
            int temp = (pc - k + modulo) % modulo;
            
            ans += fre.getOrDefault(temp, 0);
            
            
            fre.put(pc % modulo, fre.getOrDefault(pc % modulo, 0) + 1);
        }
        
        return ans;
    }
}