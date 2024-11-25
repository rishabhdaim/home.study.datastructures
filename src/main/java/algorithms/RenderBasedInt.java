package algorithms;

public class RenderBasedInt {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int i = 10, r = 45;
		System.err.println(renderBasedInt(i, r));
	}

	static String renderBasedInt(int i, int r) {
		// Render i to the base r (where r is an integer between 2 and 10).
		String s;
		if (i < 0) {
			s = '-' + renderBasedInt(-i, r);
		} else if (i < r) {
			char d = (char) ('0' + i);
			s = " " + d;
		} else {
			char d = (char) ('0' + i % r);
			s = renderBasedInt(i / r, r) + d;
		}
		return s;
	}
}
