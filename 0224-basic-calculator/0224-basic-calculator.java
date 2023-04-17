class Solution {
    
    int index = 0;
    public int calculate(String s) {
        
//         s += "|";
        
//         Deque<Integer> stack1 = new ArrayDeque<>();
//         Deque<Character> stack2 = new ArrayDeque<>();
//         int val = 0;
//         boolean containsVal = false;
//         boolean mul = false;
        
//         for(int i=0;i<s.length();i++){
            
//             char ch = s.charAt(i);
            
//             if(ch==' ') continue;
//             else if(Character.isDigit(ch)){
//                 val = val *10 + (ch-'0');
//                 containsVal = true;
//             }else{
                
//                 if(!containsVal && ch=='-'  ){
//                     mul = true;
//                 }
                
//                 if(mul && containsVal){
//                     stack2.pop();
//                     stack1.push(-val);
//                     mul = false;
//                     containsVal = false;
//                     val = 0;
//                 }else if(containsVal){
//                     stack1.push(val);
//                     containsVal = false;
//                     val = 0;
//                 }
                
//                 // System.out.println(ch);
//                 // System.out.println(stack2);

                
                
//                 if(ch=='(') stack2.push(ch);
//                 else if(ch==')'){
//                     while(!stack2.isEmpty() && stack2.peek()!='('){
//                         char pop = stack2.pop();
//                         if(pop=='+'){
//                             stack1.push(stack1.pop() + stack1.pop());
//                         }else if(pop=='-'){
//                             stack1.push(-stack1.pop() + stack1.pop());
//                         }
//                     }
//                     stack2.pop();
//                 }else{
//                     while(!stack2.isEmpty() && getPrecedence(stack2.peek())<=getPrecedence(ch)){
//                         char pop = stack2.pop();
//                         if(pop=='+'){
//                             stack1.push(stack1.pop() + stack1.pop());
//                         }else if(pop=='-'){
//                             stack1.push(-stack1.pop() + stack1.pop());
//                         }
                        
//                     }
                    
//                     stack2.push(ch);

//                }
                
//             }
//         }
        
//         // System.out.println(stack1);
        
//         //  while(!stack2.isEmpty()){
//         //     char pop = stack2.pop();
//         //     if(pop=='+'){
//         //         stack1.push(stack1.pop() + stack1.pop());
//         //     }else if(pop=='-'){
//         //         stack1.push(-stack1.pop() + stack1.pop());
//         //     }
//         // }
        
//         return stack1.pop();
        
         return calculator(s);
       
    }
    
    private int calculator(String s){
        
        int ans = 0, curr = 0,sign = 1;
        
        while(index<s.length()){
            
            char ch = s.charAt(index++);
            
            if(ch==' ') continue;
            else if(Character.isDigit(ch)){
                curr = curr * 10 + (ch-'0');
            }else if(ch=='+' || ch=='-'){
                ans += curr*sign;
                curr=0;
                sign = ch=='+' ? 1 : -1;
                
            }else if(ch=='('){
                curr = calculator(s);
            }else if(ch==')') return ans + curr*sign;
        }
        
        return ans+curr*sign;
    }    
    
    
//     private static int getPrecedence(char ch){
        
//         if(ch=='(') return 1;;
        
//         return 0;
    // }
}



