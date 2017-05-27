package adt;

public class TestStringADT {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		char[] cs = { 'a', 'b', 'c', 'f', 'r', 'r', 'h', 'j', 'k', 'u', 'o',
				'p' };

		StringADT adt = new StringADT(cs);

		System.out.println(adt);

		System.out.println(adt.charAt(11));

		adt = adt.concat(adt);
		System.out.println(adt);

		adt = adt.subString(2, 5);
		System.out.println(adt);
	}

}
