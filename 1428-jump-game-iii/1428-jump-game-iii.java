class Solution {
    public boolean canReach(int[] arr, int start) {
        
    //    return solve1(arr,start);

        return solve2(arr, start);
    }

    private boolean solve2(int[] arr, int start){

        int n = arr.length;
        boolean[] visit = new boolean[n];

        return dfs(start, arr, visit);
    }

    private boolean dfs(int index, int[] arr, boolean[] visit){

        if(arr[index]==0) return true;

        visit[index] = true;
        int n = arr.length;

        int plus = index + arr[index];

        if(plus >=0 && plus< n && !visit[plus]){

            if( dfs(plus, arr, visit)) return true;

        } 

        int minus = index - arr[index];

        if( minus >=0 && minus < n && !visit[minus] ) {

            if( dfs(minus, arr, visit) ) return true;
        }

        return false;
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

            // if the index is within the boundary and is not visited visit it
            if(plus>=0 && plus< n && !visit[plus]){

                // at this index zero exist , so return true
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