//{ Driver Code Starts
// driver

import java.util.*;

class Node {
    int data;
    Node next;

    Node(int d) {
        data = d;
        next = null;
    }
}

class GfG{
    
    static void printList(Node n){
        while(n!=null){
            System.out.print(n.data+" ");
            n = n.next;
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        
        while (T-- > 0) {
            
            int n = sc.nextInt();
            int val = sc.nextInt();
            
            Node first = new Node(val);
            Node tail = first;
            for(int i=0; i<n-1; i++)
            {
                val = sc.nextInt();
                tail.next = new Node(val);
                tail = tail.next;
            }
            
            int m = sc.nextInt();
            val = sc.nextInt();
            
            Node second = new Node(val);
            tail = second;
            for(int i=0; i<m-1; i++)
            {
                val = sc.nextInt();
                tail.next = new Node(val);
                tail = tail.next;
            }
            
            Solution g = new Solution();
            Node res = g.addTwoLists(first, second);
            printList(res);
        }
    }
}

// } Driver Code Ends


/* node for linked list

class Node {
    int data;
    Node next;

    Node(int d) {
        data = d;
        next = null;
    }
}

*/

class Solution{
    //Function to add two numbers represented by linked list.
    static Node addTwoLists(Node first, Node second){
       
        first = reverseList(first);
        second = reverseList(second);
        
        Node pre = null;
        Node curr1 = first,curr2 =  second;
        int carry = 0;
        while(curr1!=null && curr2!=null){
            
            pre = curr1;
            int sum = carry + curr1.data + curr2.data;
            curr1.data = sum%10;
            carry = sum/10;
            
            curr1 = curr1.next;
            curr2 = curr2.next;
        }
        
        if(curr1==null && curr2!=null){
            pre.next = curr2;
        }
        
        while(curr1!=null){
            pre = curr1;
            
            int sum = carry + curr1.data;
            curr1.data = sum%10;
            carry = sum/10;
            
            curr1 = curr1.next;
        }
        
        while(curr2!=null){
            
            pre  = curr2;
            int sum = carry + curr2.data;
            curr2.data = sum%10;
            carry = sum/10;
            curr2 = curr2.next;
        }
        
        if(carry==1){
            pre.next = new Node(1);
        }
        
        return reverseList(first);
    }
    
    private static Node reverseList(Node curr){
        
        Node pre  = null;
        
        while(curr!=null){
            
            Node next = curr.next;
            
            curr.next =  pre;
            pre = curr;
            curr = next;
        }
        
        return pre;
    }
}