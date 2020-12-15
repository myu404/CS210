/*
 * Michael Yu, CS210
 * Bellevue College, Fall 2020
 * October 26, 2020
 * Week 8, Coding Assignment 7: Sum big integers from file
 * Java Program: SumBigInts.java
 */

import java.io.*; // Import Java library for file input/output
import java.util.*; // Import Java library for Scanner object (console, text file, etc.)

public class SumBigInts {
	
	public static final int MAX_DIGITS = 25;

	public static void main(String[] args) throws FileNotFoundException {
		// Create file and Scanner object
		File f = new File("sum.txt");
		Scanner input = new Scanner(f);
		
		// Process data file to perform big integer addition
		processFile(input);
	}
	
	// processFile() method uses Scanner object to read text file
	public static void processFile(Scanner input) {
		int numLines = 0;
		
		// Each line read is then passed to another Scanner object that reads the tokens of the line
		while (input.hasNextLine()) {
			numLines++;
			
			// Store each line String to a String variable
			String data = input.nextLine();
			Scanner dataToken = new Scanner(data);
			
			// Count the number of tokens in a line
			int numTokens = 0;
			while (dataToken.hasNext()) {
				numTokens++;
				dataToken.next();
			}
			
			// 2D array is created for each line, where each row is a token and each column is a digit
			// Process each token and write each digit to an array element
			dataToken = new Scanner(data);
			int[][] lineIntArr = lineTo2DArray(dataToken, numTokens);
			
			// Add numbers belonging to the line
			lineIntArr = addBigInt(lineIntArr);
			
			// Print results
			printBigInt(lineIntArr);
		}
		
		System.out.println("\nTotal lines = " + numLines);
	}
	
	
	// lineTo2DArray() method uses the line read from Scanner object and convert it to 2D array
	private static int[][] lineTo2DArray(Scanner data, int numTokens) {
		
		// Number of rows in 2D array = number of tokens + row for big integer sum
		// Number of columns in 2D array = maximum allowed number of digits
		// 2D array is auto-initialized to 2D array of 0s
		int[][] numArr = new int[numTokens+1][MAX_DIGITS];
		int row = 0;
		
		// Convert each token into an integer array
		// Token digit is read from right to left, where the right-most digit is placed in the right-most slot in the array
		while (data.hasNext()) {
			String numText = data.next();
			if (numText.length() > MAX_DIGITS) {
				throw new ArrayIndexOutOfBoundsException("Integer token data (" + numText + ") exceeds " + MAX_DIGITS + " digits");
			}
			int col = MAX_DIGITS - 1;
			
			// Loop through the length of token to assign array to numeric digit
			for (int i = numText.length() - 1; i >= 0; i--) {
				numArr[row][col] = Character.getNumericValue(numText.charAt(i));
				col--;
			}
			row++;
		}
		
		return numArr;
	}
	
	// addBigInt() method performs big integer addition via digit addition from right to left
	// Account for carrying over numbers to the next digit location
	private static int[][] addBigInt(int[][] bigIntArray) {
		
		int sumCarryOver = 0;
		
		// Outer loop tracks the 2D array column to ensure all rows of digit are added properly
		// before continuing to the next column to the left
		for (int col = bigIntArray[0].length - 1; col >= 0; col--) {
			int digitSum = 0;
			
			// Inner loop tracks the 2D array row to add all digits in column
			for (int row = 0; row < bigIntArray.length; row++) {
				digitSum += bigIntArray[row][col];
			}
			
			// Add any carry over value to the digit sum
			digitSum += sumCarryOver;
			
			// Determine the amount of carry over to the next digit via integer division
			sumCarryOver = digitSum/10;
			
			// Write digit to the sum array, which is located in the last row of the 2D array
			bigIntArray[bigIntArray.length - 1][col] = digitSum % 10;
		}
		
		// Error handling if final integer sum exceeds maximum allowed number of digits
		if (sumCarryOver != 0) {
			throw new ArrayIndexOutOfBoundsException("Big integer sum exceeds " + MAX_DIGITS + " digits");
		}
		return bigIntArray;
	}
	
	// printBigInt() method prints the sum of numbers from text data line
	private static void printBigInt(int[][] bigIntArray) {
		
		for (int row = 0; row < bigIntArray.length; row++) {
			
			// Determine the index of the first digit after leading zeros for each data value (stored in array form)
			int leadZeroCol = 0;
			while (leadZeroCol < bigIntArray[row].length) {
				if (bigIntArray[row][leadZeroCol] != 0 || leadZeroCol == bigIntArray[row].length - 1) {
					break;
				}
				leadZeroCol++;
			}
			
			// Address fencepost problem with plus sign ("+") and equal sign ("=")
			if (row > 0 && row < bigIntArray.length - 1) {
				System.out.print(" + ");
			} else if (row == bigIntArray.length - 1){
				System.out.print(" = ");
			}
			
			for (int col = leadZeroCol; col < bigIntArray[row].length; col++) {
				System.out.print(bigIntArray[row][col]);
			}
		}
		System.out.println();
	}
	
	// addBigIntVoid() method is an alternative to addBigInt() method
	// This method utilizes the fact that an array is a reference type and do not return a value
	// Passing an array as a parameter into a method copies the reference address to the array
	// Changes to the values of the array affect all reference variables to the array
	// This behavior is the same as pointers in C and C++
	private static void addBigIntVoid(int[][] bigIntArray) {
		
		int sumCarryOver = 0;
		
		
		for (int col = bigIntArray[0].length - 1; col >= 0; col--) {
			int digitSum = 0;
			for (int row = 0; row < bigIntArray.length; row++) {
				digitSum += bigIntArray[row][col];
			}
			
			digitSum += sumCarryOver;
			sumCarryOver = digitSum/10;
			bigIntArray[bigIntArray.length - 1][col] = digitSum % 10;
		}
		
		if (sumCarryOver != 0) {
			throw new ArrayIndexOutOfBoundsException("Big integer sum exceeds " + MAX_DIGITS + " digits");
		}
	}
}
