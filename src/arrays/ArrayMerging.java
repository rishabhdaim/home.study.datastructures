package arrays;

public class ArrayMerging {

	// To merge a1[left1...right1] and a2[left2...right2] into a3[left3...]
	// (where both al and a2 are sorted):
	// 1. Set i = left1, set j = Ieft2, and set k = left3.
	// 2. While i <= right 1 and j <= right2, repeat:
	// 2.1. If al[i] is less than or equal to a2[j]:
	// 2.1.1. Copy al[i] into a3[k].
	// 2.1.2. Increment i and k.
	// 2.2. Otherwise, if al[i] is greater than or equal to d2[j]:
	// 2.2.1. Copy a2[j] into a3[k].
	// 2.2.2. Increment 7 and k.
	// 3. While i <= right1, repeat:
	// 3.1. Copy al[i] into a3[k].
	// 3.2. Increment i and k.
	// 4. While j <= right2, repeat:
	// 4.1. Copy a2[j] into a3[k].
	// 4.2. Increment j and k.
	// 5. Terminate.

	/**
	 * @param args
	 */
	public static void main(String[] args) {
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
		strings[9] = "x";

		ArrayHelper.printArray(strings);

		String[] strings2 = new String[10];
		strings2[0] = "a";
		strings2[1] = "l";
		strings2[2] = "m";
		strings2[3] = "n";
		strings2[4] = "o";
		strings2[5] = "p";
		strings2[6] = "q";
		strings2[7] = "r";
		strings2[8] = "s";
		strings2[9] = "z";

		ArrayHelper.printArray(strings2);

		String[] strings3 = new String[strings.length + strings2.length];

		mergeArrays(strings, 0, strings.length - 1, strings2, 0,
				strings2.length - 1, strings3, 0);

		ArrayHelper.printArray(strings3);
	}

	private static <T> void mergeArrays(Comparable<T>[] arr1, int left1,
			int right1, Comparable<T>[] arr2, int left2, int right2,
			Comparable<T>[] arr3, int left3) {
		int k = left3;
		int i = left1, j = left2;

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
	}
}
