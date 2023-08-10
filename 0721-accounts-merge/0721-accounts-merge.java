class DisjointSet {

    // path compression refer to connecting the node directly to the ultimate node, while returning from the recursion
    private int[] parent;

    private int[] size;     // using only one, (rank or size)

    public DisjointSet(int n){

        this.parent = new int[n+1]; // n+1 because, indexing might start from 1
        this.size = new int[n+1];

        for(int i=0;i<=n;i++) parent[i] = i;

        Arrays.fill(size, 1);       // initially nodes are alone , so their size 1
    }

    public int findParent(int node){

        // TC : O(4*a) == O(constant time) (a = alpha , a is very near to 1)

        // if the parent of the node is the node itself , then he is the ultimate parent
        if(parent[node]==node) return node;

        // on returning apply path-Compression, connect the node, to the ultimate node
        return parent[node] = findParent(parent[node]);
    }

    public void unionBySize(int u, int v){

        // TC : O(4*a) == O(constant time) (a = alpha , a is very near to 1)

        int ultimateParentOfU = findParent(u);
        int ultimateParentOfV = findParent(v);

        if(ultimateParentOfU==ultimateParentOfV) return;

        if(size[ultimateParentOfU] > size[ultimateParentOfV]){

            parent[ultimateParentOfV] = ultimateParentOfU;      // connect v to u
            size[ultimateParentOfU] += size[ultimateParentOfV];     // as nodes are now connected to u, increase its size

        }else{      // if the sizes are equal , then it doesn't , which nodes, we connect ,and we have to increase the size in both the cases

            parent[ultimateParentOfU] = ultimateParentOfV;      // connect u to v
            size[ultimateParentOfV] += size[ultimateParentOfU];
        }
    }

}

class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        

        Map<String, Integer> map = new HashMap<>();

        int n = accounts.size();
        DisjointSet ds = new DisjointSet(n);


        for(int i=0;i<n;i++){

            for(int j=1;j<accounts.get(i).size();j++){

                if(map.containsKey(accounts.get(i).get(j))){

                    int parent = map.get(accounts.get(i).get(j));

                    ds.unionBySize(parent, i);
                }else{

                    map.put(accounts.get(i).get(j), i);
                }
            }
        }

        List<List<String>> list = new ArrayList<>();
        for(int i=0;i<n;i++) list.add(new ArrayList<>());

        for(Map.Entry<String,Integer> it : map.entrySet()){

            String s = it.getKey();
            int parent = it.getValue();

            int uParent= ds.findParent(parent);

            list.get(uParent).add(s);
        }

        List<List<String>> ans = new ArrayList<>();

        for(int i=0; i<n;i++){

            if(list.get(i).isEmpty()) continue;


            Collections.sort(list.get(i));

            String name = accounts.get(i).get(0);

            List<String> ls = new ArrayList<>();

            ls.add(name);

            for(String s : list.get(i)){

                ls.add(s);
            }

            ans.add(ls);

        }

        return ans;

        

        
    }
}