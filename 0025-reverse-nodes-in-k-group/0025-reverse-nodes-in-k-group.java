/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {

        // return bruteForce(head,k);

        return optimalSolution(head, k);
    }

    private ListNode optimalSolution(ListNode head, int k){

        // Make use of recursive to solve the each k-size portion 
        // from a recursion call return the new head, to that it can be connected to previous's new tail

        // TC : O(N)
        // SC : O(1)

        if(head == null || head.next == null) return head;

        int i=0;
        ListNode curr = head;

        // check to see it we have k elements or not
        while(i<k){
            if(curr == null) return head;
            curr = curr.next;
            i++;
        }

        curr = head;
        ListNode pre = null;

        for(i=0;i<k;i++){

            ListNode next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }

        if(curr != null){
            ListNode nextHead = optimalSolution(curr,k);
            head.next = nextHead;  // connect tail to next head
        }

        return pre;  // return new head
    }

    private ListNode bruteForce(ListNode head, int k){

        // Brute Force : Convert the linked list to arraylist, and then reverse in k - group, (take the step size
        // to be k of for loop and reverse element b/w the range i to i+k-1 using two pointers) , and then
        // make linked list out of the list , make sure to set tail of list to be null

        // TC : O(N)
        // SC : O(N)

        if(head==null || head.next == null) return head;

        List<ListNode> list = new ArrayList<>();

        ListNode curr = head;

        while(curr != null){
            list.add(curr);
            curr = curr.next;
        }

        for(int i=0;i<list.size();i+=k){

            int j = i;
            int y = i+k-1;

            if(y >= list.size()) break;

            while(j < y){
                ListNode node = list.get(j);
                list.set(j, list.get(y));
                list.set(y,node);

                j++;
                y--;
            }
        }

        ListNode temp = new ListNode();
        ListNode tail = temp;

        for(ListNode node : list){
            tail.next = node;
            tail = tail.next;
        }

        tail.next  = null;

        return temp.next;
    }
}