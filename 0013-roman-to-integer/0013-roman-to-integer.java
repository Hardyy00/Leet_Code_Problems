class Solution {
    public int romanToInt(String s) {

        // use if else to check of the before condition if it satisfies then
        // minus that particular value from the answer

        // TC : O(N)
        // SC : O(7)
        
        Map<Character,Integer> map = new HashMap<>();

        map.put('I',1);
        map.put('V', 5);
        map.put('X',10);
        map.put('L',50);
        map.put('C',100);
        map.put('D',500);
        map.put('M', 1000);

        int ans = 0;

        int n= s.length();

        for(int i=n-1;i>=0;i--){

            char ch = s.charAt(i);
            if(ch=='I' || ch=='X' || ch=='C'){

                if(ch=='I'){

                    if(i+1 < n && (s.charAt(i+1)=='V' || s.charAt(i+1)=='X')){
                        ans -= map.get(ch);
                    }else{
                        ans += map.get(ch);
                    }
                }else if(ch=='X'){

                    if(i+1 < n && (s.charAt(i+1)=='L' || s.charAt(i+1)=='C')){
                        ans -= map.get(ch);
                    }else{
                        ans += map.get(ch);
                    }

                }else if(ch=='C'){

                    if(i+1 < n && (s.charAt(i+1)=='D' || s.charAt(i+1)=='M')){
                        ans -= map.get(ch);
                    }else{
                        ans += map.get(ch);
                    }
                }

            }else{

                ans += map.get(ch);
            }
        }

        return ans;
    }
}