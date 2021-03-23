/**
 * Matthew Radermacher, 4/7/2017
 *
 * NQueens Problem using Bitwise Operations (also mirroring solutions to cut time in half)
 * 
 * Standard program using array is too slow O(n^n), n = 14 = time of about 10.43 seconds
 * Bitwise program drastically faster and more memory efficient O(n!), n = 14 = time of about 35 milliseconds
 * 
 * I have tested up to n = 19 = time of about 43 minutes and 22 seconds
 * 
 * great writeup on nqueens with bitwise operations
 * http://andrewjonesblog.azurewebsites.net/solving-nqueens-using-bitwise-operators/
 */

import java.util.Scanner;

public class NQueens {
	
	private static long count = 0;
	private static int all;
	
	public static long solve(int n) {
		if (n == 1) return 1;
		int mid = n / 2;
		all = (1 << n) - 1;
		check_row(mid - 1);
		count *= 2;
		if (n % 2 != 0) {
			int c = 1 << mid;
			check_col(c, c << 1, c >>> 1);
		}
		return count;
	}
	
	private static void check_row(int r) {
		if (r < 0) return;
		int c = 1 << r;
		check_col(c, c << 1, c >>> 1);
		check_row(r - 1);
	}
	
	private static void check_col(int cRow, int cLeft, int cRight) {
        int yMask = all & ~(cRow | cLeft | cRight);
        check_col(cRow, cLeft, cRight, yMask);
	}
	
	private static void check_col(int cRow, int cLeft, int cRight, int yMask) {
		if (yMask > 0) {
			int pos = -yMask & yMask;
			int nextCRow = cRow | pos;
			yMask ^= pos;
			if (nextCRow < all) check_col(nextCRow, (cLeft | pos) << 1, (cRight | pos) >>> 1);
			else count++;
			check_col(cRow, cLeft, cRight, yMask);
		}
	}
	
	public static void main(String [] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter valid number of queens: ");
		int n = input.nextInt();
		long answer = 0;
		if (n > 0) answer = solve(n);
		System.out.println("The number of valid arrangements is " + answer);
	}
}
