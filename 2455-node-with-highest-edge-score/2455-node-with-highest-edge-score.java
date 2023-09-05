class Solution {
    public int edgeScore(int[] edges) {

        int n = edges.length;

        long[] edgeScore = new long[n];

        for(int i=0;i<n;i++){

            edgeScore[edges[i]] += (long)i;
        }

        long maxValue = edgeScore[0];
        // System.out.println(Arrays.toString(edgeScore));
        int index = 0;

        for(int i=1;i<n;i++){

            if(edgeScore[i] > maxValue){

                maxValue = edgeScore[i];
                index = i;
            }
        }

        return index;
        
    }
}