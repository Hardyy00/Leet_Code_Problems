/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public void deleteNode(ListNode node) {

        // bruteForce(node);

        optimalSolution(node);
    }

    private void optimalSolution(ListNode node){

        // Optimal Approach : Move the node to right (or copy the right value) and point to the next of next

        // TC : O(1)
        // SC : O(1)

        node.val = node.next.val;
        node.next = node.next.next;
    }

    private void bruteForce(ListNode node){
        // Bute Force : move node to the back of the list (by swapping) and when curr.next.next == null, break
        // to remove the curr.next  

        // TC: O(N)
        // SC : O(1)
        
        ListNode curr = node;

        while(curr.next != null){

            // swap the values
            int temp = curr.val;
            curr.val = curr.next.val;
            curr.next.val = temp;

            // if the last node is target node, break, to remove it
            if(curr.next.next == null) break;

            curr = curr.next;
        }

        curr.next = null;
    }
}