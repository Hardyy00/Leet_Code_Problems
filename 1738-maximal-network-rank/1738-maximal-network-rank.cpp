class Solution {
public:
    int maximalNetworkRank(int n, vector<vector<int>>& roads) {
        
        vector<int> degree(n,0);

        vector<vector<int>> mat(n, vector<int>(n,0));

        for(auto row : roads){

            degree[row[0]]++;
            degree[row[1]]++;

            mat[row[0]][row[1]] = mat[row[1]][row[0]] = 1;
        }

        int maxi = 0;

        for(int i=0;i<n;i++){

            for(int j=0;j<n;j++){

                if(i==j) continue;
                int rank = degree[i] + degree[j];

                if(mat[i][j]) rank--;

                maxi = max(maxi, rank);
            }
        }

        return maxi;
    }
};