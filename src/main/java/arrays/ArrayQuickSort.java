package arrays;

public class ArrayQuickSort {

	// To sort a[left. . .right]:
	// 1. If left < right:
	// 1.1. Partition a[left...right] such that a[left...p-1] are all less than
	// or equal to
	// a[p], and a[p+1. . .right] are all greater than or equal to a[p].
	// 1.2. Sort a[left...p-1].
	// 1.3. Sort a[p+1...right].
	// 2. Terminate.

	// To partition a[left. . .right] such that a[left. . .p?1] are all less
	// than or equal to a[p],
	// and a[p+1. . .right] are all greater than or equal to a[p]:
	// 1. Let pivot be the value of a[left], and set p = left.
	// 2. For r - left+1, . . . , right, repeat:
	// 2.1. If a[r] is less than pivot:
	// 2.1.1. Copy a[r] into a[p], a[p+1] into a[r], and pivot into a[p+1 ].
	// 2.1.2. Increment p.
	// 3. Terminate with answer p.
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

		quickSort(strings, 0, strings.length - 1);
		ArrayHelper.printArray(strings);
	}

	private static <T> void quickSort(Comparable<T>[] a, int left, int right) {
		if (left < right) {
			int p = partition(a, left, right);
			quickSort(a, left, p - 1);
			quickSort(a, p + 1, right);
		}

	}

	private static <T> int partition(Comparable<T>[] a, int left, int right) {
		// Partition a [left. . .right] such that a [left. . .p-1 ] are all less
		// than or equal to a[p] and a [p+1 ...right] are all greater than or
		// equal to a[p].
		Comparable<T> pivot = a[left];
		int p = left;
		for (int i = left + 1; i <= right; i++) {
			@SuppressWarnings("unchecked")
			int comp = a[i].compareTo((T) pivot);
			if (0 > comp) {
				a[p] = a[i];
				a[i] = a[p + 1];
				a[p + 1] = pivot;
				p++;
			}
		}
		return p;
	}
}