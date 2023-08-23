class Pair{

    public char ch;
    public int fre;

    public Pair(char ch, int fre){

        this.ch = ch;
        this.fre = fre;
    }
}

class Solution {
    public String reorganizeString(String s) {

        if(s.length()==1) return s;
        
        PriorityQueue<Pair> pq = new PriorityQueue<>((x,y)-> y.fre - x.fre);
        int[] fre = new int[26];

        for(int i=0;i<s.length();i++){

            fre[s.charAt(i)-'a']++;
        }

        for(int i=0;i<26;i++){

            if(fre[i]!=0){
                pq.offer(new Pair((char)(i+97), fre[i]));
            }
        }

        if(pq.size()==1) return "";

        StringBuilder ans = new StringBuilder();

        // System.out.println(pq);

        while(ans.length() < s.length()){

            Pair p1 = pq.poll();
            Pair p2 = null;

            p1.fre--;

            ans.append(p1.ch);

            if(pq.size()>=1){

                p2  = pq.poll();
                p2.fre--;
                ans.append(p2.ch);
            }

            if(p1.fre>0) pq.offer(p1);
            if(p2!= null && p2.fre >0 ) pq.offer(p2);

            if(s.length() - ans.length() >=2 && pq.size()==1) return "";
            
        }

        return ans.toString();
    }
}