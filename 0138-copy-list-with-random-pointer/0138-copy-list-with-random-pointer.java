/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

class Solution {
    public Node copyRandomList(Node head) {

        if(head==null)
            return null;

        Node curr;


        // fitting a copy of each element between nodes
        for(curr = head;curr!=null;){

            Node next = curr.next;
            curr.next = new Node(curr.val);
            curr.next.next = next;
            curr = next;
        }

        // setting random pointers to each copy
        for(curr=head;curr!=null ;curr = curr.next.next){
            curr.next.random = curr.random!=null ? curr.random.next : null;
        }

        // extracting the copies
        Node copyHead = null,copyCurr = null;

        for(curr=head;curr!=null;){

            if(copyHead==null){
                copyHead = curr.next;
                copyCurr = copyHead;
            }else{
                copyCurr.next = curr.next;
                copyCurr = copyCurr.next;
            }

            curr.next = curr.next.next;
            curr = curr.next;
        }

        return copyHead;



        
    }
}