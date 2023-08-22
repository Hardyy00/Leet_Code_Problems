class Solution {
    public boolean canReach(int[] arr, int start) {
        
        Queue<Integer> q = new LinkedList<>();
        int n  = arr.length;
        boolean[] visit = new boolean[arr.length];

        q.offer(start);

        while(!q.isEmpty()){

            int curr = q.poll();

            if(arr[curr] == 0) return true;

            int plus = curr + arr[curr];

            if(plus>=0 && plus< n && !visit[plus]){ 
                q.offer(plus);
                visit[plus] = true;
            }

            int minus = curr - arr[curr];

            if(minus >=0 && minus < n && !visit[minus]){ 
                q.offer(minus);
                visit[minus] = true;
            }
        }

        return false;
    }
}