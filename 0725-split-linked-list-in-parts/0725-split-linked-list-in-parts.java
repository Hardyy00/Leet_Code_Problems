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
    public ListNode[] splitListToParts(ListNode head, int k) {

        ListNode[] list = new ListNode[k];

        int size = 0;
        ListNode curr = head;

        // calculating the size of list
        while(curr!=null){
            size++;
            curr = curr.next;
        }

        // calculating size of each group
        int ele = size/k == 0 ? 1 : size/k;
        // calculate no of groups have 1 greater size
        int great = size%k;
        // for index of list, counter = to keep track of groups having 1 greater size
        // i= counter the elements in a single group
        int index = 0 , counter = 0,i = 0;
        curr = head;
        // group ending condition
        int stop = great==0 ? ele : ele+1;
        // linked list having size less than k
        if(size/k==0){ 
            stop = 1;
            great = 0;
        };

        while(curr!=null){
            
            i++;
            if(i==1){
                list[index]=curr;
                index++;
            }

            if(i==stop){
                ListNode next = curr.next;
                curr.next = null;
                i=0;
                counter++;
                stop = counter < great ? ele+1:ele;
                curr = next; 
            }else{
                curr = curr.next;
            }

        }

        return list;

        
    }
}