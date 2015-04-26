
import java.util.Scanner;

public class MagicSquares
	{
	   public static final int N = 3;

	   /**
	      Reads N integer numbers from the keyboard and arranges them
	      in a N x N array row-by-row
	      @return the N x N array (the square)
	   */
	   public static int[][] readSquare()
	   {
		return null;
	      //write this method; use the method nextInt
	   }

	   /**
	      Calculates the sum of the elements along the main diagonal
	      of the square
	      @param square - an NxN array of numbers
	      @return the sum of the elements along the main diagonal
	   */
	   public static int sumMainDiagonal(int[][] square)
	   {
		return 0;
	      //write this method
	   }

	   /**
	      Calculates the sum of the elements along the secondary diagonal
	      of the square
	      @param square - the NxN array of numbers
	      @return the sum of the elements along the secondary diagonal
	   */
	   public static int sumSecondaryDiagonal(int[][] square)
	   {
		return 0;
	      //write this method
	   }

	   /**
	      Calculates the sum of the given column in the square
	      @param square - an N x N array
	      @param columnNumber - the column to sum
	      @return sum of the elements in column columnNumber
	   */
	   public static int sumColumn(int[][] square, int columnNumber)
	   {
		return columnNumber;
	      //write this method
	   }

	   /**
	      Calculates the sum of the given row in the square
	      @param square - an NxN array of numbers
	      @param rowNumber - the row to sum
	      @return sum of the elements in row rowNumber
	   */
	   public static int sumRow(int[][] square, int rowNumber)
	   {
		return rowNumber;
	      //write this method
	   }
	   
	   /**
			Checks if the square is a magic square by calling the methods correctNumbers
			sumColumn, sumRow, sumMainDiagonal and sumSecondaryDiagonal
			@param square - an NxN square of numbers
			@return true if square is a magic square and false otherwise
	   */
	   public static boolean validMagicSquare(int[][] square)
	   {
		return false;
			//write this method
	   }

	   //given
	   /**
	      Checks if the square contains the numbers from 1 to N^2
	      @param square - an NxN square of numbers
	      @return true if square contains the numbers, otherwise return false
	   */
	   public static boolean correctNumbers(int[][] square)
	   {
	      boolean[] seenNumber = new boolean[N * N];
	      
	      for (int i = 0; i < N; i++)
	      {
	         for (int j = 0; j < N; j++)
	         {
	            int number = square[i][j];
	            if (number < 1 || number > (N * N))
	            {
	               return false;
	            }
	            else if (seenNumber[number - 1])
	            {
	               return false;
	            }
	            else
	            {	               
	               seenNumber[number - 1] = true;
	            }
	         }
	      }
	      return true;
	   }

	   //given
	   public static void main(String[] args)
	   {
	      System.out.println("Please enter " + (N * N) + " numbers: ");
	      int[][] square = readSquare();
	      if (validMagicSquare(square))
	      {
	         System.out.println("It's a magic square!");
	      }
	      else
	      {
	         System.out.println("Not a magic square!");
	      }
	   }
}

