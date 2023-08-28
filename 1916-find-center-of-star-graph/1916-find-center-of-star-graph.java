class Solution {
    public int findCenter(int[][] edges) {
        
    //    return solve(edges);

        return solve2(edges);
    }

    private int solve2(int[][] edges){

        if(edges[0][0]==edges[1][0]) return edges[0][0];

        if(edges[0][0] == edges[1][1]) return edges[0][0];

        if(edges[0][1] == edges[1][0]) return edges[0][1];

        if(edges[0][1] == edges[1][1]) return edges[0][1];

        return -1;
    }

    private int solve(int[][] edges){
        // TC : O(E)
        //  SC : O(E) , as e == n-1

        int n = edges.length+1;  // e == n-1 => n = e+1

        int[] indegree = new int[n+1];  // as nodes, are from 1 to n    

        for(int[] row : edges){

            indegree[row[0]]++;
            indegree[row[1]]++;

            // check the degree right here
            if(indegree[row[0]]==n-1) return row[0];

            if(indegree[row[1]] == n-1) return row[1];

        }

        return -1;  // no center node was found
    }
}