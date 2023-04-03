// CS 3310.01 Spring 2023
// Janista Gitbumrungsin
// Project 1 Matrix Multiplication

import java.util.Scanner;

public class Multiply
{
    public static void main(String[] args) 
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter size of matrices: ");
        int size = scan.nextInt();

        int[][] matrix1 = new int[size][size];
        int[][] matrix2 = new int[size][size];

        matrix1 = fillMatrix(matrix1, scan);
        matrix2 = fillMatrix(matrix2, scan);
    }

    public static int[][] generateMatrix(int[][] matrix)
    {
        for(int i = 0; i < matrix.length; i++)
        {
            for(int j = 0; j < matrix.length; j++)
            {
                matrix[i][j] = (int)(Math.random() * (100) - 50);
            }
        }
        return matrix;
    }

    public static int[][] fillMatrix(int[][] matrix, Scanner scan)
    {
        for(int i = 0; i < matrix.length; i++)
        {
            for(int j = 0; j < matrix.length; j++)
            {
                System.out.printf("Enter value of [%d][%d]: ", i, j);
                matrix[i][j] = scan.nextInt();
            }
        }

        return matrix;
    }

    public static void printMatrix(int[][] matrix)
    {
        for(int i = 0; i < matrix.length; i++)
        {
            for(int j = 0; j < matrix.length; j++)
            {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
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