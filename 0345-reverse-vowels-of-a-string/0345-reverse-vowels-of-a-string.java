class Solution {
    public String reverseVowels(String s) {

        StringBuilder str = new StringBuilder(s);
        int i=0;
        int j=str.length()-1;
        while(i<j){

            if(isVowel((str.charAt(i)+"").toLowerCase())){

                while(!isVowel((str.charAt(j)+"").toLowerCase())) {
                    j--;
                }

                char a1 = str.charAt(i);
                str.replace(i,i+1,str.charAt(j)+"");
                str.replace(j,j+1,a1+"");
                j--;
            }
            i++;

        }

        return str.toString();
    }

    private static boolean isVowel(String s){

        return s.equals("a") || s.equals("e") || s.equals("i") || s.equals("o") || s.equals("u");
    }
}