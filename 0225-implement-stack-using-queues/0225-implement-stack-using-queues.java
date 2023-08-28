class MyStack {

    private Queue<Integer> queue;
    private Queue<Integer> helper;

    public MyStack() {
        
        this.queue = new LinkedList<>();
        this.helper = new LinkedList<>();
    }
    
    public void push(int x) {

        this.queue.add(x);
        
    }
    
    public int pop() {

        if(queue.size()==1) return queue.poll();

        while(queue.size()>1){
            helper.add(queue.poll());
        }

        int pop =  queue.poll();

        while(!helper.isEmpty()){
            queue.add(helper.poll());
        }

        return pop;

        
    }
    
    public int top() {

         if(queue.size()==1) return queue.peek();

        while(queue.size()>1){
            helper.add(queue.poll());
        }

        int pop =  queue.poll();

        while(!helper.isEmpty()){
            queue.add(helper.poll());
        }

        queue.add(pop);

        return pop;
        
    }
    
    public boolean empty() {

        return queue.isEmpty();
        
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */