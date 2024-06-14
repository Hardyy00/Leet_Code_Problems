class Solution {
    public List<String> wordSubsets(String[] words1, String[] words2) {
        
        int[] fre =new int[26];

        for(String s : words2){

            int[] tempFre = new int[26];

            for(int i=0;i<s.length();i++){
                tempFre[s.charAt(i)-'a']++;

                fre[s.charAt(i)-'a'] = Math.max(fre[s.charAt(i)-'a'], tempFre[s.charAt(i)-'a']);
            }

            // System.out.println(Arrays.toString(tempFre));
        }

        
        // System.out.println(Arrays.toString(fre));

        List<String> ans = new ArrayList<>();

        for(String s : words1){

            int[] masterFre =new int[26];
            for(int i=0;i<s.length();i++){
                masterFre[s.charAt(i)-'a']++;
            }

            boolean f = true;

            for(int i=0;i<26;i++){

                if(fre[i] > masterFre[i]){
                    f = false;
                    break;
                }
            }

            if(f){
                ans.add(s);
            }
        }

        return ans;
    }
}