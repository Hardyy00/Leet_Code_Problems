class Solution {
public:
    int snakesAndLadders(vector<vector<int>>& board) {
        int n = board.size();
        int last = n * n;
        vector<int> arr(last + 1, 1e6);
        arr[1] = 0;

        queue<int> q;
        q.push(1);

        while (!q.empty()) {
            int cell = q.front();
            q.pop();

            for (int i = cell + 1; i <= cell + 6 && i <= last; ++i) {
                int row = getRow(i, n);
                int col = getCol(row, i, n);

                if (board[row][col] != -1) {
                    int reach = board[row][col];
                    if (arr[cell] + 1 < arr[reach]) {
                        arr[reach] = arr[cell] + 1;
                        q.push(reach);
                    }
                } else {
                    if (arr[cell] + 1 < arr[i]) {
                        arr[i] = arr[cell] + 1;
                        q.push(i);
                    }
                }
            }
        }

        return arr[last] == 1e6 ? -1 : arr[last];
    }

private:
    int getRow(int val, int n) {
        return n - static_cast<int>(ceil(static_cast<double>(val) / n));
    }

    int getCol(int row, int val, int n) {
        if ((n % 2 == 0 && row % 2 != 0) || (n % 2 != 0 && row % 2 == 0)) {
            int rem = val % n - 1;
            if (rem == -1) rem = n - 1;
            return rem;
        }

        int rem = val % n;
        if (rem != 0) rem = n - rem;

        return rem;
    }
};
