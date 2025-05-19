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

        // Optimal Way 
        // Insert copies in b/w the nodes, and then assign the random pointers by  curr.next.random = curr.random != null
        // ? curr.random.next : null, after it extract the copied nodes,and then mutate the list to its original state

        // TC : O(N)
        // SC : O(1) 
        
        Node curr = head;

        // add copied nodes
        while(curr != null){
            Node newNode = new Node(curr.val);
            newNode.next = curr.next;
            curr.next = newNode;
            curr = curr.next.next;
        }

       
        curr = head;

        // assign random pointers
        while(curr != null){
            if(curr.random != null) curr.next.random = curr.random.next;
            curr = curr.next.next;
        }

        Node temp= new Node(0);
        Node tail = temp;

        curr = head;

        // extraction of nodes, and removing them
        while(curr != null){

            tail.next = curr.next;
            tail = tail.next;
            curr.next = curr.next.next;
            curr = curr.next;
        }

       
        return temp.next;
    }
}