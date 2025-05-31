class KthLargest {

    PriorityQueue<Integer>  queue ;
    int k;

    public KthLargest(int k, int[] nums) {
        
        this.queue = new PriorityQueue<>();;
        this.k = k;
        for(int i : nums) add(i);

        
    }
    
    public int add(int val) {  
        // TC : O(n*log(k)) for constructor and O(logk) for add() method and SC : O(k) for pri. queue
        queue.offer(val);
        if(queue.size()>k) queue.poll();

        return queue.peek();
    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */