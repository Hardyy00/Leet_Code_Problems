//{ Driver Code Starts
// Initial Template for Java

import java.util.*;
class Rat {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-- > 0) {
            int n = sc.nextInt();
            int[][] a = new int[n][n];
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++) a[i][j] = sc.nextInt();

            Solution obj = new Solution();
            ArrayList<String> res = obj.findPath(a, n);
            Collections.sort(res);
            if (res.size() > 0) {
                for (int i = 0; i < res.size(); i++)
                    System.out.print(res.get(i) + " ");
                System.out.println();
            } else {
                System.out.println(-1);
            }
        }
    }
}

// } Driver Code Ends


// User function Template for Java

// m is the given matrix and n is the order of matrix
class Solution {
    public static ArrayList<String> findPath(int[][] m, int n) {
        
        ArrayList<String> ans = new ArrayList<>();
        StringBuilder path = new StringBuilder();
        boolean[][] visit = new boolean[n][n];
        
        if(m[0][0]==1)
            findPath(0,0,path,m,ans,visit);
        
        return ans;
    }
    
    private static void findPath(int r, int c, StringBuilder path,int[][] mat,ArrayList<String> ans,boolean[][] visit) {
        
        if(r==mat.length-1 && c==mat.length-1){
            
            ans.add(path.toString());
            return;
        }
        
        visit[r][c] = true;
        
        if(r>0 && !visit[r-1][c] && mat[r-1][c]==1){
            
            path.append("U");
            findPath(r-1,c,path,mat,ans,visit);
            path.deleteCharAt(path.length()-1);
            
        }
        
        if(c<mat.length-1 && !visit[r][c+1] && mat[r][c+1]==1){
            
            path.append("R");
            findPath(r,c+1,path,mat,ans,visit);
            path.deleteCharAt(path.length()-1);
            
        }
        
        if(r<mat.length-1 && !visit[r+1][c] && mat[r+1][c]==1){
            
            path.append("D");
            findPath(r+1,c,path,mat,ans,visit);
            path.deleteCharAt(path.length()-1);
            
        }
        
        if(c>0 && !visit[r][c-1] && mat[r][c-1]==1){
            
            path.append("L");
            findPath(r,c-1,path,mat,ans,visit);
            path.deleteCharAt(path.length()-1);
            
        }
        
        visit[r][c] = false;
    }
}