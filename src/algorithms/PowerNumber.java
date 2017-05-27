package algorithms;

public class PowerNumber {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		long i = 5, j = 7;
		long k = power(i, j);
		System.out.println(k);
		k = powerRecursive(i, j);
		System.out.println(k);

	}

	static long power(long i, long j) {
		long k = 1, l = i, m = j;
		while (m > 0) {
			if (m % 2 != 0)
				k *= l;
			m = m / 2;
			l *= l;
		}
		return k;
	}

	static long powerRecursive(long b, long n) {
		// Return the value of b raised to the n'th power
		// (where n is a nonnegative integer).
		/*
		 * if (n == 0) return 1; else return b * power(b, n - 1);
		 */

		// Return the value of b raised to the n'th power (where n is a
		// nonnegative
		// integer).
		if (n == 0)
			return 1;
		else {
			long p = powerRecursive(b, n / 2);
			if (n % 2 == 0) // n is even
				return p * p;
			else
				// n is odd
				return p * p * b;
		}
	}
}
