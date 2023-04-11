// CS 3310.01 Spring 2023
// Janista Gitbumrungsin
// Project 1 Matrix Multiplication

import java.util.Scanner;

/**
 * Executes three different algorithms for matrix multiplication and prints execution time of each algorithm in milliseconds
 * Values of each matrix can be printed by uncommenting the lines
 * @author Janista Gitbumrungsin
 */
public class Multiply
{
    private static long classicStart;
    private static long classicEnd;
    private static long dncStart;
    private static long dncEnd;
    private static long strassenStart;
    private static long strassenEnd;

    public static void main(String[] args) 
    {
        System.out.println("\n----------- MATRIX MULTIPLIER ----------");
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter size of matrices: ");
        int size = scan.nextInt();

        int[][] matrix1 = new int[size][size];
        int[][] matrix2 = new int[size][size];

        int option = 0;

        while(option != 1 && option != 2) //Loop until user chooses manual vs random generation
        {
            System.out.println("Would you like to manually fill in the matrices or randomly generate them?\nEnter 1 for manual, 2 for random: ");
            option = scan.nextInt();
        }

        if(option == 1)
        {
            System.out.println("\nEnter values for Matrix 1:");
            matrix1 = fillMatrix(matrix1, scan);
            System.out.println("\nEnter values for Matrix 2:");
            matrix2 = fillMatrix(matrix2, scan);
        }
        else if(option == 2)
        {
            matrix1 = generateMatrix(matrix1);
            matrix2 = generateMatrix(matrix2);
        }


        System.out.println("\nMatrix 1:");
        //printMatrix(matrix1); //Uncomment to print values of matrix
        System.out.println("Matrix 2:");
        //printMatrix(matrix2); //Uncomment to print values of matrix

        classicStart = System.nanoTime();
        int[][] result1 = classical(matrix1, matrix2, size);
        classicEnd = System.nanoTime();

        dncStart = System.nanoTime();
        int[][] result2 = divideAndConquer(matrix1, matrix2, size);
        dncEnd = System.nanoTime();

        strassenStart = System.nanoTime();
        int[][] result3 = strassen(matrix1, matrix2, size);
        strassenEnd = System.nanoTime();
        
        System.out.println("\nResults:");
        System.out.println("Classical:");
        //printMatrix(result1); //Uncomment to print values of matrix
        System.out.println("Total time: " + (double)((classicEnd - classicStart)/1000000.0) + " ms");

        System.out.println();
        System.out.println("Divide and Conquer:");
        //printMatrix(result2); //Uncomment to print values of matrix
        System.out.println("Total time: " + (double)((dncEnd - dncStart)/1000000.0) + " ms");

        System.out.println();
        System.out.println("Strassen's:");
        //printMatrix(result3); //Uncomment to print values of matrix
        System.out.println("Total time: " + (double)((strassenEnd - strassenStart)/1000000.0) + " ms");

        System.out.println();
    }

    /**
     * Generates random values to input into a matrix, ranging from integers of -50 to 50
     * @param matrix A 2d array representing a matrix
     * @return A 2d array of a matrix filled with random integers
     */
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

    /**
     * Prompts user to individually enter an integer value for every value of a matrix and assigns them to the matrix
     * @param matrix A 2d array representing a matrix
     * @param scan A Scanner to take user input
     * @return A 2d array of a matrix filled with the user's input
     */
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

    /**
     * Prints out every value of a matrix in the format of the matrix
     * @param matrix A 2d array representing a matrix
     */
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

    /**
     * Performs classic matrix multiplcation on two matrices
     * @param A A 2d array representing the first matrix to be multiplied
     * @param B A 2d array representing the second matrix to be multiplied
     * @param n An integer representing the size of the matrices which are of size n x n
     * @return A 2d array representing the matrix that results from the multiplcation
     */
    public static int[][] classical(int[][] A, int[][] B, int n)
    {
        int[][] C = new int[n][n];

        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < n; j++)
            {
                C[i][j] = 0;

                for(int k = 0; k < n; k++)
                {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }

        return C;
    }

