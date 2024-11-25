package algorithms;

public class QuadraticSolution {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		double a = 2, b = 2, c = 4;
		double d = 0;
		double e = 0;
		b = b / a;
		c = c / a;
		e = b * b / 4;
		d = e + c;
		d = SquareRoot.squareRoot(d);
		e = d - (b / 2);
		System.err.println(e);
	}

}
