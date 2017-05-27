package arrays.exercise;

import arrays.ArrayHelper;
import arrays.ArraySelectionSort;

public class SetFunction {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[] a = ArrayHelper.unSortedArray();
		String[] b = ArrayHelper.sortedArray();
		String[] c = new String[a.length + b.length];
		String[] d = new String[a.length + b.length];
		ArraySelectionSort.selectionSort(a, 0, a.length - 1);
		ArrayHelper.printArray(a);
		ArrayHelper.printArray(b);
		setUnion(a, 0, a.length - 1, b, 0, b.length - 1, c, 0);

		ArrayHelper.printArray(a);
		ArrayHelper.printArray(b);
		ArrayHelper.printArray(c);

		setIntersection(a, 0, a.length - 1, b, 0, b.length - 1, d, 0);

		ArrayHelper.printArray(d);

	}

	private static <T> void setUnion(Comparable<T>[] a, int l1, int r1,
			Comparable<T>[] b, int l2, int r2, Comparable<T>[] c, int l3) {

		int i = l1;
		int j = l2;
		int k = l3;

		while (i <= r1 && j <= r2) {
			@SuppressWarnings("unchecked")
			int comp = a[i].compareTo((T) b[j]);
			if (comp < 0)
				c[k++] = a[i++];
			else if (comp > 0)
				c[k++] = b[j++];
			else
				c[k++] = b[j++] = a[i++];
		}
		while (i <= r1)
			c[k++] = a[i++];
		while (j <= r2)
			c[k++] = b[j++];
	}

	private static <T> void setIntersection(Comparable<T>[] a, int l1, int r1,
			Comparable<T>[] b, int l2, int r2, Comparable<T>[] d, int l3) {

		int i = l1;
		int j = l2;
		int k = l3;

		while (i <= r1 && j <= r2) {
			@SuppressWarnings("unchecked")
			int comp = a[i].compareTo((T) b[j]);
			if (comp == 0)
				d[k++] = a[i++] = b[j++];
			else if (comp > 0)
				j++;
			else
				i++;
		}
	}
}
