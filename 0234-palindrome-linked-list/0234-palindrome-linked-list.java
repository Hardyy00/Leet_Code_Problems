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
    public boolean isPalindrome(ListNode head) {
        
        // return optimalSolution(head);

        return mostOptimalSolution(head);
    }

    private boolean mostOptimalSolution(ListNode head){

        // Most Optimal Solution : Find the middle node, and from the middle node, get to the end of the list
        // using recursion, on coming bac check if each value is values at corresponding ends are equal or not
        // if not then always return false. Use an array of size 1, to maintain a curr variable for the start

        // TC : O(N)
        // SC : O(1)

        if(head==null || head.next == null) return true;

        ListNode middleNode = head;
        ListNode[] curr1 = {head};  // the starting reference

        return recursion(curr1, middleNode);  
    }


    private boolean recursion(ListNode[] curr1, ListNode curr2){

        if(curr2==null) return true;

        if(!recursion(curr1,curr2.next)) return false;

        if(curr1[0].val != curr2.val) return false;

        curr1[0] = curr1[0].next;  // move the reference

        return true;  
    }

    private boolean optimalSolution(ListNode head){

        // Optimal Solution : Find the middle of the linked list, break it into two halves (set pre.next to null)
        // reverse the second half, and then start comparaing 

        // TC : O(N)
        // SC : O(1)

        if(head== null || head.next == null) return true;

        ListNode midNode = findMiddleNode(head);
        ListNode reverseHead = reverseList(midNode);

        ListNode curr1 = head;
        ListNode curr2 = reverseHead;

        while(curr1  != null && curr2 != null){

            if(curr1.val != curr2.val) return false;

            curr1 = curr1.next;
            curr2 = curr2.next;
        }

        return true;
    }

    private ListNode reverseList(ListNode head){

        ListNode curr = head, pre = null;

        while(curr != null){
            ListNode next = curr.next;
            curr.next = pre;
            pre =curr;
            curr = next;
        }

        return pre;
    }

    private ListNode findMiddleNode(ListNode head){

        ListNode slow = head;
        ListNode fast = head;
        ListNode pre = null;

        while(fast != null && fast.next != null){
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        // pre.next = null;  // split the linked list (not used for most optimal solution)
        return slow;
    }
}