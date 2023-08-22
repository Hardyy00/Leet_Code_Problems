class Solution {
    public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        
        int[] indegree = new int[n];
        Queue<Integer> q  = new LinkedList<>();

        for(int i=0;i<n;i++){

            if(leftChild[i]!=-1) indegree[leftChild[i]]++;

            if(rightChild[i] != -1) indegree[rightChild[i]]++;

        }

        int nodes = n;

        for(int i=0;i<n;i++){

            if(indegree[i]==0){

                q.offer(i);
                nodes--;
            }

            if(indegree[i]>1) return false;
        }

        if(q.size() != 1) return false;

        while(!q.isEmpty()){

            int node = q.poll();

            if(leftChild[node]!=-1){

                indegree[leftChild[node]]--;

                if(indegree[leftChild[node]] == 0){

                    q.offer(leftChild[node]);
                    nodes--;
                }
            }

            if(rightChild[node]!=-1){

                indegree[rightChild[node]]--;

                if(indegree[rightChild[node]] == 0){

                    q.offer(rightChild[node]);
                    nodes--;
                }
            }
        }

        return nodes==0;


    }
}