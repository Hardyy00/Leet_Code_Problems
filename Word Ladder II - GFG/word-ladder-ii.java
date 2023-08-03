//{ Driver Code Starts
import java.util.*;
import java.lang.*;
import java.io.*;

class comp implements Comparator<ArrayList<String>> {
    // override the compare() method
    public int compare(ArrayList<String> a, ArrayList<String> b)
    {
        String x = "";
        String y = "";
        for(int i=0; i<a.size(); i++)
            x += a.get(i);
        for(int i=0; i<b.size(); i++)
            y += b.get(i);
        return x.compareTo(y);
    }
}

public class GFG
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        while(T-->0)
        {
            int n = Integer.parseInt(br.readLine().trim());
            String[] wordList = new String[n];
            for(int i = 0; i < n; i++){
                wordList[i] = br.readLine().trim();
            }
            String startWord, targetWord;
            startWord = br.readLine().trim();
            targetWord = br.readLine().trim();
            Solution obj = new Solution();
            ArrayList<ArrayList<String>> ans = obj.findSequences(startWord, targetWord, wordList);
            if(ans.size()==0)
                System.out.println(-1);
            else
            {
                Collections.sort(ans, new comp());
                for(int i=0; i<ans.size(); i++)
                {
                    for(int j=0; j<ans.get(i).size(); j++)
                    {
                        System.out.print(ans.get(i).get(j) + " ");
                    }
                    System.out.println();
                }
            }
        }
    }
}
// } Driver Code Ends


//User function Template for Java

class Solution
{
    public ArrayList<ArrayList<String>> findSequences(String startWord, String targetWord, String[] wordList)
    {
        
        Set<String> set = new HashSet<>();
        
        for(String s : wordList) set.add(s);
        
        
        Queue<ArrayList<String>> queue = new LinkedList<>();
        ArrayList<String> usedWords = new ArrayList<>();
        
        ArrayList<String> temp = new ArrayList<>();
        
        temp.add(startWord);
        
        queue.offer(temp);
        usedWords.add(startWord);
        
        int level = 0;
        
        ArrayList<ArrayList<String>> ans = new ArrayList<>();
        
        while(!queue.isEmpty()){
            
            ArrayList<String> words = queue.poll();
            
            if(words.size() > level){
                
                level++;
                
                while(!usedWords.isEmpty()){
                    set.remove(usedWords.remove(usedWords.size()-1));
                }
            }
            
            String word = words.get(words.size()-1);
            
            if(word.equals(targetWord)){
                
                if(ans.size()==0) ans.add(words);
                
                else if( words.size() == ans.get(0).size() ) ans.add(words);
            }
            
            char[] w = word.toCharArray();
            
            for(int i=0;i<word.length();i++){
                
                char save = w[i];
                
                for(char ch='a' ; ch<='z'; ch++){
                    
                    w[i] = ch;
                    
                    String st = new String(w);
                    
                    if(set.contains(st)){
                        
                        words.add(st);
                        
                        queue.offer( new ArrayList<>(words));
                        
                        words.remove(words.size()-1);
                        
                        usedWords.add(st);
                        
                    }
                }
                
                w[i]  = save;
            }
            
        }
        
        return ans;
    
    }
}