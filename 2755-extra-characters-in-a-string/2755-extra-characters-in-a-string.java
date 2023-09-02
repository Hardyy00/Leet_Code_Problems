class Solution {
    public int minExtraChar(String s, String[] dict) {
        
        Set<String> set = new HashSet<>();
        
        
        for(String val : dict) set.add(val);
        
        int[] collect = new int[s.length()+1];
        
        for(int i=1;i<=s.length();i++){
            collect[i] = collect[i-1]+1;
            
            for(int j=i;j>=1;j--){
                
                String st = s.substring(j-1,i);
                
                if(set.contains(st)){
                    collect[i] = Math.min(collect[i],collect[j-1]);
                }
            }
        }
        
        return collect[s.length()];
        
    }
}