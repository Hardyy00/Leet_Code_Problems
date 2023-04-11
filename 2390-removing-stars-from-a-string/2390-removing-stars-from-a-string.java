class Solution {
    public String removeStars(String s) {
        
        
        StringBuilder ans = new StringBuilder();
        
        for(int i=0;i<s.length();i++){
            
            char ch = s.charAt(i);
            
            if(ch=='*' && ans.length()>0){
                ans.deleteCharAt(ans.length()-1);
            }else{
                ans.append(ch);
            }
        }
        
        return ans.toString();
        
    }
}