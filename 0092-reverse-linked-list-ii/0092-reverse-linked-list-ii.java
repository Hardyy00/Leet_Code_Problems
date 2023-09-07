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
    public ListNode reverseBetween(ListNode head, int left, int right) {  

        if(left==right) return head;

        // if head is the starting index then reverse Head has to be returned
        if(left==1){
            
            // saving curr head to connnect it to post reverse portion  || 2<-3<-4  revHead = 2 so connect it to 5
            ListNode curr = head,revLast=curr,pre = null;
            for(int i=0;i<=right-left;i++){
                
                ListNode next = curr.next;
                curr.next = pre;
                pre = curr;
                curr = next;
            }

            revLast.next = curr;    // connecting original head to post reverse portion
            return pre;
        } 

        ListNode start = head;
        for(int i=1;i<left-1;i++){
            start = start.next;
        }
    
        // saving the reverse start node to connect it to post reverse node
        ListNode revLast = start.next,pre=null,curr = revLast;

        for(int i=0;i<=right-left;i++){
                
            ListNode next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }

        revLast.next = curr;        // connect reversed 2 to 5
        start.next = pre;           // connecting node before reversfying node to the reverseHead

        return head;




        
    }
}