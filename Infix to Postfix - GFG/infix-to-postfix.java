//{ Driver Code Starts
import java.util.*;
import java.lang.*;
import java.io.*;

class GFG {

    public static void main(String[] args) throws IOException {
        BufferedReader br =
            new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine().trim());
        while (t-- > 0) {
            System.out.println(
                new Solution().infixToPostfix(br.readLine().trim()));
        }
    }
}
// } Driver Code Ends


class Solution {
    // Function to convert an infix expression to a postfix expression.
    public static String infixToPostfix(String exp) {
        
        StringBuilder build = new StringBuilder();
        Deque<Character> stack = new ArrayDeque<>();
        
        for(int i=0;i<exp.length();i++){
            
            char ch = exp.charAt(i);
            if(ch=='(') stack.push(ch);
            else if(ch==')'){
                
                while(!stack.isEmpty()){
                    char pop = stack.pop();
                    if(pop=='(') break;
                    
                    build.append(pop);
                }
            }else if(ch=='+' || ch=='-' || ch=='/' || ch=='*' || ch=='^'){
                
                int pre = getPrecedence(ch);
                int preTop = stack.isEmpty() ? -1 : getPrecedence(stack.peek());
                
                if(stack.isEmpty()) stack.push(ch);
                else if(pre>preTop) stack.push(ch);
                else if(pre < preTop){
                    while(!stack.isEmpty() && pre <= getPrecedence(stack.peek())){
                        build.append(stack.pop());
                    }
                    
                    stack.push(ch);
                }else{
                    build.append(stack.pop());
                    stack.push(ch);
                }
            }else{
                build.append(ch);
            }
            
        }
        
        while(!stack.isEmpty()){
            build.append(stack.pop());
        }
        
        return build.toString();
        
        
    }
    
    private static int getPrecedence(char ch){
        
        if(ch=='(' || ch==')') return 1;
        else if(ch=='+' || ch=='-') return 2;
        else if(ch=='*' || ch=='/') return 3;
        else return 4;
    }
}