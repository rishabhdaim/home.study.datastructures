package arrays;

public class ArrayMergeSort {

	@SuppressWarnings("unused")
	private static int count;
	@SuppressWarnings("unused")
	private static int mergeCount;

	// To sort a[left. . .right]:
	// 1. If left < right:
	// 1.1. Let m be an integer about midway between left and right (such that
	// left < m< right).
	// 1.2. Sort a[left. . .m].
	// 1.3. Sort a[m+1. . .right].
	// 1.4. Merge a[left. . .m] and a[m+1. . .right] into an auxiliary array b.
	// \ .5. Copy all components of b into a[left. . .right].
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

		mergeSort(strings, 0, strings.length - 1);
	}

	private static <T> void mergeSort(Comparable<T>[] a, int left, int right) {

		// Sort a [left. . .right].
		@SuppressWarnings("unchecked")
		Comparable<T>[] b = new Comparable[right - left + 1];

		doMergeSort(a, b, left, right);
	}

	private static <T> void doMergeSort(Comparable<T>[] a, Comparable<T>[] b,
			int left, int right) {
		count++;
		// Merge-sort a [ left... right ] using auxiliary array b.
		int l = left;
		int r = right;
		int m = (l + r) / 2;

		// System.out.printf("left %d right %d middle %d count %d%n", l, r, m,
		// count);

		if (l < r) {
			doMergeSort(a, b, left, m);
			// System.out.println("done left..");

			doMergeSort(a, b, m + 1, right);

			// System.out.println("done right..");
			mergeArrays(a, left, m, a, m + 1, right, b, 0);
			for (int k = left; k <= right; k++)
				a[k] = b[k - left];
			ArrayHelper.printArray(b);
		}
	}

	private static <T> void mergeArrays(Comparable<T>[] arr1, int left1,
			int right1, Comparable<T>[] arr2, int left2, int right2,
			Comparable<T>[] arr3, int left3) {

		mergeCount++;

		int k = left3;
		int i = left1, j = left2;
		// System.out.println("merger count : " + mergeCount + " " + k + " " + i
		// + " " + j);

		while (i <= right1 && j <= right2) {
			@SuppressWarnings("unchecked")
			int comp = arr1[i].compareTo((T) arr2[j]);
			if (0 >= comp)
				arr3[k++] = arr1[i++];
			else
				arr3[k++] = arr2[j++];
		}
		while (i <= right1)
			arr3[k++] = arr1[i++];
		while (j <= right2)
			arr3[k++] = arr2[j++];
		// System.out.println();
		// printArray(arr3);
	}
}
