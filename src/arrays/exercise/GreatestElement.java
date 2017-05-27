package arrays.exercise;

import arrays.ArrayHelper;

public class GreatestElement {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
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

		ArrayHelper.printArray(strings);
		System.out.println();

		Comparable<String> comparable = greatestElement(strings, 0,
				strings.length - 1);
		System.out.println(comparable);
	}

	private static <T> Comparable<T> greatestElement(Comparable<T>[] a, int l,
			int r) {

		Comparable<T> greatest = a[l];
		for (int i = l + 1; i < r; i++) {
			@SuppressWarnings("unchecked")
			int comp = greatest.compareTo((T) a[i]);
			if (comp < 0)
				greatest = a[i];
		}
		return greatest;

	}
}
