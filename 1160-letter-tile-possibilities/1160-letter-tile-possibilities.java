class Solution {
    public int numTilePossibilities(String tiles) {

        boolean[] visit = new boolean[tiles.length()];
        char[] c = tiles.toCharArray();
        Arrays.sort(c);
        tiles = new String(c);
        
        return countPossibilities(tiles,visit);
        
    }
    
    private static int countPossibilities(String s ,boolean[] visit){

        int count = 0;

        for(int i=0;i<s.length();i++){

            if(visit[i] || (i>0 && !visit[i-1] && s.charAt(i-1)==s.charAt(i)))
                continue;

            count++;
            visit[i] = true;
            count+= countPossibilities(s,visit);
            visit[i] = false;
        }

        return count;
    }

    
}