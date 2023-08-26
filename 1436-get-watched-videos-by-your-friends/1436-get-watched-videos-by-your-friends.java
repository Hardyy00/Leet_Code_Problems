class Solution {
    public List<String> watchedVideosByFriends(List<List<String>> watchedVideos, int[][] friends, int id, int level) {
        
        int n = friends.length;
        boolean[] visit =new boolean[n];

        Queue<Integer> q = new LinkedList<>();

        Map<String, Integer> map = new HashMap<>();

        visit[id] = true;
        q.offer(id);

        int lev = 0;

        while(!q.isEmpty()){

            for(int i=q.size();i>0;i--){
                
                int node = q.poll();

                if(lev == level){

                    for(String s : watchedVideos.get(node)){

                        map.put(s, map.getOrDefault(s, 0) + 1);
                    }
                }else{


                    for(int adjNode : friends[node]){

                        if(!visit[adjNode]){
                            q.offer(adjNode);
                            visit[adjNode] = true;
                        }
                    }
                }

            }

            lev++;
        }

        List<Map.Entry<String, Integer>> sort = new ArrayList<>();


        for(Map.Entry<String, Integer> est : map.entrySet()){

            sort.add(est);
            
        }

        Collections.sort(sort, new Comparator<Map.Entry<String, Integer>>(){

            @Override
            public int compare(Map.Entry<String, Integer> p1, Map.Entry<String, Integer>  p2){

                if(p1.getValue().equals(p2.getValue())) return p1.getKey().compareTo(p2.getKey());

                return p1.getValue()- p2.getValue();
            }
        });


        List<String> ans = new ArrayList<>();

        for(Map.Entry<String, Integer> est  : sort){
            ans.add(est.getKey());
        }       

        return ans;



    }
}