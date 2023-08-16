
class Solution {
public:
    vector<double> calcEquation(vector<vector<string>>& equations, vector<double>& values, vector<vector<string>>& queries) {
        int it = 0;
        unordered_map<string, int> map;

        for (vector<string>& eq : equations) {
            string a = eq[0];
            string b = eq[1];

            if (!map.count(a)) {
                map[a] = it++;
            }

            if (!map.count(b)) {
                map[b] = it++;
            }
        }

        int n = map.size();

        vector<vector<double>> mat(n, vector<double>(n, -1));

        for (int i = 0; i < n; ++i) {
            mat[i][i] = 1;
        }

        it = 0;
        for (vector<string>& eq : equations) {
            int a1 = map[eq[0]];
            int a2 = map[eq[1]];

            double val = values[it++];

            mat[a1][a2] = val;
            mat[a2][a1] = 1 / val;
        }

        for (int via = 0; via < n; ++via) {
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (mat[i][via] != -1 && mat[via][j] != -1) {
                        mat[i][j] = max(mat[i][via] * mat[via][j], mat[i][j]);
                    }
                }
            }
        }

        vector<double> ans;
        it = 0;
        for (vector<string>& q : queries) {
            string s1 = q[0];
            string s2 = q[1];

            if (!map.count(s1) || !map.count(s2)) {
                ans.push_back(-1);
            } else {
                int i1 = map[s1];
                int i2 = map[s2];

                ans.push_back(mat[i1][i2]);
            }
        }

        return ans;
    }
};
