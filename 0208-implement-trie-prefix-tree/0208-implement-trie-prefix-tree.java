class Node{

    Node[] links;
    boolean flag;

    public Node(){
        this.links = new Node[26];
        this.flag = false;
    }

    public boolean containsKey(char ch){

        return this.links[ch-'a'] != null;
    }

    public void put(char ch , Node node){

        this.links[ch - 'a'] = node;
    }

    public Node get(char ch){

        return this.links[ch - 'a'];
    }

    public void setEnd(){
        this.flag = true;
    }

    public boolean isEnd(){

        return flag;
    }

}
class Trie {

    private Node root;

    public Trie() { 
        
        this.root = new Node();

    }
    
    public void insert(String word) {
        
        Node node = root;

        for(int i=0;i<word.length();i++){

            if(node.containsKey(word.charAt(i)) == false){

                node.put(word.charAt(i), new Node());
            }

            node = node.get(word.charAt(i));
        }

        node.setEnd();
    }
    
    public boolean search(String word) {
        
        Node node = root;

        for(int i=0;i<word.length();i++){

            if(node.containsKey(word.charAt(i)) == false) return false;

            node = node.get(word.charAt(i));

        }

        return node.isEnd();
    }
    
    public boolean startsWith(String prefix) {
        
        Node node = root;

        for(int i=0;i<prefix.length();i++){

            if(node.containsKey(prefix.charAt(i)) == false) return false;

            node = node.get(prefix.charAt(i));
        }

        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */