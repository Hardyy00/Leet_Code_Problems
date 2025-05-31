class Solution {
    public int findKthLargest(int[] nums, int k) {

        // TC : O(N * logK)
        // SC : O(K)
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int val : nums){

            if(pq.size() < k){
                pq.offer(val);
            }else if(pq.peek() < val){
                pq.poll();
                pq.offer(val);
            }
        }

        return pq.peek();
    }
}