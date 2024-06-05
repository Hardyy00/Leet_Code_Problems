class Solution {
    public String fractionToDecimal(long numerator, long denominator) {

        StringBuilder sb = new StringBuilder();

        if(numerator < 0 && denominator > 0 ){
            sb.append('-');
        }else if(numerator > 0 && denominator < 0){
            sb.append('-');
        }

        numerator = Math.abs(numerator);
        denominator = Math.abs(denominator);

        // System.out.println(numerator + " " + denominator);

        sb.append(numerator / denominator);

        numerator %= denominator;

        if(numerator == 0){
            return sb.toString();
        }

        

        sb.append(".");
        int index = sb.length();
        


        Map<Long,Integer> map = new HashMap<>();
        while(numerator != 0){

            if(map.containsKey(numerator)){

                // System.out.println(index + " " + sb + " " + numerator + " " + denominator);
                
                return makeAnswer(sb, map, numerator);
            }else{


                map.put(numerator,index);
            }

            int cn = 0;

            while(numerator < denominator){
                cn++;
                numerator *= 10;
            }

            for(int i=0;i<cn-1;i++){
                sb.append('0');
            }

            sb.append(numerator/ denominator);
            index = sb.length();
            
            numerator %= denominator;
        }

        return sb.toString();
        
    }

    private String makeAnswer(StringBuilder sb, Map<Long,Integer> map, long num){

        int index = map.get(num);

        sb.append(')');
        sb.insert(index, '(');

        return sb.toString();
    }
}