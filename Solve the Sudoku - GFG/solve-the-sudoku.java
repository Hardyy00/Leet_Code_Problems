//{ Driver Code Starts
//Initial Template for Java

import java.util.*;
import java.io.*;
import java.lang.*;

class Driver_class
{
    public static void main(String args[])
    {
        
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        
        while(t-- > 0)
        {
            int grid[][] = new int[9][9];
            for(int i = 0; i < 9; i++)
            {
                for(int j = 0; j < 9; j++)
                grid[i][j] = sc.nextInt();
            }
            
            Solution ob = new Solution();
            
            if(ob.SolveSudoku(grid) == true)
                ob.printGrid(grid);
            else
                System.out.print("NO solution exists");
            System.out.println();
            
        }
    }
}




// } Driver Code Ends


//User function Template for Java

class Solution
{
    //Function to find a solved Sudoku. 
    static boolean SolveSudoku(int grid[][])
    {
        fillSudoku(0,0,grid);
        
        // printGrid(grid);
        return true;
    }
    
    private static boolean fillSudoku(int r, int c,int[][] grid){
        
        if(c==grid[0].length){
            c=0;
            r++;
        }
        
        if(r==grid.length){
            return true;
        }
        
        if(grid[r][c]!=0){
            if(fillSudoku(r,c+1,grid)) return true;
        }else{
            
            for(int i=1;i<=9;i++){
                
                if(isValid(i,r,c,grid)){
                    
                    grid[r][c] = i;
                    if(fillSudoku(r,c+1,grid)) return true;
                    grid[r][c]=0;
                }
            }
        }
        
        return false;
        
    }
    
    private static boolean isValid(int num,int r,int c,int[][] grid){
        
        int rowStart = 3*(r/3);
        int colStart = 3*(c/3);
        
        for(int i=0;i<9;i++){
            
            if(grid[r][i]==num) return false;
            
            if(grid[i][c] == num) return false;
            
            if(grid[rowStart + i/3][colStart + i%3]==num) return false;
        }
        
        return true;
    }
    
    //Function to print grids of the Sudoku.
    static void printGrid (int grid[][])
    {
       
       for(int i=0;i<9;i++){
           for(int j=0;j<9;j++){
               System.out.print(grid[i][j] + " ");
           }
       }
       
    }
}