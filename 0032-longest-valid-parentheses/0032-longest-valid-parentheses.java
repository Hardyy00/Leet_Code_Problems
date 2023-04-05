class Solution {
    public int longestValidParentheses(String s) {
        
        Deque<Integer> stack = new ArrayDeque<>();
        
        for(int i=0;i<s.length();i++){
            
            if(s.charAt(i)=='(') stack.push(i);
            
            else{
                
                if(stack.isEmpty()) stack.push(i);
                else{
                    if(s.charAt(stack.peek())=='(') stack.pop();
                    else stack.push(i);
                }
            }
        }
        
        if(stack.isEmpty()) return s.length();
        
        int maxLen = 0;
        int last = s.length();
        
        while(!stack.isEmpty()){
            
            int popped  = stack.pop();
            maxLen = Math.max(maxLen,last-popped-1);
            last = popped;
        }
        
        maxLen = Math.max(maxLen,last);
        
        return maxLen;
        
        
        
        
    }
}