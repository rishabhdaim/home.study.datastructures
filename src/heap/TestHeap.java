package heap;

import java.util.Arrays;

public class TestHeap {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayHeap<Integer> arrayHeap = new ArrayHeap<>();
		arrayHeap.insert(1);
		arrayHeap.insert(12);
		arrayHeap.insert(33);
		arrayHeap.insert(42);
		arrayHeap.insert(15);
		arrayHeap.insert(56);
		arrayHeap.insert(16);
		arrayHeap.insert(17);
		arrayHeap.insert(13);
		arrayHeap.insert(21);
		arrayHeap.insert(126);
		arrayHeap.insert(117);
		arrayHeap.insert(143);
		arrayHeap.insert(215);
		System.out.println(arrayHeap);

		System.out.println(Arrays.toString(arrayHeap.heapSort(arrayHeap)));
	}

}
