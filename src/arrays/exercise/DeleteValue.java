package arrays.exercise;

import arrays.ArrayDeletion;
import arrays.ArrayHelper;

public class DeleteValue {

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
		deleteValueUnSorted("w", strings, 0, strings.length - 1);

		ArrayHelper.printArray(strings);

	}

	private static <T> void deleteValueUnSorted(Comparable<T> value,
			Comparable<T>[] a, int l, int r) {
		for (int i = l; i <= r; i++) {
			@SuppressWarnings("unchecked")
			int comp = value.compareTo((T) a[i]);

			if (comp == 0) {
				ArrayDeletion.delete(i, a, l, r);
				break;
			}
		}
	}
}
