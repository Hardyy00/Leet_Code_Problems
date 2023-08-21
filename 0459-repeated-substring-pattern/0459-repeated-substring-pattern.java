class Solution {
    public boolean repeatedSubstringPattern(String s) {
        
        for(int i=1;i<=s.length()/2;i++){

            boolean allEqual = true;
            int a = 0, b = i;
            if(s.length()%i==0){
                while(b<s.length()){

                    if(s.charAt(a) != s.charAt(b)) allEqual = false;
                    a++;
                    b++;
                }

                if ( allEqual ) return true;
            }
        }

        return false;
    }
}