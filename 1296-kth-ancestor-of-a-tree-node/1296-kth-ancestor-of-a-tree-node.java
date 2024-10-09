class TreeAncestor {

    int MAXROWS = (int) ( Math.log(5e4) / Math.log(2) ) + 1; 
    int[][] binaryLifting;

    public TreeAncestor(int n, int[] parent) {

        // using binary lifting, but taking care of -1,

        // TC : O(q * logN)
        // SC : O(logN * N)

        binaryLifting = new int[MAXROWS][n];

        build(parent);
        
    }

    private void build(int[] parent){

        System.arraycopy(parent,0, binaryLifting[0], 0, parent.length);

        for(int k=1;k<MAXROWS;k++){

            for(int node=0;node<parent.length;node++){

                if(binaryLifting[k-1][node] == -1){
                    binaryLifting[k][node] = -1;
                }else{

                    binaryLifting[k][node] = binaryLifting[k-1][ binaryLifting[k-1][node] ];
                }
            }

        }
    }
    
    public int getKthAncestor(int node, int k) {

        for(int i=0;i<MAXROWS;i++){

            int mask = (1 << i);

            if( (mask & k) > 0  ){

                node = binaryLifting[i][node];

                if(node == -1){
                    return -1;
                }
            }
        }

        return node;
        
    }
}

/**
 * Your TreeAncestor object will be instantiated and called as such:
 * TreeAncestor obj = new TreeAncestor(n, parent);
 * int param_1 = obj.getKthAncestor(node,k);
 */