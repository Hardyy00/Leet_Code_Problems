//{ Driver Code Starts
//Initial Template for Java

import java.io.*;
import java.util.*;

class GFG
{
    public static void main(String args[])throws IOException
    {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(read.readLine());
        
        while(t-- > 0)
        {
            String str[] = read.readLine().trim().split("\\s+");
            int r = Integer.parseInt(str[0]);
            int c = Integer.parseInt(str[1]);
            int matrix[][] = new int[r][c];
            
            for(int i = 0; i < r; i++)
            {
                int k = 0;
                str = read.readLine().trim().split("\\s+");
                for(int j = 0; j < c; j++){
                  matrix[i][j] = Integer.parseInt(str[k]);
                  k++;
                }
            }
            new Solution().booleanMatrix(matrix);
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < r; i++){
                for(int j = 0; j < c; j++){
                    sb.append(matrix[i][j] + " ");
                }
                sb.append("\n"); 
            }
            System.out.print(sb);
        }
    }
}

// } Driver Code Ends


//User function Template for Java

class Solution
{
    
    void booleanMatrix(int mat[][])
    {
       ArrayList<Integer> xCor = new ArrayList<>();
       ArrayList<Integer> yCor = new ArrayList<>();
       
       for(int i=0;i<mat.length;i++){
           for(int j=0;j<mat[0].length;j++){
               
               if(mat[i][j]==1){
                   xCor.add(i);
                   yCor.add(j);
               }
           }
       }
       
       transformMatrix(mat,xCor,yCor);
       
    }
    
    void transformMatrix(int[][] mat,ArrayList<Integer> xCor , ArrayList<Integer> yCor){
        
        for(int i=0;i<xCor.size();i++){
            int row = xCor.get(i);
            int col = yCor.get(i);
            
            for(int j=0;j<mat[0].length;j++){
                mat[row][j] = 1;
            }
            
            for(int j=0;j<mat.length;j++){
                mat[j][col] = 1;
            }
            
        }
    }
}