package priority.queue;

import java.util.NoSuchElementException;

/**
 * @author ANKIT DAIM
 * 
 * @param <E>
 */

// Each HeapPriorityQueue object is a priority queue whose elements are
// Comparable objects. This priority queue is represented as follows: the
// subarray el ems [0....length-1 ] contains the priority queue's elements,
// arranged in such a way that elems [(p-l)/2] is less than or equal to el ems
// [p] for every p>0.
public class HeapPriorityQueue<E extends Comparable<? super E>> implements
		PriorityQueue<E> {

	private E[] elements;
	private int length;

	/*
	 * some constants..
	 */
	private static final int DEFAULT_CAPACITY = 16;

	public HeapPriorityQueue() {
		this(DEFAULT_CAPACITY);
	}

	@SuppressWarnings("unchecked")
	public HeapPriorityQueue(int capacity) {
		elements = (E[]) new Object[capacity];
		length = 0;
	}

	@Override
	public void clear() {
		for (@SuppressWarnings("unused")
		E e : elements)
			e = null;
		for (int i = 0, l = elements.length; i < l; i++)
			elements[i] = null;
		length = 0;
	}

	@Override
	public boolean isEmpty() {
		return length == 0;
	}

	// To insert the element elem into a heap:
	// 1. Expand the heap by one position, and set hole to the new position.
	// 2. Repeat:
	// 2.1. If hole is the root position:
	// 2.1.1. Store elem at position hole.
	// 2.1.2. Terminate.
	// 2.2. Set parent to the position of hole's parent.
	// 2.3. If the element at position parent is less than or equal to elem:
	// 2.3.1. Store elem at position hole.
	// 2.3.2. Terminate.
	// 2.4. If the element at position parent is greater than elem:
	// 2.4.1. Copy the element at position parent into position hole.
	// 2.4.2. Set hole to parent.
	@Override
	public void addLast(E e) {
		if (length == elements.length)
			expand();
		int hole = length++;

		for (;;) {
			if (hole == 0) {
				elements[0] = e;
				return;
			}

			int parent = (hole - 1) >> 1;
			if ((elements[parent]).compareTo(e) <= 0) {
				elements[hole] = e;
				return;
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

	// To delete the least element in a heap:
	// 1. Set least to the element at the root position, and set hole to the
	// root position.
	// 2. Set last to the element at the last position, and contract the heap by
	// eliminating that position.
	// 3. Repeat:
	// 3.1. If hole has no child:
	// 3.1.1. Store last at position hole.
	// 3.1.2. Terminate with answer least.
	// 3.2. If hole has a left child only:
	// 3.2.1. Set child to the position of hole's left child.
	// 3.3. If hole has two children:
	// 3.3.1. Set child to the position of whichever child of hole contains
	// the lesser element.
	// 3.4. If last is less than or equal to the element at position child:
	// 3.4.1. Store last at position hole.
	// 3.4.2. Terminate with answer least.
	// 3.5. If last is greater than the element at position child:
	// 3.5.1. Copy the element at position child into position hole.
	// 3.5.2. Set hole to child.

	/*
	 * Remove and return the least element in this priority queue, or throw a
	 * NoSuchElementException if this priority queue is empty.(If there are
	 * several equal least elements, remove the same element that would be
	 * returned by getLeast.)
	 * 
	 * (non-Javadoc)
	 * 
	 * @see queues.MyQueue#removeFirst()
	 */
	@Override
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
				// no left child,
				elements[hole] = last;
				elements[length] = null;
				return least;
			} else if (right > length)
				// only left child..
				child = left;
			else
				child = (elements[left]).compareTo(elements[right]) <= 0 ? left
						: right;

			if (last.compareTo(elements[child]) <= 0) {
				elements[hole] = last;
				elements[length] = null;
				return least;
			} else {
				elements[hole] = elements[child];
				hole = child;
			}
		}
	}

	@Override
	public E getFirst() {
		if (length == 0)
			throw new NoSuchElementException("queue is empty..");
		return elements[0];
	}

	@Override
	public int size() {
		return length;
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
		return "HeapPriorityQueue [elements=" + sb.toString() + ", length="
				+ length + "]";
	}

	@Override
	public E getGreatest() {
		return null;
	}
}
