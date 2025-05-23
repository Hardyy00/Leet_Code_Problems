class Pair{
    int num;
    int fre;

    public Pair(int num, int fre){
        this.fre = fre;
        this.num = num;
    }
}
class Solution {
    
    public int[] topKFrequent(int[] nums, int k) {

        return solve2(nums,k);
    
    }

    private int[] solve2(int[] nums, int k){
        
        // Use bucket sort
        // first get all the frequencies, then store the number with a particular frequency
        // to the index represented by the frequency atmost, an element can has n frequency

        // after that simply traverse from the back and add k elements

        // TC : O(N + N + K)
        // SC : O(K + N)

        Map<Integer,Integer> map = new HashMap<>();

        int n = nums.length;
        for(int i=0;i<n;i++){
            map.put(nums[i], map.getOrDefault(nums[i],0) + 1 );
        }

        List<Integer>[] arr = new List[n+1];

        for(Map.Entry<Integer,Integer> ent : map.entrySet()){
            if(arr[ent.getValue()] == null) arr[ent.getValue()] = new ArrayList<>();

            arr[ent.getValue()].add(ent.getKey());
        }

        int[] ans = new int[k];
        k--;

        for(int i=n;i>=0;i--){

            if(arr[i] != null){

                for(int val : arr[i]){

                   
                     ans[k--] = val;

                     if(k==-1) break;
                   
                }

                if(k==-1) break;
            }
        }

        return ans;
    }

    private int[] solve1(int[] nums, int k){
        
        // store frequency in the map
        //  
        // we can optimize it contain only top k element, by using a min heap
        // and inserting only when the data is greater than the top element

        // TC : O(N*logK)
        // SC : O(K + N)
        
        Map<Integer,Integer> map =new HashMap<>();

        for(int i=0;i<nums.length;i++){
            map.put(nums[i], map.getOrDefault(nums[i],0)+1);
        }

        PriorityQueue<Pair> pq =  new PriorityQueue<>((a,b)-> a.fre-b.fre);

        for(Map.Entry<Integer,Integer> ent : map.entrySet()){

            if(pq.size() < k) {
                pq.offer(new Pair(ent.getKey(), ent.getValue()));
            }else if(pq.peek().fre < ent.getValue()){
                pq.poll();
                pq.offer(new Pair(ent.getKey(), ent.getValue()));
            }

        }

        int[] answer = new int[k];

        int i=0;

        while(!pq.isEmpty()){
            answer[i++] = pq.poll().num;
        }

        return answer;
    
    }
}