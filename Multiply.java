// CS 3310.01 Spring 2023
// Janista Gitbumrungsin
// Project 1 Matrix Multiplication

public class Multiply
{
    public static void main(String[] args) 
    {
        
    }

    public static int[][] generateMatrix(int[][] matrix)
    {
        return matrix;
    }

    public static int[][] classical(int[][] A, int[][] B, int n)
    {
        int[][] C = new int[n][n];

        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < n; j++)
            {
                for(int k = 0; k < n; k++)
                {
                    C[i][j] = A[i][k] * B[k][j];
                }
            }
        }

        return C;
    }

    public static int[][] divideAndConquer(int[][] A, int[][] B, int n)
    {
        int[][] C = new int[n][n];
        return C;
    }

    public static int[][] strassen(int[][] A, int[][] B, int n)
    {
        int[][] C = new int[n][n];
        return C;
    }
}