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
    public ListNode middleNode(ListNode head) {
        
        // return bruteForce(head);

        return optimalSol(head);
    }

    private ListNode optimalSol(ListNode head){

        // Optimal solution : if we use a pointer such that it move at a 2x speed, then when it will be null
        // then the pointer with 1x speed will be at the middle

        // TC : O(N)
        // SC : O(1)

        ListNode slow = head;
        ListNode fast = head;

        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    private ListNode bruteForce(ListNode head){

        // Brute force : find the number of element in the linkedList, then find the middile elment 
        // by traversing it again
        
        // TV : O(N)
        // SC : O(1)

        if(head== null || head.next == null) return head;

        int count= 0;

        ListNode curr = head;

        while(curr != null){
            count++;
            curr = curr.next;
        }


        int mid = count/2;  // middle element
        int index = 0;

        curr = head;
        while(curr != null){

            if(index == mid) return curr;

            index++;
            curr = curr.next;

        }

        return null;
    }
}