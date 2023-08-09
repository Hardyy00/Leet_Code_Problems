//{ Driver Code Starts


import java.util.*;
import java.io.*;
import java.lang.*;

public class Main{
	static BufferedReader br;
	static PrintWriter ot;
    public static void main(String args[]) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		ot = new PrintWriter(System.out);
		int t = Integer.parseInt(br.readLine().trim());
		while(t-- > 0){
			String s[] = br.readLine().trim().split(" ");
			int V = Integer.parseInt(s[0]);
			int E = Integer.parseInt(s[1]);
			int edges[][] = new int[E][3];
			for(int i = 0; i < E; i++){
				s = br.readLine().trim().split(" ");
				edges[i][0] = Integer.parseInt(s[0]);
				edges[i][1] = Integer.parseInt(s[1]);
				edges[i][2] = Integer.parseInt(s[2]);
			}
			ot.println(new Solution().spanningTree(V, E, edges));
		}
		ot.close();
	}
}
// } Driver Code Ends


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
    

class Solution{
	static int spanningTree(int v, int e, int edges[][]){
        
        int m = edges.length;
        Integer[][] edgesObj = new Integer[m][3];
        int i =0;
        for(int[] row : edges){
            
            edgesObj[i][0] = row[0];
            edgesObj[i][1] = row[1];
            edgesObj[i][2] = row[2];
            
            i++;
        }
        
        Arrays.sort(edgesObj, new Comparator<Integer[]>(){
            
            @Override
            public int compare(Integer[] r1, Integer[] r2){
                
                int a = r1[2];
                int b = r2[2];
                
                return a-b;
            }
        });
        
        
        DisjointSet ds = new DisjointSet(v);
        int sum = 0;
        for(Integer[] row : edgesObj){
            
            int u1 = row[0];
            int v1 = row[1];
            int w = row[2];
            
            if(ds.findParent(u1)!= ds.findParent(v1)){
                
                sum += w;
                ds.unionBySize(u1,v1);
            }
        }
        
        return sum;
        

	}
}