    /**
     * Performs divide and conquer matrix multiplcation on two matrices
     * @param A A 2d array representing the first matrix to be multiplied
     * @param B A 2d array representing the second matrix to be multiplied
     * @param n An integer representing the size of the matrices which are of size n x n
     * @return A 2d array representing the matrix that results from the multiplcation
     */
    public static int[][] divideAndConquer(int[][] A, int[][] B, int n)
    {
        int[][] C = new int[n][n];

        if(n <= 1)
        {
            C[0][0] = A[0][0] * B[0][0];

            return C;
        }
        else
        {
            int size = n/2;

            int[][][] splitA = divide(A, size);
            int[][][] splitB = divide(B, size);

            int[][] A11 = splitA[0];
            int[][] A12 = splitA[1];
            int[][] A21 = splitA[2];
            int[][] A22 = splitA[3];
            int[][] B11 = splitB[0];
            int[][] B12 = splitB[1];
            int[][] B21 = splitB[2];
            int[][] B22 = splitB[3];

            int[][] X1 = divideAndConquer(A11, B11, size);
            int[][] X2 = divideAndConquer(A12, B21, size);
            int[][] X3 = divideAndConquer(A11, B12, size);
            int[][] X4 = divideAndConquer(A12, B22, size);
            int[][] X5 = divideAndConquer(A21, B11, size);
            int[][] X6 = divideAndConquer(A22, B21, size);
            int[][] X7 = divideAndConquer(A21, B12, size);
            int[][] X8 = divideAndConquer(A22, B22, size);

            int[][] C11 = add(X1, X2);
            int[][] C12 = add(X3, X4);
            int[][] C21 = add(X5, X6);
            int[][] C22 = add(X7, X8);

            for(int i = 0; i < size; i++)
            {
                for(int j = 0; j < size; j++)
                {
                    C[i][j] = C11[i][j];
                    C[i][j + size] = C12[i][j];
                    C[i + size][j] = C21[i][j];
                    C[i + size][j + size] = C22[i][j];
                }
            }

            return C;
        }
    }

    /**
     * Performs Strassen's algorithm for matrix multiplcation on two matrices
     * @param A A 2d array representing the first matrix to be multiplied
     * @param B A 2d array representing the second matrix to be multiplied
     * @param n An integer representing the size of the matrices which are of size n x n
     * @return A 2d array representing the matrix that results from the multiplcation
     */
    public static int[][] strassen(int[][] A, int[][] B, int n)
    {
        int[][] C = new int[n][n];

        if(n <= 1)
        {
            C[0][0] = A[0][0] * B[0][0];

            return C;
        }
        else
        {
            int size = n/2;

            int[][][] splitA = divide(A, size);
            int[][][] splitB = divide(B, size);

            int[][] A11 = splitA[0];
            int[][] A12 = splitA[1];
            int[][] A21 = splitA[2];
            int[][] A22 = splitA[3];
            int[][] B11 = splitB[0];
            int[][] B12 = splitB[1];
            int[][] B21 = splitB[2];
            int[][] B22 = splitB[3];

            int[][] P = strassen(add(A11, A22), add(B11, B22), size);
            int[][] Q = strassen(add(A21, A22), B11, size);
            int[][] R = strassen(A11, subtract(B12, B22), size);
            int[][] S = strassen(A22, subtract(B21, B11), size);
            int[][] T = strassen(add(A11, A12), B22, size);
            int[][] U = strassen(subtract(A21, A11), add(B11, B12), size);
            int[][] V = strassen(subtract(A12, A22), add(B21, B22), size);

            int[][] C11 = add(subtract(S, T), add(P, V));
            int[][] C12 = add(R, T);
            int[][] C21 = add(Q, S);
            int[][] C22 = add(subtract(R, Q), add(P, U));

            for(int i = 0; i < size; i++)
            {
                for(int j = 0; j < size; j++)
                {
                    C[i][j] = C11[i][j];
                    C[i][j + size] = C12[i][j];
                    C[i + size][j] = C21[i][j];
                    C[i + size][j + size] = C22[i][j];
                }
            }

            return C;
        }
    }

    /**
     * Splits a matrix into four segments according to a specified segment size
     * @param matrix A 2d array representing a matrix
     * @param size An integer representing the size of the new submatrices
     * @return A 3d array to store 2d arrays that each represent the resulting submatrices
     */
    public static int[][][] divide(int[][] matrix, int size)
    {
        int[][] mat11 = new int[size][size];
        int[][] mat12 = new int[size][size];
        int[][] mat21 = new int[size][size];
        int[][] mat22 = new int[size][size];

        for(int i = 0; i < size; i++)
        {
            for(int j = 0; j < size; j++)
            {
                mat11[i][j] = matrix[i][j];
                mat12[i][j] = matrix[i][j + size];
                mat21[i][j] = matrix[i + size][j];
                mat22[i][j] = matrix[i + size][j + size];
            }
        }

        int[][][] results =  {mat11, mat12, mat21, mat22};
        return results;
    }

    /**
     * Adds together the values of two matrices
     * @param A A 2d array representing the first matrix to be added
     * @param B A 2d array representing the second matrix to be added
     * @return A 2d array representing the result of the addition
     */
    public static int[][] add(int[][] A, int[][] B)
    {
        int[][] result = new int[A.length][A.length];

        for(int i = 0; i < A.length; i++)
        {
            for(int j = 0; j < A.length; j++)
            {
                result[i][j] = A[i][j] + B[i][j];
            }
        }

        return result;
    }

    /**
     * Subtracts the values of two matrices
     * @param A A 2d array representing the first matrix to be subtracted from
     * @param B A 2d array representing the second matrix to be subtracted
     * @return A 2d array representing the result of the subtraction
     */
    public static int[][] subtract(int[][] A, int[][] B)
    {
        int[][] result = new int[A.length][A.length];

        for(int i = 0; i < A.length; i++)
        {
            for(int j = 0; j < A.length; j++)
            {
                result[i][j] = A[i][j] - B[i][j];
            }
        }

        return result;
    }
}