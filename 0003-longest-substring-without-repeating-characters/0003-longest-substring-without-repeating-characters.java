class Solution {
    public int lengthOfLongestSubstring(String s) {
        
        // return bruteForce(s);

        return optimalSolution(s);
    }

    private int optimalSolution(String s){

        // Optimal Approach : use sliding window for ,finding such string with non repeating characters
        // if current characters' frequency becomes > 1, then move the window until is becomes equal
        // make frequency array of 256, because it contains, all characters

        // TC : O(N)
        // SC : O(256)

        int[] fre = new int[256];

        int si = 0;
        int ei = 0;
        int maxi = 0;

        int n = s.length();

        while(ei < n){

            fre[s.charAt(ei)]++;


            // shrink the window
            while(fre[s.charAt(ei)] > 1 && si<=ei){

                fre[s.charAt(si)]--;
                si++;
            }

            int len = ei-si+1;  // calculate window length
            maxi = Math.max(maxi, len);

            ei++;
        }

        return maxi;
    }

    private int bruteForce(String s){

        // Brute Force : Iterate over all the substring and , find the substring with unique characters
        // use a frequency array for each substring, 

        // TC : O(N^2)
        // SC: O(256)

        int n= s.length();

        int maxi = 0;

        for(int i=0;i<n;i++){

            int[] fre= new int[256];

            for(int j=i;j<n;j++){

                fre[s.charAt(j)-'a']++;

                if(fre[s.charAt(j)-'a'] >1) break;

                maxi = Math.max(maxi, j-i+1);
            }
        }

        return maxi;
    }
}