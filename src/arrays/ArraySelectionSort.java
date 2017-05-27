package arrays;

public class ArraySelectionSort {

	// To sort a[left...right]:
	// 1. For l = left, ..., right–1, repeat:
	// 1.1. Set p such that a[p] is the least of a[l...right].
	// 1.2. If p* l, swap a[p] and a[l].
	// 2. Terminate.

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[] strings = new String[10];
		strings[0] = "a";
		strings[1] = "z";
		strings[2] = "j";
		strings[3] = "m";
		strings[4] = "e";
		strings[5] = "f";
		strings[6] = "w";
		strings[7] = "h";
		strings[8] = "i";
		strings[9] = "x";
		ArrayHelper.printArray(strings);

		selectionSort(strings, 0, strings.length - 1);
		ArrayHelper.printArray(strings);
	}

	public static <T> void selectionSort(Comparable<T>[] a, int left, int right) {
		for (int i = left; i < right; i++) {
			int least = i;
			Comparable<T> temp = a[i];
			for (int j = i + 1; j <= right; j++) {
				@SuppressWarnings("unchecked")
				int comp = a[j].compareTo((T) a[least]);
				if (comp < 0)
					least = j;
			}

			if (least != i) {
				a[i] = a[least];
				a[least] = temp;
			}
		}
	}
}
