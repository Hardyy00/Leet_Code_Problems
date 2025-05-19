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
    public ListNode rotateRight(ListNode head, int k) {

        if(head== null || head.next==null) return head;

        // return betterSolution(head, k);

        return bestSolution(head,k);
        
    }

    private ListNode bestSolution(ListNode head, int k){

        // Best Solution : find the length of the linkedlist, and mod the k,, after this reach the element
        // after which break should be made, next element after break is the new head. make curr.next = null
        // and find the tail of the after break portion, connec the tail to the old head
        // and make newHead the head

        // TC : O(N)
        // SC : O(1)

        ListNode curr = head;
        int len = 0;

        while(curr != null){
            len++;
            curr = curr.next;
        }

        k = k % len;

        if(k==0) return head;

        curr = head;
        for(int i=0;i<len-k-1;i++){
            curr = curr.next;
        }

        ListNode newHead = curr.next;
        curr.next = null;  // make a break

        ListNode iterator = newHead;  // to  find the tail of this portion

        while(iterator != null && iterator.next != null){

            iterator = iterator.next;
        }

        iterator.next = head;  // connect tail to previous head
        head = newHead;  // make newHead

        return head;
    }

    private ListNode betterSolution(ListNode head, int k){

        // Better Solution : Convert it to array, and apply the rotate array logic to it, that it
        // reverse the 0,n-k-1, and reverse the n-k,n-1 and then reverse the whole array

        // TC : O(N)
        // SC : O(N)

        ListNode curr = head;
        List<ListNode> list = new ArrayList<>();

        while(curr != null){
            list.add(curr);
            curr = curr.next;
        }

        k = k % list.size();
        reverse(list.size()-k,list.size()-1,list);
        reverse(0,list.size()-k-1,list);

        reverse(0,list.size()-1,list);

        ListNode temp = new ListNode();
        ListNode tail = temp;

        for(ListNode node : list) {
            tail.next = node;
            tail = tail.next;
        }

        tail.next = null;

        return temp.next;
    }

    private void reverse(int low, int high, List<ListNode> list){

        while(low < high){

            ListNode temp = list.get(low);
            list.set(low, list.get(high));
            list.set(high,temp);
            low++;
            high--;
        }
    }
}