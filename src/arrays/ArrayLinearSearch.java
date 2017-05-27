package arrays;

public class ArrayLinearSearch {
	// distinct from any array index.
	private static final int NONE = -1;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// To find which (if any) component of a[left...right] equals target:
		// 1. For p = left, ..., right, repeat:
		// 1.1. If target is equal to a[p], terminate with answer p.
		// 2. Terminate with answer none.

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
		int i = linearSearch("as", strings, 0, strings.length - 1);
		System.out.println(i);
		i = linearSearch("j", strings, 0, strings.length - 1);
		System.out.println(i);

		i = linearSortedArraySearch("as", strings, 0, strings.length - 1);
		System.out.println(i);
		i = linearSortedArraySearch("j", strings, 0, strings.length - 1);
		System.out.println(i);
	}

	private static int linearSearch(Object value, Object[] array, int left,
			int right) {
		for (int i = left; i <= right; i++)
			if (value.equals(array[i]))
				return i;
		return NONE;
	}

	private static <T> int linearSortedArraySearch(Comparable<T> value,
			Comparable<T>[] array, int left, int right) {
		// To find which (if any) component of a[left...right] equals target
		// (where a is sorted):
		// 1. For p = left, ..., right, repeat:
		// /1.1. If target is equal to a[p], terminate with answer p.
		// 1.2. If target is less than a[p], terminate with answer none.
		// 2. Terminate with answer none.

		for (int i = left; i <= right; i++) {
			@SuppressWarnings("unchecked")
			int comp = value.compareTo((T) array[i]);
			if (0 == comp)
				return i;
			else if (0 > comp)
				return NONE;
		}
		return NONE;

	}
}
