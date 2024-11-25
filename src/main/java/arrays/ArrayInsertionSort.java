package arrays;

public class ArrayInsertionSort {
	// To sort a[left. . .right]:
	// 1. For r = left+1, . . . , right, repeat:
	// 1.1. Let val = a[r].
	// 1.2. Insert val into a[left . . .r] in such a way that the subarray is
	// sorted.
	// 2. Terminate.

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

		insertionSort(strings, 0, strings.length - 1);
		ArrayHelper.printArray(strings);
	}

	@SuppressWarnings("unchecked")
	private static <T> void insertionSort(Comparable<T>[] a, int left, int right) {

		for (int i = left + 1; i <= right; i++) {

			Comparable<T> least = a[i];
			int j = i;
			while (j > left && a[j - 1].compareTo((T) least) > 0) {
				a[j] = a[j - 1];
				j--;
			}
			a[j] = least;

		}

	}

}
