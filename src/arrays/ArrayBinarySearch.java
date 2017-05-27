package arrays;

public class ArrayBinarySearch {

	// distinct from any array index.
	private static final int NONE = -1;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// To find which (if any) component of a[left...right] equals target
		// (where a is sorted):
		// 1. Set l = left, and set r = right.
		// 2. While l < r, repeat:
		// 2.1. Let m be an integer about midway between l and r.
		// 2.2. If target is equal to a[m], terminate with answer m.
		// 2.3. If target is less than a[m], set r = m – 1.
		// 2.4. If target is greater than a[m], set l = m + 1.
		// 3. Terminate with answer none.

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

		ArrayHelper.printArray(strings);
		int i = binarySearch("l", strings, 0, strings.length - 1);
		System.out.println(i);
		i = binarySearch("f", strings, 0, strings.length - 1);
		System.out.println(i);
	}

	private static <T> int binarySearch(Comparable<T> value,
			Comparable<T>[] array, int left, int right) {
		// Find which (if any) component of a [ left... right] equals target
		// (where a is sorted).
		int l = left;
		int r = right;
		int m;
		for (int i = left; i <= right; i++) {
			while (l < r) {
				m = (l + r) / 2;
				@SuppressWarnings("unchecked")
				int comp = value.compareTo((T) array[m]);
				if (0 == comp)
					return m;
				else if (0 > comp)
					r = m - 1;
				else
					l = m + 1;
			}
		}
		return NONE;
	}
}
