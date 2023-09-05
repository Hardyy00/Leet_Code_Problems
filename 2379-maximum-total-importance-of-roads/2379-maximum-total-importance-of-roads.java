class Pair{

    int node;
    int indegree;

    public Pair(int node, int indegree){

        this.node = node;
        this.indegree = indegree;
    }
}
class Solution {
    public long maximumImportance(int n, int[][] edges) {

        int[] indegree = new int[n];

        for(int[] row : edges){

            indegree[row[0]]++;
            indegree[row[1]]++;
        }
        
        PriorityQueue<Pair> pq = new PriorityQueue<>((x,y) -> y.indegree - x.indegree);

        for(int i=0;i<n;i++){
            pq.offer(new Pair(i, indegree[i]));
        }

        int[] numbers = new int [n];

        int val =  n;

        while(!pq.isEmpty()){

            Pair pair = pq.poll();

            numbers[pair.node] = n--;

        }

        long sum = 0;

        for(int[] row : edges){

            sum += numbers[row[0]];
            sum += numbers[row[1]];
        }

        return sum;
        
    }
}