class Pair{

    int node;
    int val;

    public Pair(int node,int val){

        this.node = node;
        this.val = val;
    }
}
class Solution {
    public int maxStarSum(int[] vals, int[][] edges, int k) {
        
        int n = vals.length;

        List<PriorityQueue<Pair>> adj = new ArrayList<>();

        for(int i=0;i<n;i++) adj.add(new PriorityQueue<Pair>((x,y)-> y.val - x.val));

        for(int[] row : edges){

            adj.get(row[0]).offer(new Pair(row[1],vals[row[1]]));

            adj.get(row[1]).offer(new Pair(row[0], vals[row[0]]));
        }

        int maxi = Integer.MIN_VALUE;

        for(int i=0;i<n;i++){

            int sum = vals[i];
            PriorityQueue<Pair> pq = adj.get(i);

            int temp = k;

            while(!pq.isEmpty() && k>0 && pq.peek().val >=0){

                sum += pq.poll().val;
                k--;
            }

            k = temp;

            maxi = Math.max(maxi,sum);
        }

        return maxi;
        
    }
}