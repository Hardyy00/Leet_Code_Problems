class Solution {
    public boolean isPalindrome(String s) {
        
        String alphaString = "";
        
        
        for(int i=0;i<s.length();i++){
            
            char ch = s.charAt(i);
            
            if(Character.isLetterOrDigit(ch)){
                if(ch>=65 && ch<=90){
                    ch+=32;
                }
                
                alphaString += ch;
            }
            
        }
        
        return palindrome(alphaString);
        
    }
    
    private static boolean palindrome(String s){
        
        int i=0;
        int j= s.length()-1;
        
        while(i<j){
            if(s.charAt(i)!=s.charAt(j))
                return false;
            
            i++;
            j--;
        }
        
        return true;
    }
}