package algorithms;

public class GCD {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int a = 10;
		int b = 54;
		int i = gcd(a, b);
		System.out.println(i);
	}

	private static int gcd(int a, int b) {
		int i = a;
		int j = b;

		while ((i % j) != 0) {
			int k = i % j;
			i = j;
			j = k;
		}
		return j;
	}

}
