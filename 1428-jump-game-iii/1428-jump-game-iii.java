class Solution {
    public boolean canReach(int[] arr, int start) {
        
       return solve1(arr,start);
    }

    private boolean solve1(int[] arr, int start){

        // Applying bfs
        // TC : O(N)
        // SC : O(2N)

        Queue<Integer> q = new LinkedList<>();
        int n  = arr.length;
        boolean[] visit = new boolean[arr.length];  // so that i won't visit the same index again

        q.offer(start);

        while(!q.isEmpty()){

            int curr = q.poll();

            int plus = curr + arr[curr];

            if(plus>=0 && plus< n && !visit[plus]){

                if(arr[plus] == 0) return true;

                q.offer(plus);
                visit[plus] = true;
            }

            int minus = curr - arr[curr];

            if(minus >=0 && minus < n && !visit[minus]){ 

                if(arr[minus] == 0) return true;

                q.offer(minus);
                visit[minus] = true;
            }
        }

        return false;
    }
}