class Solution {
    public List<List<String>> partition(String s) {
        
        List<List<String>> answer = new ArrayList<>();

        List<String> list = new ArrayList<>();

        recursion(0,s,list,answer);

        return answer;
    }

    private void recursion(int index, String s, List<String> list, List<List<String>> answer){

        // TC : O(2^N * N)
        // SC : O(N)

        if(index >=s.length()){
            answer.add(new ArrayList<>(list));
            return;
        }
        

        StringBuilder build =new StringBuilder();

        for(int i=index;i<s.length();i++){

            build.append(s.charAt(i));
            String str = build.toString();
            if(isPalindrome(str)){
                list.add(str);
                recursion(i+1,s,list,answer);
                list.remove(list.size()-1);
            }
        }
    }

    private boolean isPalindrome(String s){

        int i=0;
        int j = s.length()-1;

        while(i < j){

            if(s.charAt(i) != s.charAt(j)) return false;

            i++;
            j--;
        }

        return true;
    }
}