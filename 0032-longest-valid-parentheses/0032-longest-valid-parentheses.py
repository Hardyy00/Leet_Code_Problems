class Solution(object):
    def longestValidParentheses(self, s):
        stack = []
        
        i = 0
        for ch in s :
            if ch=='(' : 
                stack.append(i)
            else :
                if not stack :
                    stack.append(i)
                else :
                    if s[stack[-1]]=='(' :
                        stack.pop()
                    else :
                        stack.append(i)
                        
            i+=1
        
        if(stack==None) :
            return len(s)
        maxLen = 0
        last = len(s)
        
        while stack :
            popped = stack.pop()
            maxLen = max(maxLen,last-popped-1)
            last = popped
            
        
        maxLen = max(maxLen,last)
        return maxLen
        