class Solution {
public:
    int findCenter(vector<vector<int>>& edges) {
        
        int n = edges.size() +1;
        
        vector<int> indegree(n+1 ,0);

        for(auto row : edges){

            indegree[row[0]]++;
            indegree[row[1]]++;

            if(indegree[row[0]] == n-1) return row[0];

            if(indegree[row[1]] == n-1 ) return row[1];
        }

        return -1;
    }
};