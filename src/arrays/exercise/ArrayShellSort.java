package arrays.exercise;

import arrays.ArrayHelper;

public class ArrayShellSort {

	// To sort a[left. . .right]:
	// 1. Set gap to right - left + 1.
	// 2. Repeat:
	// 2.1. Halve gap (discarding any remainder).
	// 2.2. If gap is even, increment gap.
	// 2.3. For i = gap, ..., right, repeat:
	// 2.3.1. Copy a[i] to current.
	// 2.3.2. Set j to i-gap.
	// 2.3.3. While j > left and current is less than a[j], repeat:
	// 2.3.3.1. Copy a[j] to a[j + gap].
	// 2.3.3.2. Decrement. j by gap.
	// 2.3.4. Copy current to a[j + gap].
	// 2.4. If gap = 1, terminate.

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[] a = ArrayHelper.unSortedArray();
		shellSort(a, 0, a.length - 1);

		ArrayHelper.printArray(a);
	}

	@SuppressWarnings("unchecked")
	private static <T> void shellSort(Comparable<T>[] a, int l, int r) {
		int gap = (l + r) >>> 1;
		while (gap > 1) {
			if (gap % 2 == 0)
				gap += 1;
			shellSort(a, l, gap);
			for (int i = gap; i <= r; i++) {
				Comparable<T> current = a[i];
				int j = i - gap;

				while (j > l && current.compareTo((T) a[j]) < 0) {
					a[j + gap] = a[j];
					j = j - gap;
				}

				a[j + gap] = current;
			}
		}

	}
}
