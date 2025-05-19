class Solution {
    public int findContentChildren(int[] g, int[] s) {

        // Try to distribute the cookies to less greedy children first
        // TC: O(NLogN + MlogM)
        // SC : O(1)
        
        Arrays.sort(g);
        Arrays.sort(s);
        

        int n = g.length;
        int m = s.length;
        
        int i=0;
        int j=0;
        int cn = 0;

        while(i<n && j < m){

            if(s[j]>=g[i]){
                i++;
                j++;
                cn++;
            }else if(s[j] < g[i]){
                j++;
            }
        }

        return cn;
    }
}