class Solution {
    public boolean isBipartite(int[][] graph) {
        
        // if in a graph , we can apply two colors, such that no two adjacent nodes have same color , then
        // its a bipartite graph
        // if the graph has odd length cycle only then its a bipartite graph

        // hence coloring adjacent nodes wih yellow and green color , 0==yellow and 1 == green

       int v = graph.length;
       int[] colors = new int[v];   // to store colors (also acts as visit array)

       Arrays.fill(colors,-1);      // when colors[i]==-1, it has not been colored

    //    Queue<Integer> queue = new LinkedList<>();

       for(int i=0;i<v;i++){

           // if not colored, then traverse it
           if(colors[i]==-1){

               // if not a bipartite , then return false immediately
            //    if(!bfs(i,queue,graph,colors)) return false;

                if(!dfs(i,0,graph,colors)) return false;
           }
       }

       return true;
    }

    private boolean dfs(int node, int color ,int[][] graph, int[] colors){

        colors[node] = color;

        for(int neighbor : graph[node]){

            if(colors[neighbor]==-1){
        
                if( !dfs(neighbor,color ^ 1 ,graph, colors)) return false;

            }else if(colors[neighbor]==color){
                return false;
            }
        }

        return true;
    }

    private boolean bfs(int start , Queue<Integer> queue,int[][] graph, int[] colors){

        queue.offer(start);

        colors[0] = 0;   // 0 == yellow color, 1 == green color

        while(!queue.isEmpty()){

            int node = queue.poll();
            int color = colors[node];

            for(int neighbor : graph[node]){
                
                // it is not colored, so color it an push it in queue
                if(colors[neighbor]==-1){

                    queue.offer(neighbor);
                    colors[neighbor] = color==0 ? 1 : 0;    // color it with different color

                // if node is already visited and has the same color as it, then it is not a bipartite graph
                // cause we have node with same color as its adjacent node  
                }else if(colors[neighbor]==color){
                    return false;
                }
            }
        }

        return true;    // the curret component is bipartite
    }    
}