package adt;

public class TestImmutableDate {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ImmutableDate date = new ImmutableDate(2014, 2, 17);

		System.out.println(date);

		date = date.advance(23);
		System.out.println(date);

		date = new ImmutableDate(500, 1, 12);
	}

}
