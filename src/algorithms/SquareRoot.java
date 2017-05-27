package algorithms;

public class SquareRoot {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		double i = 16.4025;
		double j = squareRoot(i);
		System.out.println(j);
	}

	static double squareRoot(double i) {
		double j = i;
		double k = (1 + j) / 2;
		while (((k * k) - j) > 0.00000000001) {
			double l = j / k;
			k = (k + l) / 2;
		}
		return k;
	}

}
