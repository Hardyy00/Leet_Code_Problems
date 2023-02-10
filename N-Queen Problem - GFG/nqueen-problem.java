//{ Driver Code Starts
// Initial Template for Java

import java.io.*;
import java.util.*;
import java.lang.*;

class GFG{
    public static void main(String args[])throws IOException
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            
            Solution ob = new Solution();
            ArrayList<ArrayList<Integer>> ans = ob.nQueen(n);
            if(ans.size() == 0)
                System.out.println("-1");
            else {
                for(int i = 0;i < ans.size();i++){
                    System.out.print("[");
                    for(int j = 0;j < ans.get(i).size();j++)
                        System.out.print(ans.get(i).get(j) + " ");
                    System.out.print("] ");
                }
                System.out.println();
            }
        }
    }
}
// } Driver Code Ends


// User function Template for Java

class Solution{
    static ArrayList<ArrayList<Integer>> nQueen(int n) {
        
        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        
        int[] rowCheck = new int[n];
        int[] upperDiagonalCheck = new int[2*n-1];
        int[] lowerDiagonalCheck  = new int[2*n-1];
        
        placeQueens(0,n,rowCheck,upperDiagonalCheck,lowerDiagonalCheck,list,ans);
        
        return ans;
    }
    
    private static void placeQueens(int col,int n,int[] rowCheck,int[] upperDiagonalCheck,int[] lowerDiagonalCheck,ArrayList<Integer> list,ArrayList<ArrayList<Integer>> ans){
        
        if(col==n){
            ans.add(new ArrayList<>(list));
        }
        
        for(int row=0;row<n;row++){
            
            if(rowCheck[row]==1 || upperDiagonalCheck[n-1+col-row]==1 || lowerDiagonalCheck[row+col]==1) continue;
            
            rowCheck[row]++;
            upperDiagonalCheck[n-1+col-row]++;
            lowerDiagonalCheck[row+col]++;
            list.add(row+1);
            placeQueens(col+1,n,rowCheck,upperDiagonalCheck,lowerDiagonalCheck,list,ans);
            rowCheck[row]--;
            upperDiagonalCheck[n-1+col-row]--;
            lowerDiagonalCheck[row+col]--;
            list.remove(list.size()-1);
            
        }
    }
}