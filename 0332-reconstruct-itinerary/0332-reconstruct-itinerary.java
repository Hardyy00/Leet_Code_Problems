class Pair{

    String s;
    boolean isVisit;

    public Pair(String s){

        this.s = s;
        this.isVisit = false;  // to check if in the current path the node is visited or not
    }
}

class Solution {
    public List<String> findItinerary(List<List<String>> tickets) {
        
        // TC : O(E) + O(E * Log E) + O(N + E)
        Map<String, List<Pair>> map = new HashMap<>();

        // map every string to its adjacency list, and construct the graph
        for(List<String> edge : tickets){

            String s1 = edge.get(0);
            String s2 = edge.get(1);

            // if it is a new string insert it in the map
            if(!map.containsKey(s1)) map.put( s1, new ArrayList<>() );

            if(!map.containsKey( s2 )) map.put( s2, new ArrayList<>() );

            map.get(s1).add(new Pair(s2));
        }

        int n = map.size(); // unique strings 

        // so sort the pair acc. to strings
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
        ans.add("JFK");  // ADD THE FIRST FLIGHT

        dfs("JFK",map, ans, tickets.size());

        return ans;
    }

    private boolean dfs(String node, Map<String, List<Pair>> map,List<String> ans, int size){
        
        // O(V + E)   (V for visiting all the nodes and e for total loop)
        if( ans.size() == size + 1) return true; // IF WE HAVE ADD ALL THE DESTINATIONS, return true

        for(Pair pair: map.get(node)){

            if( !pair.isVisit ){  // IF THE NODE IS NOT VISITED IN THE CURRENT PATH

                ans.add(pair.s);  // ADD THE ANSWER BEFORE CALL
                pair.isVisit = true;  // SINCE WE ARE NOT VISITING THIS NODE, MARK IT AS TRUE

                if(dfs(pair.s,map,ans,size)) return true;  // ON FINDING THE ANSWER, GO BACK

                pair.isVisit = false;  // SINCE WE ARE TRYING A NEW PATH, MARK IT AS FALSE
                ans.remove(ans.size()-1);  // BACKTRACKING
            }
        }

        return false;
    }
}