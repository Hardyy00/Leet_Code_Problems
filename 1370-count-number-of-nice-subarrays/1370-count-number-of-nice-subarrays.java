class Solution {
    public int numberOfSubarrays(int[] nums, int k) {

        // consider odd as 1 and even as 0, then we need to find subarrays having sum == K
        // i.e pre[r]-pre[l-1] = k => pre[l-1] = pre[r] - k
        // we can find this using hashmap

        // have a running sum
        // if sum ==k , the subarray [0..i] is such subarray, so increment

        // also get and add pre[r] - k , to the map

        // at this is the number of elements, that can be considered as the left extreme of the subarray

        // TC : O(N)
        // SC : O(N)          

        Map<Integer,Integer> map = new HashMap<>();

        int cn = 0;

        int sum =0;
        
        for(int i=0;i<nums.length;++i){

            sum += (nums[i] & 1)==1 ? 1 : 0;

            if(sum == k){
                cn++;
            }

            cn += map.getOrDefault(sum - k, 0);

            map.put(sum , map.getOrDefault(sum ,0) + 1);
        }

        return cn;
    }
}