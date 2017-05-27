package arrays;

public class ArrayHelper {

	// To make this class non-instantiable

	private ArrayHelper() {

	}

	public static void printArray(Object[] strings) {
		for (Object string : strings)
			System.out.print(string + " ");
		System.out.println();
	}

	public static String[] unSortedArray() {

		String[] strings = new String[10];
		strings[0] = "b";
		strings[1] = "z";
		strings[2] = "j";
		strings[3] = "m";
		strings[4] = "e";
		strings[5] = "f";
		strings[6] = "w";
		strings[7] = "h";
		strings[8] = "i";
		strings[9] = "a";
		return strings;
	}

	public static String[] sortedArray() {

		String[] strings = new String[10];
		strings[0] = "a";
		strings[1] = "b";
		strings[2] = "c";
		strings[3] = "d";
		strings[4] = "e";
		strings[5] = "f";
		strings[6] = "g";
		strings[7] = "h";
		strings[8] = "i";
		strings[9] = "j";
		return strings;
	}
}
