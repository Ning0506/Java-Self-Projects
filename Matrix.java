/***************************************************

  Name: Ning Yang
  Date: 03/27/2020
  Homework #12
  
  Program name:        Matrix
  Program description: Prompt the user to enter number of rows and columns.
                       Generate a matrix and print it.
  
****************************************************/

import  java.util.Scanner;

public class Matrix {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		int i = 0;

        while (i == 0) {

        	System.out.print("Please enter the number of rows: ");
			int row = input.nextInt();

			System.out.print("Please enter the number of columns: ");
			int col = input.nextInt();

			System.out.println();

			if (row > 5 || row < 0) {

				if (col > 6 || col < 0){
					System.out.print("Sorry, that is an invalid input, please try again.");
				    continue;
				}
			}

			int[][] array = new int[row][col];

			for (int r = 0; r < array.length; r++) {
				for (int c = 0; c < array[r].length; c++){
					array[r][c] = 10 * r + c;
				}
			}

			printMatrix(array);
			System.out.println();


			String answ = "";

			while (!answ.equals("Y")) {

				System.out.println("P Print matrix" + "\t- Print the contents of the matrix");
				System.out.println("R Reverse rows" + "\t- Reverse all elements in every row of the matrix");
				System.out.println("S columnSum" + "\t- Calculate the sum of the values in each column");
				System.out.println("T transpose" + "\t- Rows become columns (and vice versa)");
				System.out.println("Q quit" + "\t\t- Exit the program");
					
				System.out.print("\nWhat would you like to do? Enter P, R, S, T, or Q to quit: ");
				answ = input.next().toUpperCase();

				System.out.println();

				if (answ.equals("P")){
					printMatrix(array);
				    System.out.println();
				}
				else if (answ.equals("R")){
					reverseRows(array);
				    System.out.println();
				}
				else if (answ.equals("S")){
	                columnSum(array);
	                System.out.println();
				}
	            else if (answ.equals("T")){
	                transpose(array);
	                System.out.println();
	            }
	            else if (answ.equals("Q")){
	            	i = 1;
	                break;
	            }
			}
		}
	}


	public static void printMatrix(int[][] matrix) {
		for (int r = 0; r < matrix.length; r++) {
			for (int c = 0; c < matrix[r].length; c++){
			    System.out.printf("%3d", matrix[r][c]);
			    System.out.print("\t");
			}
		    System.out.println();
		}
	}

	public static int[][] reverseRows(int[][] matrix) {
		int[][] matrixNew = new int[matrix.length][matrix[0].length];

		for (int r = 0; r < matrix.length; r++) {
			for (int c = 0; c < matrix[r].length; c++){
			    matrixNew[r][matrix[r].length-1-c] = matrix[r][c];
			}
		}
		printMatrix(matrixNew);
		return matrixNew;
	}

	public static int[][] columnSum(int[][] matrix) {
	    int[][] matrixCo = new int[1][matrix[0].length];

		for (int c = 0; c < matrix[0].length; c++) {
			int total = 0;
			for (int r = 0; r < matrix.length; r++){
				total += matrix[r][c];
			    matrixCo[0][c] = total;
			}
		}
		printMatrix(matrixCo);
		return matrixCo;
	}

	public static int[][] transpose(int[][] matrix) {
		int[][] matrixTr = new int[matrix[0].length][matrix.length];

		for (int r = 0; r < matrix.length; r++) {
			for (int c = 0; c < matrix[r].length; c++){
				matrixTr[c][r] = matrix[r][c];
			}
		}
		printMatrix(matrixTr);
		return matrixTr;
	}
}