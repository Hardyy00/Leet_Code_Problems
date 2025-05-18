/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        
        // return bruteForce(headA, headB);

        return optimalSolution(headA, headB);
    }

    private ListNode optimalSolution(ListNode headA, ListNode headB){

        // One way Approach : start them together, when a node reaches null, move it to the head of another list
        // do this while curr1 != curr2, we are doing this so that the pointer of shorter list, is able to cover the
        // extra distance 

        // TC : O(max(c1,c2))
        // SC : O(1)

        ListNode curr1 = headA;
        ListNode curr2 = headB;

        while(curr1 != curr2){


            if(curr1== null) curr1 = headB;
            else curr1 = curr1.next;

            if(curr2== null) curr2 = headA;
            else curr2 = curr2.next;
        }

        return curr1;
    }

    

    private ListNode bruteForce(ListNode headA, ListNode headB){

        // Brute Force : Put a list in a set, after it iterate over another list, and if the node is already
        // present then return that node, (the first node that is in the set , is the answer)

        // TC : O(c1 + c2)
        // SC : O(c1)

        Set<ListNode> set = new HashSet<>();

        ListNode curr1=  headA;
        ListNode curr2 = headB;

        while(curr1 != null){

            set.add(curr1);
            curr1 = curr1.next;
        }

        while(curr2 != null){

            if(set.contains(curr2)) return curr2;
            else{
                curr2 =curr2.next;
            }
        }

        return null;
    }

    private ListNode solution1(ListNode headA,ListNode headB){

        // Approach1 : calc. length of both linkedlists , move the pointer of 1 list with greater length
        // with extra distance, so that we can move them together , after that simply check their references

        // TC : O(c1 + c2 +   max(c1,c2))
        // SC : O(1)

        int c1 = length(headA);
        int c2 = length(headB);


        ListNode curr1 = headA;
        ListNode curr2 = headB;

        if(c1 > c2) {

            for(int i=0;i<c1-c2;i++){
                curr1 = curr1.next;
            }

        }else if(c2 > c1){

            for(int i=0;i<c2-c1;i++){
                curr2 = curr2.next;
            }

        }


        while(curr1 != null && curr2 != null){

            if(curr1 == curr2) return curr1;

            curr1 = curr1.next;
            curr2 = curr2.next;
        }

        return null;
    }

    private int length(ListNode node){

        ListNode curr = node;
        int count = 0;

        while(curr != null){
            count++;
            curr = curr.next;
        }

        return count;
    }
}