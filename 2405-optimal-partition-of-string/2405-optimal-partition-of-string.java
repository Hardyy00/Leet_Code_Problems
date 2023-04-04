class Solution {
    public int partitionString(String s) {
        
        int in = 0;
        int si = 0;
        int count = 0;
        int[] fre = new int[26];
        
        for(int i=0;i<s.length();i++){
            
            char ch = s.charAt(i);
            
            fre[ch-97]++;
            
            // if a character appears more than 1 times then start the new substring from the current 
            // index
            if(fre[ch-97]>1){
                
                while(si<in){
                    fre[s.charAt(si)-97]--;
                    si++;
                }
            }
            
            if(si==in){
                count++;

            }
            
            in++;
        }
        
        return count;
        
    }
}