package arrays;

public class ArrayDeletion {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// To delete the value at position del of a[left...right] (where left <
		// del < right):
		// 1. Copy a[del+1... right] into a[del... right?1].
		// 2. Make a[right] unoccupied.
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
		delete(2, strings, 0, strings.length - 1);
		ArrayHelper.printArray(strings);
	}

	public static void delete(int index, Object[] arr, int left, int right) {
		// Delete the object at position del of a [left...right ]
		// (where left < del < right).
		for (int i = index; i < right; i++)
			arr[i] = arr[i + 1];
		arr[right] = null;
	}
}
