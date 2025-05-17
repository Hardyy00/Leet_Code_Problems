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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        // TC : O(max(n,m))
        // sc : O(1)
        
        ListNode temp = new ListNode();  // to maintain the new list
        ListNode tail = temp;  // for the tail of new list

        int carry = 0;

        ListNode curr1= l1;
        ListNode curr2 = l2;

        while(curr1!=null && curr2!=null){

            int sum = carry + curr1.val + curr2.val;
            ListNode newNode = new ListNode(sum % 10);
            carry = sum /10;

            tail.next= newNode; // attach new node
            tail = tail.next;
            curr1 = curr1.next;
            curr2 = curr2.next;
        }

        while(curr1!=null){

            int sum = carry + curr1.val;
            ListNode newNode = new ListNode(sum % 10);
            carry = sum /10;

            tail.next = newNode;

            curr1 = curr1.next;
            tail = tail.next;
        }

        while(curr2!=null){
            int sum = carry + curr2.val;
            ListNode newNode = new ListNode(sum % 10);
            carry = sum/10;

            tail.next = newNode;

            curr2 = curr2.next;
            tail = tail.next;
        }

        if(carry ==1) tail.next = new ListNode(1);

        return temp.next;
    }
}
