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

        // return solve1(n, edges);

        return solve2(n, edges);
        
    }

    public long solve2(int n, int[][] edges){

        // Using sorting 
        // TC : O(M) + O(N) + O(N*Log N) + O(N) + O(M)
        // SC : O(N) + O(N)  (temp + numbers)

        int[] indegree = new int[n];  // using it to hold indegree as well as numbers

        for(int[] row : edges){
            indegree[row[0]]++;
            indegree[row[1]]++;
        }

        Arrays.sort(indegree);

        int number = 1;

        long ans = 0;

        for(int i : indegree){

            ans += (long)i*number;
            number++;
        }

        return ans;
    }

    public long solve1(int n, int[][] edges){


        // Using Priority Queue
        // The node with maximum indegree, occurs maximum times in the roads
        // so on assigning it a big number we can make the sum bigger
        // hence assigning number to nodes according to their indegree

        // TC : O(M) + O(N) + O(N*Log N) + O(N*Log N) + O(N) + O(M)
        // SC : O(N) + O(N) + O(N)  (indegree + pq + numbers)
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

            numbers[pair.node] = val--;

        }

        long sum = 0;

        for(int[] row : edges){

            sum += numbers[row[0]];
            sum += numbers[row[1]];
        }

        return sum;
    }
}