class Solution {
    public boolean canFinish(int numCourses, int[][] pre) {
        
        List<List<Integer>> adj = new ArrayList<>();

        int n = numCourses;

        for(int i=0;i<n;i++){

            adj.add(new ArrayList<>());
        }

        for(int i=0;i<pre.length;i++){

            adj.get(pre[i][1]).add(pre[i][0]);
        }

        Queue<Integer> queue = new LinkedList<>();

        int[] indegree = new int[n];

        for(int i=0;i<n;i++){

            for(int node : adj.get(i)){

                indegree[node]++;
            }
        }

        int notZero = 0;

        for(int i=0;i<n;i++){

            if(indegree[i]==0) queue.offer(i);

            else notZero++;
        }

        if(queue.isEmpty()) return false;   // cycle exist so we will not be able to complete

        while(!queue.isEmpty()){

            int node = queue.poll();

            for(int next : adj.get(node)){

                indegree[next]--;

                if(indegree[next]==0){

                    queue.offer(next);
                    notZero--;
                }
            }
        }

        System.out.println(Arrays.toString(indegree));

        if(notZero==0) return true;

        return false;

    }
}