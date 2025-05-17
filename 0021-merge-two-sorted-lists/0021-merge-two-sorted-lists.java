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
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        // Optimal Approach : use the merge two sorted array approach using two pointer, 
        // make a separate list for the connection of nodes

        // TC : O(N+M)
        // SC : O(1)
        
        if(list1==null) return list2;

        if(list2==null) return list1;

        ListNode temp = new ListNode();

        ListNode curr1 = list1;
        ListNode curr2 = list2;
        ListNode tail = temp;  // for the tail of new linked list

        while(curr1!= null && curr2!=null){

            if(curr1.val < curr2.val) {
                tail.next  = curr1;
                curr1 = curr1.next;
                tail = tail.next;
            }else{
                tail.next = curr2;
                curr2 = curr2.next;
                tail = tail.next;
            }
        }

        // add the remaining characters
        while(curr1 != null){
            tail.next = curr1;
            curr1 = curr1.next;
            tail = tail.next;
        }

        while(curr2 != null){
            tail.next = curr2;
            curr2  = curr2.next;
            tail = tail.next;
        }

        tail.next= null;

        return temp.next;

    }
}