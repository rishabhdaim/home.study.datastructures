package arrays;

import java.util.Date;

public class DemoArray {

	/**
	 * @param args
	 */
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		int[] primes = { 2, 3, 5, 7, 11, 13 };
		for (int i = 0; i < primes.length; i++)
			System.out.println(primes[i]);

		Date[] holidays = new Date[3];

		int thisYear = 2014;

		holidays[0] = new Date(thisYear + 1, 1, 1);
		holidays[1] = new Date(thisYear + 1, 5, 1);
		holidays[2] = new Date(thisYear + 1, 12, 25);

		ArrayHelper.printArray(holidays);
	}
}