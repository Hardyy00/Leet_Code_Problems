class Solution {
    int index = 0;
    public String decodeString(String s) {
        

        StringBuilder sb = new StringBuilder();

        while(index < s.length()){

            char ch = s.charAt(index);

            if(Character.isDigit(s.charAt(index))){
                index++;

                String num = Character.toString(ch);

                if(Character.isDigit(s.charAt(index))){

                    num+=s.charAt(index);
                    index++;

                }

                if(Character.isDigit(s.charAt(index))){

                    num+=s.charAt(index);
                    index++;

                }

                StringBuilder temp  = repeat(Integer.parseInt(num), s);

                sb.append(temp);
                // System.out.println(index);

            }else if(Character.isLetter(ch)){

                sb.append(ch);
            }

            index++;
        }

        return sb.toString();
    }

    private StringBuilder repeat(int times, String s ){

        StringBuilder sb = new StringBuilder();
        buildString(s,sb);

        StringBuilder ans = new StringBuilder();

        for(int i=0;i<times;i++){

            ans.append(sb);
        }

        return ans;
    }

    private void buildString(String s , StringBuilder build){

        char ch = s.charAt(index);

        if(ch==']') return;

        if(ch == '['){

            index++;
            buildString(s,build);

        } else if(Character.isLetter(ch)){

            build.append(ch);
            index++;
            buildString(s,build);

        } else{

            index++;

            String num = Character.toString(ch);

            if(Character.isDigit(s.charAt(index))){

                num+=s.charAt(index);
                index++;

            }

            if(Character.isDigit(s.charAt(index))){

                num+=s.charAt(index);
                index++;

            }
            build.append(repeat(Integer.parseInt(num),s));
            // System.out.println(index);
            index++;

            buildString(s,build);
            
        }



    }
}