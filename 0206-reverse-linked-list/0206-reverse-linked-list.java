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
    public ListNode reverseList(ListNode head) {
        
        // return optimalSol(head);

        return recursive(head);
    }

    private ListNode recursive(ListNode head){

        // Recursive : always add the element in front of its next element
        // TC : O(N)
        // SC : O(N) or O(1)

        if(head == null || head.next == null) return head;

        ListNode revHead = recursive(head.next);

        head.next.next = head;
        head.next = null;

        return revHead;
    }

    private ListNode optimalSol(ListNode head){

        // Optimal Sol: Make current's next to previous, every time

        // TC : O(N)
        // SC : O(1)

        if(head==null || head.next == null) return head;

        ListNode curr = head;
        ListNode pre  = null;

        while(curr != null){

            ListNode next =curr.next;
            curr.next = pre;
            pre = curr;

            curr = next;
        }

        return pre;
    }
}