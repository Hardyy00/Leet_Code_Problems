class Solution {
    Map<String, Integer> map = new HashMap<>();
    List<List<String>> ans = new ArrayList<>();

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        
        Set<String> set = new HashSet<>();

        for(String s : wordList) set.add(s);

        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        set.remove(beginWord);
        map.put(beginWord, 1);  // at level 1

        while(!queue.isEmpty()){

            String word = queue.poll();
            int level = map.get(word);

            if(word.equals(endWord)) break;

            char[] w = word.toCharArray();

            for(int i=0;i<word.length();i++){

                char save = w[i];

                for(char ch='a' ; ch<='z'; ch++){

                    w[i] = ch;
                    String s = new String(w);

                    if(set.contains(s)){

                        queue.offer(s);
                        set.remove(s);

                        map.put(s,level+1);
                    }
                }

                w[i] = save;
            }
        } 

        if(map.containsKey(endWord)){

            List<String> temp = new ArrayList<>();
            temp.add(endWord);

            dfs(endWord,temp, beginWord);
        }

        return ans;

        
    }

    private void dfs(String word, List<String> list, String beginWord){

        if(word.equals(beginWord)){
            
            Collections.reverse(list);

            ans.add(new ArrayList<>(list));

            Collections.reverse(list);

            return;
        }

        int level = map.get(word);
        char[] w = word.toCharArray();

        for(int i=0;i<word.length();i++){

            char save = w[i];

            for(char ch='a' ; ch<='z' ; ch++){

                w[i] = ch;

                String s = new String(w);

                // if 's' , is on the immediate previous level
                if(map.containsKey(s) && map.get(s) + 1 == level){

                    list.add(s);

                    dfs(s,list,beginWord);

                    list.remove(list.size()-1);
                }
            }

            w[i] = save;
        }

    }
}