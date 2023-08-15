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
    public ListNode partition(ListNode head, int x) {

        if(head == null || head.next == null) return head;

        ListNode front = null, back = null;
        if(head.val>=x) back = head;
        else{

            ListNode curr = head;
            while(curr!=null){

                if(curr.val < x && back==null){
                    front = curr;
                }
                if(curr.val>=x){
                    back = curr;
                    break;
                }

                curr = curr.next;
            }
        }

        if(back==null) return head;

        ListNode curr = back.next;
        ListNode pre = back;
        while(curr!=null){

            if(curr.val < x){

                ListNode temp = curr;
                // System.out.println(curr.val);
                pre.next = temp.next;
                if(front==null){
                    curr.next = head;
                    head = curr;
                    front = curr;
                    
                }else{

                    front.next = curr;
                    curr.next = back;
                    front = curr;
            
                }
            }

            pre = curr;
            curr = curr.next;
        }

        return head;
        
    }
}