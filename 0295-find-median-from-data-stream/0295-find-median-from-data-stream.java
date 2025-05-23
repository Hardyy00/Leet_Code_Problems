class MedianFinder {

    PriorityQueue<Integer> maxHeap;  // represents left side
    PriorityQueue<Integer> minHeap;  // represet right side

    public MedianFinder() {

        this.maxHeap = new PriorityQueue<>((a,b)-> b-a);
        this.minHeap = new PriorityQueue<>();
        
    }
    
    public void addNum(int num) {

        // Approach : maintain two priority queues, that represent each half of a sorted array
        // the left side can have 1 geater size when total elements are odd.
        // if we find a bigger value that cannot come into , left side put it into right side
        // whenever left size has greater number of element (>1 ) put 1 element in the right side
        // if right side has greater number put it into left side
        
        // TC : O(logN)
        // SC : O(N)

        //left side - maxHeap, right side - minHeap

        // put the element in the left side priority queue

        if(maxHeap.isEmpty()) {
            maxHeap.offer(num);
            return;
        }
        
        // if number is smaller put it into left side
        if(num <= maxHeap.peek()) maxHeap.offer(num);
        else minHeap.offer(num);  // else put it into right side

        if(maxHeap.size() - minHeap.size() > 1){
            minHeap.offer(maxHeap.poll());
        }

        if(minHeap.size()>maxHeap.size()){
            maxHeap.offer(minHeap.poll());
        }

    }
    
    public double findMedian() {
        
        if((maxHeap.size() + minHeap.size()) %2 !=0 ) return (double) maxHeap.peek();
        
        double median = (minHeap.peek() + maxHeap.peek())/2.0;

        return median;
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */