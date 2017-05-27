package heap;

import java.util.NoSuchElementException;

public class ArrayHeap<E> {
	private E[] elements;
	private int length;

	private static final int DEFAULT_CAPACITY = 2;

	public ArrayHeap() {
		this(DEFAULT_CAPACITY);
	}

	@SuppressWarnings("unchecked")
	public ArrayHeap(int capacity) {
		elements = (E[]) new Object[capacity];
		length = 0;
	}

	/**
	 * @param e
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int insert(E e) {
		if (length == elements.length)
			expand();
		int hole = length++;
		for (;;) {
			if (hole == 0) {
				elements[hole] = e;
				return hole;
			}

			int parent = (hole - 1) >> 1;

			if (((Comparable<E>) elements[parent]).compareTo(e) <= 0) {
				elements[hole] = e;
				return hole;
			} else {
				elements[hole] = elements[parent];
				hole = parent;
			}
		}
	}

	/**
	 * to expand the element array..
	 */
	private void expand() {
		E[] oldHeap = elements;
		@SuppressWarnings("unchecked")
		E[] newHeap = (E[]) new Object[elements.length << 1];

		for (int i = 0, l = oldHeap.length; i < l; i++)
			newHeap[i] = oldHeap[i];
		elements = newHeap;
	}

	/**
	 * @param e
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public E removeFirst() {
		if (length == 0)
			throw new NoSuchElementException("queue is empty..");
		E last = elements[--length];
		E least = elements[0];
		int hole = 0;
		for (;;) {
			int left = 2 * hole + 1;
			int right = 2 * hole + 2;
			int child;

			if (left > length) {
				// no childs..
				elements[hole] = last;
				elements[length] = null;
				return least;
			} else if (right > length) // only left child..
				child = left;
			else
				child = ((Comparable<E>) elements[left])
						.compareTo(elements[right]) <= 0 ? left : right;
			if (((Comparable<E>) last).compareTo(elements[child]) <= 0) {
				elements[hole] = last;
				elements[length] = null;
				return least;
			} else {
				elements[hole] = elements[child];
				hole = child;
			}
		}
	}

	/**
	 * @param arrayHeap
	 * @return
	 */

	// To sort a sequence of elements input into a sequence output:
	// 1. Create an empty heap.
	// 2. For each element elem of input, repeat:
	// 2.1. Insert elem into the heap.
	// 3. Make output empty.
	// 4. While the heap is nonempty, repeat:
	// 4.1. Remove the least element from the heap into elem.
	// 4.2. Append elem to output.
	// 5. Terminate.
	public E[] heapSort(ArrayHeap<E> arrayHeap) {

		@SuppressWarnings("unchecked")
		E[] es = (E[]) new Object[arrayHeap.length];
		int i = 0;
		while (arrayHeap.length > 0)
			es[i++] = arrayHeap.removeFirst();
		return es;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append("{");

		for (E e : elements)
			if (e != null)
				sb.append(e + " ");
		sb.append("}");
		return "ArrayHeap [elements=" + sb.toString() + ", length=" + length
				+ "]";
	}
}
