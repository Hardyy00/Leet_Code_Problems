class Solution {
    public String predictPartyVictory(String senate) {
        
        int r = 0,d =0;
        boolean[] skip = new boolean[senate.length()];
        for(int i=0;i<senate.length();i++){
            
            if(senate.charAt(i)=='R') r++;
            else d++;
        }
        
        int skipR=0, skipD=0,i=0;
        
        
        while(r>0 && d>0){
            
            char ch = senate.charAt(i);
            
            // System.out.println(i + " " + " r : " + r + " d : " + d + " skipD : " + skipD + " skipR : " + skipR);
            
            
            if(skip[i]){
                i= (i+1)%senate.length();
                continue;
            }
            
            if(ch=='R' && skipR>0){
                skip[i] = true;
                skipR--;
            }else if(ch=='D' && skipD>0){
                skip[i] = true;
                skipD--;
            }else if(r>0 && ch=='R' && skipR==0){
                skipD++;
                d--;
            }else if(d>0 && ch=='D' && skipD==0){
                skipR++;
                r--;
            }
            
            
            
            i= (i+1)%senate.length();
            
            
            
            
        }
        
        // System.out.println(r + " " + d);
        
        return d<=0 ? "Radiant" : "Dire";
        
    }
}