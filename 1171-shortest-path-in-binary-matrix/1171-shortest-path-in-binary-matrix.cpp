class Solution {
public:
    int shortestPathBinaryMatrix(vector<vector<int>>& grid) {
        
        int n = grid.size();

        if(grid[0][0]==1 || grid[n-1][n-1]==1) return -1;

        if(n==1) return 1;

        int ra[8] = {-1,-1,-1,0,1,1,1,0};
        int ca[8] = {-1,0,1,1,1,0,-1,-1};
        queue<pair<pair<int, int>, int>> q;

        vector<vector<int>> dis(n, vector<int>(n,1e8));

        dis[0][0] = 1;

        q.push({{0,0}, 1});

        while(!q.empty()){

            int r = q.front().first.first;
            int c = q.front().first.second;
            int d = q.front().second;

            q.pop();

            for(int i=0;i<8;i++){

                int nr = r + ra[i];
                int nc = c + ca[i];

                if(nr>=0 && nr< n && nc>=0 && nc<n && grid[nr][nc]==0 && d+1 < dis[nr][nc]){

                    dis[nr][nc] = d+1;

                    if(nr==n-1 && nc==n-1) return dis[nr][nc];

                    q.push({{nr,nc}, dis[nr][nc]});
                }
            }
        }

        return -1;

    }
};