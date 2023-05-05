class Solution {
    public int maxVowels(String s, int k) {

        int countVowel = 0;
        for(int i=0;i<k;i++){
            if(isVowel(s.charAt(i)))
                countVowel++;
        }

        int maxVowel = countVowel;

        for(int i=k;i<s.length();i++){

            if(isVowel(s.charAt(i)))
                countVowel++;
            
            if(isVowel(s.charAt(i-k)))
                countVowel--;

            maxVowel = Math.max(maxVowel,countVowel);
        }

        return maxVowel;
        
    }

    private static boolean isVowel(char ch){

        switch(ch){
            case 'a' : 
            case 'e' :
            case 'i' :
            case 'o' :
            case 'u' :
                return true;
        }

        return false;
    }
}