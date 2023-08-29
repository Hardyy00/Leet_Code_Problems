class Pair{

    int index;
    boolean isRecipe;

    public Pair(int index, boolean isRecipe){

        this.index = index;
        this.isRecipe = isRecipe;
    }

    @Override
    public String toString(){

        return  "{ " +index + " " + isRecipe + " }";
    }
}
class Solution {
    public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {

        Map<String, Pair> map = new HashMap<>(); 
        Queue<String> q = new LinkedList<>();

        int index = 0;

        for(String recipe : recipes){

            map.put(recipe, new Pair(index++, true));
        }
        
        for(int i=0;i<recipes.length;i++){

            for(String s : ingredients.get(i)){

                if(!map.containsKey(s)){

                    map.put(s, new  Pair(index++, false));
                }
                
            }
        }
        
        

        for(String s : supplies){
            q.offer(s);
        }

        int m = map.size();

        int[] indegree = new int[recipes.length];

        List<List<String>> adj = new ArrayList<>();

        for(int i=0;i<m;i++) adj.add(new ArrayList<>());

        for(int i=0;i<recipes.length; i++){

            String s = recipes[i];
            indegree[i] = ingredients.get(i).size();

            for(String it : ingredients.get(i)){

                int in = map.get(it).index;
                adj.get(in).add(s);
            }
        }

        List<String> ans = new ArrayList<>();

        while(!q.isEmpty()){

            String s= q.poll();


            if(map.containsKey(s) == false) continue;

            Pair p = map.get(s);

            int in = p.index;
            boolean isRecipe= p.isRecipe;

            if(isRecipe) ans.add(s);

            for(String it : adj.get(in)){

                int recIndex = map.get(it).index;

                indegree[recIndex]--;

                if(indegree[recIndex]==0) q.offer(it);
                
            }

        }
        return ans;
    }
}