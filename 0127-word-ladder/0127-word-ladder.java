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

        // using BFS
        // changing every character of a word, and finding it in the set

        // TC : O(N*M*26) (as queue will almost contain N elements + in the inner
        // loop we are creating M length string therefore O(N*M*M*26) where M is the length of a word)
        
        Set<String> set = new HashSet<>();

        for(String s : wordList) set.add(s);

        Queue<Pair> queue = new LinkedList<>();

        queue.offer(new Pair(beginWord,1)); // starting with begin  word and 1 level

        set.remove(beginWord);     // remove the visited word, since visiting it again
        // would lead to a long again , and we don't want that

        while(!queue.isEmpty()){

            String word = queue.peek().word;
            int level = queue.peek().level;

            queue.poll();

            if(word.equals(endWord)) return level;      // we reach end word

            char[] w = word.toCharArray();  // to do changes efficiently

            for(int i=0;i<word.length();i++){

                char save = w[i];       // save the initail character somewhere

                for(char ch = 'a' ; ch<='z';ch++){

                    w[i] = ch;  // change the ith character

                    String newWord = new String(w); //form the new string

                    if(set.contains(newWord)){

                        queue.offer(new Pair(newWord,level+1));

                        set.remove(newWord); // remove the visited word, since visiting it again
        // would lead to a long again , and we don't want that
                    }
                }

                w[i] = save;        // assign the initial character

            }
        }

        return 0;    // we weren't able to create the sequence
    }
}