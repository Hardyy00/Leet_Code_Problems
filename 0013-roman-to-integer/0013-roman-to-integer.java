class Solution {
    public int romanToInt(String s) {
        
        int n= s.length();
        int ans= 0;

        Map<Character, Integer> map = new HashMap<>();

        map.put('I', 1);
        map.put('V',5);
        map.put('X', 10);
        map.put('L',50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        for(int i=0;i < n-1;i++){

            char first = s.charAt(i), second = s.charAt(i+1);

            if(map.get(first) < map.get(second)){
                ans -= map.get(first);
            }else{
                ans += map.get(first);
            }
        }

        ans += map.get( s.charAt(n-1) );

        return ans;
    }
}