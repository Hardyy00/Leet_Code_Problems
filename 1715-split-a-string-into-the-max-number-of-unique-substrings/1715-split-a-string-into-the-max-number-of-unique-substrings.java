class Solution {
    int max = 0;
    public int maxUniqueSplit(String s) {
        
        countUniqueSubs(0,s,new HashSet<>());
        return max;        
    }

    private void countUniqueSubs(int index,String s,HashSet<String> set){

        if(index==s.length()){
            max = Math.max(max,set.size());
            return;
        }

        if(set.size() + (s.length()-index) <= max)
            return;

        for(int i=index;i<s.length();i++){
            String str = s.substring(index,i+1);
            if(!set.contains(str)){
                set.add(str);
                countUniqueSubs(i+1,s,set);
                set.remove(str);
            }

        }

    }
}