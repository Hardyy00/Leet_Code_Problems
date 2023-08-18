class Pair{

    String s;
    boolean isVisit;

    public Pair(String s){

        this.s = s;
        this.isVisit = false;
    }
}

class Solution {
    public List<String> findItinerary(List<List<String>> tickets) {
        
        Map<String, List<Pair>> map = new HashMap<>();

        for(List<String> edge : tickets){

            if(!map.containsKey(edge.get(0))) map.put(edge.get(0), new ArrayList<>() );

            if(!map.containsKey(edge.get(1))) map.put(edge.get(1), new ArrayList<>() );
        }

        int n = map.size();

        for(List<String> edge : tickets){

            String s1 = edge.get(0);
            String s2 = edge.get(1);

           map.get(s1).add(new Pair(s2));

        }

        Comparator<Pair> comp = new Comparator<>(){

            @Override
            public int compare(Pair p1, Pair p2){

                return p1.s.compareTo(p2.s);
            }
        };

        for(List<Pair> lis : map.values()){

            Collections.sort(lis, comp);

        }

        List<String> ans = new ArrayList<>();
        ans.add("JFK");

        dfs("JFK",map, ans, tickets.size());

        return ans;
    }

    private boolean dfs(String node, Map<String, List<Pair>> map,List<String> ans, int size){
        
        if( ans.size() == size + 1) return true;

        for(Pair pair: map.get(node)){

            if( !pair.isVisit ){

                ans.add(pair.s);
                pair.isVisit = true;

                if(dfs(pair.s,map,ans,size)) return true;

                pair.isVisit = false;
                ans.remove(ans.size()-1);
            }
        }

        return false;
    }
}