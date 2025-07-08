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
        
        // TC : O(N*M*Log M) : where m is the number of strings for each account
        // TC : O(N*M * K *Log(N*M)) , tc for sortig n *m strings , n*m*k * Log(n*m), as there will be
        // n *m *log(n*m) comparisions,and length of each comparision if k , on average

        // SC : O(N*M) +O(N*M) + O(N*M)

        Map<String, Integer> map = new HashMap<>();

        int n = accounts.size();
        DisjointSet ds = new DisjointSet(n);  // to make connection in case we find similar accounts


        for(int i=0;i<n;i++){

            for(int j=1;j<accounts.get(i).size();j++){

                // if the mail is already in the map, that means someone must already have it
                // in this case , this account too belongs to him, so make this node
                // the child of the other node
                if(map.containsKey(accounts.get(i).get(j))){

                    // get parent through the map, as this string would be mapped to its original parent
                    int parent = map.get(accounts.get(i).get(j));   

                    ds.unionBySize(parent, i);
                }else{

                    // otherwise, put this string into the map indicating , that the parent
                    // of this mail is i
                    map.put(accounts.get(i).get(j), i);
                }
            }
        }

        // to merge accounts , just mergeing all the email to their original accounts
        List<List<String>> list = new ArrayList<>();
        for(int i=0;i<n;i++) list.add(new ArrayList<>());   // we don't know which index
        // would be empty so , create all indexes

        // iterate over all the strings and their parents in the map
        for(Map.Entry<String,Integer> it : map.entrySet()){

            String s = it.getKey();
            int parent = it.getValue();

            int uParent= ds.findParent(parent); // get the ultimate parent of account

            list.get(uParent).add(s);   // the mail belongs to uparent, so add it
        }

        List<List<String>> ans = new ArrayList<>();

        for(int i=0; i<n;i++){

            // this account has been merged, so it is empty, no need to consider it
            if(list.get(i).isEmpty()) continue;


            Collections.sort(list.get(i));  // sort all the emails

            String name = accounts.get(i).get(0);   // get the name of the account holder

            List<String> ls = new ArrayList<>();    // to contain a account

            ls.add(name);

            for(String s : list.get(i)){    // add all the emails

                ls.add(s);
            }

            ans.add(ls);    // add account

        }

        return ans;

        

        
    }
}