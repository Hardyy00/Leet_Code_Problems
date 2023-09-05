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

        int[] temp = new int[n];

        for(int[] row : edges){
            temp[row[0]]++;
            temp[row[1]]++;
        }

        List<Pair> list = new ArrayList<>();

        for(int i=0;i<n;i++){
            list.add(new Pair(i, temp[i]));
        }

        Collections.sort(list, new Comparator<>(){

            @Override
            public int compare(Pair p1, Pair p2){

                return p1.indegree - p2.indegree;
            }
        });

        int val = n;

        for(int i=n-1;i>=0;i--){
            Pair pair = list.remove(list.size()-1);
            temp[pair.node] = val--;
        }

        long sum = 0;

        for(int[] row : edges){

            sum += temp[row[0]];
            sum += temp[row[1]];
        }

        return sum;
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