class Solution {
    public String simplifyPath(String path) {

        // String[] st = path.split("/");
        // // System.out.println(Arrays.toString(st));

        // Deque<String> stack = new ArrayDeque<>();

        // for(int i=0;i<st.length;i++){

        //     if(!stack.isEmpty() && st[i].equals("..")) stack.pop();
        //     else if(!st[i].equals(".") && !st[i].equals("..") && !st[i].equals("")) stack.push(st[i]);
        // }

        // // System.out.println(stack);

        // if(stack.isEmpty()) return "/";

        // StringBuilder ans = new StringBuilder();

        // while(!stack.isEmpty()){
        //     ans.insert(0,stack.pop()).insert(0,"/");
        // }

        // return ans.toString();
        


        // add '/' in last so that /. or /.. is executed
        if(path.charAt(path.length()-1)=='.') path+="/";

        Deque<Character> stack = new ArrayDeque<>();
        int count = 0;
        // determine if the .. is afterslash or is after a name (har..)
        boolean afterSlash = false;

        for(int i=0;i<path.length();i++){

            char ch = path.charAt(i);

            if(ch=='.'){ 
                if(path.charAt(i-1)=='/') afterSlash = true;
                count++;
                stack.push(ch);
            }else{
                
                // if its like this /. ,then remove . 
                if(afterSlash && count==1 && ch=='/'){ 
                    stack.pop();
                }else if(afterSlash && count==2 && ch=='/'){
                    int slash = 0;

                    // if its like this /hardik/.. then remove hardik/..
                    while(!stack.isEmpty()){

                        if(stack.peek()=='/')
                            slash++;

                        if(slash==2) break;

                        stack.pop();
                    }

                }

                count = 0;

                // if stack.is empty then push anyway or if current element is / but previous element is also /
                // then disregard it
                if((stack.isEmpty() && ch=='/' )|| (ch=='/' && stack.peek()!='/') ) stack.push(ch);
                // other wise push an character
                else if(ch!='/') stack.push(ch);

                // since the . was not started with / so setting it to false
                afterSlash = false;
            }

            
        }   

          // removeing the last '/' 
         if(!stack.isEmpty() && stack.peek()=='/') stack.pop();

        // if the stack is empty then we are in root directory
         if(stack.isEmpty()) return "/";

         char[] arr = new char[stack.size()];
         int i = stack.size()-1;

         // last element occurs first so inserting to the end of the string
         while(!stack.isEmpty()){
            arr[i--] = stack.pop();
         }

        return new String(arr);

    }


}