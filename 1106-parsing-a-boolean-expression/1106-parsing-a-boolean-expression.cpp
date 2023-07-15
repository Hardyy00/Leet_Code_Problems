class Solution {
int index = 0;  // index var, that helps to move across recursion
public:
    bool parseBoolExpr(string expression) {
        
        // Recursion
        // TC : O(N)
        // SC : O(N) (implicit stack space) || o(1) (explicit stack space)
        return solve( ' ', expression);
    }

    bool solve(char sign,string &s){
        // if sign for which you are calculating, is &, then take true, cuz even if you
        // get a true answer,if will get neglected if, ans is false
        // and if sign ! then take ans= false, cuz you also have to consider false answers 

        bool ans = sign == '&' || sign=='!' ? true : false; 
        // cout << "Called At : " << index << endl; 
        while(index<s.size()){

            char ch = s[index];

            // either true or false
            if(isalpha(ch)){
                
                // do stuff depending on the sign for which you are calculating
                if(sign=='&'){
                    ans = ans && (ch=='f' ? false : true);
                }else if(sign=='|'){
                    ans = ans || (ch=='f' ? false : true);
                }else{
                    // if calculating for !, then its gonna be a single value so just save it
                    ans = ch=='f' ? false : true;   
                    
                }

            }else if(ch=='&' || ch=='|'){

                index+=2;   // move index to the index across '('
                bool val = solve(ch,s);     // solve the () portion in a insolated recursion frame
                // do stuffs for which you are calculating
                if(sign=='&'){  
                    ans = ans && val;
                }else if(sign=='|'){
                    ans = ans || val;
                }else if(sign=='!' || sign==' '){
                   ans = val;   // for ! sign, it gonna be a single value so save it
                }

            }else if(ch=='!'){

                index+=2;
                bool val = !solve(ch, s);   // whatever the value come , just toggle it because the
                // sign for which you are gonna calculate is !, so whenever answer come  , toggle it

                if(sign=='&') ans = ans && val;

                else if(sign=='|') ans = ans || val;

                else ans = val;

            }else if(ch==')'){
                return ans;     // the () is finished so return the answer
            }

            index++;
        }

        return ans;
    }
};