package adt;

public class TestPerson {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Person p1 = new Person("rishabh", "kumar", 1991, false);
		Person p2 = new Person("rishabh", "daim", 1991, false);

		System.out.println(p1.equals(p2));

		System.out.println(p1.compareTo(p2));

		p1 = p1.changeName("daim");

		System.out.println(p1.equals(p2));

		System.out.println(p1.compareTo(p2));
	}

}
