class LRUCache {

    // TC : O(1)
    // SC : O(Cap)
    static class Node{
        int key;
        int val;
        Node next;
        Node pre;

        public Node(int key,int val){
            this.key = key;
            this.val = val;
        }
    }

    int cap;
    Node tail;
    Node head;
    int size;
    Map<Integer,Node> map;

    public LRUCache(int capacity) {
        cap = capacity;
        map = new HashMap<>();
    }
    
    public int get(int key) {
        // if i'm trying to get an element , when we used it recently, so just put it on the head
        if(map.containsKey(key)){
            Node temp = map.get(key);       
            remove(temp.key);           // remove the element
            if(head==null){
                tail=head=temp;
            }else{
                temp.pre = head;
                head.next = temp;
                head = temp;
            }

            return temp.val;
        }

        return -1;

        // return map.getOrDefault(map.get(key).val, -1);
        
    }
    
    public void put(int key, int value) {

        if(map.containsKey(key)){
            // if map already contains the key, then remove it from its place and insert it in the front
            remove(key);
            Node temp = map.get(key);
            temp.val = value;   // assign new value
            temp.pre = head;
            if(head!=null) head.next = temp;
            head = temp;
            return;     
        }

        // if map does not contain the value, then add it in the front
        if(size<cap){
            Node temp = new Node(key,value);
            map.put(key,temp);      // put new key in the map
            if(tail==null){
                head=tail=temp;     // if it is the first value , then make it head as well as tail
            }else{
                temp.pre = head;
                head.next = temp;
                head=temp;
            }
            size++;

        }else if(size==cap){    // if we want to add a new key, but capacity is full, so remove
        // the least recently used key , that is in the last
            
            int tailKey = tail.key;
            remove(tailKey);        // remove the tail
            map.remove(tailKey);        // remove the tail from map too
            Node temp = new Node(key,value);
            map.put(key,temp);
            if(head!=null){     
                head.next = temp;
                temp.pre = head;
                head = temp;
            }else{
                head=tail=temp;
            }
        } 
    }

    void remove(int key){

        Node node = map.get(key);

        // if what we want to remove it tail
        if(node==tail){
            Node temp = tail;
            tail = tail.next;
            if(tail!=null) tail.pre = null;     // to deattach the removed element from everywhere
            else head=tail;
            temp.next = null;

        }else if(node==head){

            Node temp = head;
            head = head.pre;
            temp.pre = null;
            if(head!=null) head.next = null;
            else tail = head;

        }
        else{
            // remove the element from the middle
            node.pre.next = node.next;
            node.next.pre = node.pre;
        }
 
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */