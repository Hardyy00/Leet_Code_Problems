//{ Driver Code Starts
//Initial Template for Java

import java.util.*;
import java.lang.*;
import java.io.*;


// } Driver Code Ends
//User function Template for Java

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
    
    public List<Integer> numOfIslands(int rows, int cols, int[][] operators) {
        
        DisjointSet ds = new DisjointSet(rows * cols);
        List<Integer> ans = new ArrayList<>();
        
        int[][] mat = new int[rows][cols];
        int count = 0;
        
        int[] ra = { -1,0,1,0};
        int[] ca = {0,1,0,-1};

        
        for(int[] op : operators){
            
            
            int row = op[0];
            int col = op[1];
            int node = row * cols + col;
            
            if(mat[row][col]==1){
                ans.add(count);
                continue;
            }
            
            mat[row][col] =1;
            count++;
            
            for(int i=0;i<4;i++){
                
                int nr = row + ra[i];
                int nc = col + ca[i];
                
                if(nr>=0 && nr<rows && nc>=0 && nc<cols && mat[nr][nc]==1){
                    
                    int adjNode = nr*cols + nc;
                    
                    if(ds.findParent(node) != ds.findParent(adjNode)){
                        
                        ds.unionBySize(node, adjNode);
                        count--;
                    }
                    
                }
                
            }
            
            ans.add(count);
        }
        
        return ans;
    }
    
    
    
}







//{ Driver Code Starts.

class GFG {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- > 0) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            int  k= sc.nextInt();
            int[][] a = new int[k][2];
            for(int i=0;i<k;i++){
            
                a[i][0] = sc.nextInt();
                a[i][1] = sc.nextInt();
            }
            
            Solution obj = new Solution();
            List<Integer> ans = obj.numOfIslands(n,m,a);
           
            for(int i:ans){
                System.out.print(i+" ");
            }
            System.out.println();
        }
    }
}

// } Driver Code Ends