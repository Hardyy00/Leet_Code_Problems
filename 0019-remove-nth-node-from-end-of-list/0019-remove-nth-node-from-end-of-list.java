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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        
        // return bruteForce(head, n);

        return optimalApproach(head, n);
    }

    private ListNode optimalApproach(ListNode head, int n){

        // Optimal Approach : Use slow and fast pointer, move the fast pointer to distance of N+1, and then move
        // both of them. But then nth element has to be removed, handle that

        // TC : O(N)
        // SC : O(1)

        if(head == null || head.next == null) return null;

        ListNode fast = head;

        for(int i=0;i<n+1;i++) {

            if(fast == null) return head.next;

            fast = fast.next;
        }

        ListNode slow = head;

        while(fast != null){
            fast = fast.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;

        return head;
    }

    private ListNode bruteForce(ListNode head, int n){

        // Brute force : calculate the length of the list, after that reach to the element before the element ,to remove
        // , after that simply remove it, stoppage point=  < len - n -1 

        // TC : O(N)
        // SC : O(1)

        if(head == null || head.next == null) return null;

        int len = 0;

        ListNode curr = head;

        while(curr != null) {
            len++;
            curr = curr.next;
        }

        if(len == n) return head.next;  // remove the first element

        int stop = len -n -1;  // reach before the element to remove

        curr = head;
        int i = 0;
        while(i<stop){
            curr = curr.next;
            i++;
        }

        curr.next = curr.next.next;

        return head;
    }
}