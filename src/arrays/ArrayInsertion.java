package arrays;

public class ArrayInsertion {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// To insert val at position ins in a[left ... right] (where left < ins
		// < right):
		// 1. Copy a[ins...right-1] intoa[ins+l...right].
		// 2. Copy val into a[ins],
		// 3. Terminate.
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

		insert("k", 2, strings, 0, strings.length - 1);

		ArrayHelper.printArray(strings);
	}

	private static void insert(Object value, int index, Object[] arr, int left,
			int right) {
		// Insert val at position ins in a [ left...right ]
		// (where left < ins < right).
		for (int i = right; i > index; i--)
			arr[i] = arr[i - 1];
		arr[index] = value;
	}
}
