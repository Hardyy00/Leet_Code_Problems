class Solution {
    public int partitionString(String s) {
        
        int count = 0;
        Set<Character> set = new HashSet<>();
        
        for(int i=0;i<s.length();i++){
            
            char ch = s.charAt(i);
            
            if(set.contains(ch)){
                set.clear();
                count++;
            }
            
            set.add(ch);
        }
        
        count++;
        
        return count;
        
    }
}