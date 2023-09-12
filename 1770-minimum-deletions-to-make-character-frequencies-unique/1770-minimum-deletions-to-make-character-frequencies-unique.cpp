class Solution {
public:
    int minDeletions(std::string s) {
        
        if (s.length() == 1) return 0;

        std::vector<int> fre(26, 0);
        int diff = 0;

        for (int i = 0; i < s.length(); i++) {

            char ch = s[i];
            fre[ch - 'a']++;

            if (fre[ch - 'a'] == 1) diff++;
        }

        std::sort(fre.begin(), fre.end());

        int lastIndex = 26 - diff;
        int ans = 0;

        for (int i = 24; i >= lastIndex; i--) {

            if (fre[i] >= fre[i + 1] && fre[i + 1] != 0) {
                int freToBe = fre[i + 1] - 1;
                int del = fre[i] - freToBe;
                fre[i] = freToBe;
                ans += del;
            }
            else if (fre[i + 1] == 0) {
                ans += fre[i];
                fre[i] = 0;
            }
        }

        return ans;
    }
};
