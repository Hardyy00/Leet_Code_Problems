class Solution {
    public List<String> letterCombinations(String digits) {
        
    
        List<String> ans = new ArrayList<>();
        
        if(digits.length()==0)
            return ans;

        StringBuilder builder = new StringBuilder();

        generateCombinations(0,digits,builder,ans);

        return ans;
        
    }

    private static void generateCombinations(int index,String digits,StringBuilder builder,List<String> ans){

        if(index==digits.length()){
            String s = builder.toString();

            if(!s.isEmpty()){
                ans.add(s);
            }
            return;
        }

        char begin = digits.charAt(index);
        int start = 0;
        int times = 0;
        
        if(begin>=50 && begin<=55){
            start = (Integer.parseInt(begin+"")-2)*3 + 97;
        }else if(begin=='8'){
            start = 116;
        }else if(begin=='9'){
            start = 119;
        }

        times = begin=='9' || begin=='7'? 4 : 3;
        times+=start;

        for(int i=start;i<times;i++){
            builder.append((char)i);
            generateCombinations(index+1,digits,builder,ans);
            builder.delete(builder.length()-1,builder.length());
        }
    }
}