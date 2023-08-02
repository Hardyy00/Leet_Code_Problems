class Pair{

    String word;
    int level;

    public Pair(String word, int level){

        this.word = word;
        this.level = level;
    }
}
class Solution {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        
        Set<String> set = new HashSet<>();

        for(String s : wordList) set.add(s);

        Queue<Pair> queue = new LinkedList<>();

        queue.offer(new Pair(beginWord,1));
        set.remove(beginWord);     // remove the visited word, since visiting it again
        // would lead to a long again , and we don't want that

        while(!queue.isEmpty()){

            String word = queue.peek().word;
            int level = queue.peek().level;

            queue.poll();

            if(word.equals(endWord)) return level;

            char[] w = word.toCharArray();

            for(int i=0;i<word.length();i++){

                char save = w[i];

                for(char ch = 'a' ; ch<='z';ch++){

                    w[i] = ch;

                    String newWord = new String(w);

                    if(set.contains(newWord)){

                        queue.offer(new Pair(newWord,level+1));

                        set.remove(newWord);
                    }
                }

                w[i] = save;

            }
        }

        return 0;
    }
}