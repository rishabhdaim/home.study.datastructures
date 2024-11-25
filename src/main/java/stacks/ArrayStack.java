package stacks;

import java.util.Arrays;
import java.util.NoSuchElementException;

//1. It must be possible to make a stack empty.
//2. It must be possible to add an element to the top of a stack.
//3. It must be possible to remove the topmost element from a stack.
//4. It must be possible to test whether a stack is empty.
//5. It should be possible to access the element at the top of a stack without removing it.

/**
 * Each Arrays tack object is a unbounded stack whose elements are objects.This
 * stack is represented as follows: its depth is held in depth, and its elements
 * occupy the subarray elems[0...depth?l].
 * 
 * @author ANKIT DAIM
 * 
 * @param <E>
 */
public class ArrayStack<E> {

	// instance fields..

	private E[] element;
	private int size;

	/**
	 * creates an empty array of 20 elements..
	 */
	@SuppressWarnings("unchecked")
	public ArrayStack() {
		element = (E[]) new Object[20];
		size = 0;
	}

	/**
	 * @return returns true if and only if size is zero i.e. ArrayStack<E> is
	 *         empty..
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * @return return the last in element of this stack..
	 */
	public E getLast() {
		if (size == 0)
			throw new NoSuchElementException("Stack is empty..");
		return element[size - 1];
	}

	/**
	 * @param n
	 * @return
	 */
	public E getElement(int n) {
		if (n < 0 || n > size)
			throw new IndexOutOfBoundsException("index out of bounds.");
		if (size == 0)
			throw new NoSuchElementException("Stack is empty..");
		return element[size - 1 - n];
	}

	/**
	 * Make this stack empty.
	 */
	public void clear() {
		for (int i = 0; i < size; i++)
			element[i] = null;
		size = 0;
	}

	/**
	 * this method add e to this stack..
	 * 
	 * @param e
	 *            the value to be added to this stack..
	 */
	public void addLast(E e) {
		if (size == element.length)
			expand();
		element[size++] = e;
	}

	/**
	 * @return element at last position..
	 */
	public E removeLast() {
		if (size == 0)
			throw new NoSuchElementException("stack is emtpy..");
		E e = element[--size];
		element[size] = null;
		return e;
	}

	/**
	 * used to expand array..
	 */
	private void expand() {
		@SuppressWarnings("unchecked")
		E[] e = (E[]) new Object[2 * element.length];
		for (int i = 0; i < size; i++)
			e[i] = element[i];
		element = e;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		StringBuilder builder = new StringBuilder();
		for (int i = size; i > 0; i--)
			builder.append(element[i - 1] + " ");

		return "ArrayStack [element=" + builder.toString() + ", size=" + size
				+ "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(element);
		result = prime * result + size;
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		ArrayStack<?> other = (ArrayStack<?>) obj;
		if (!Arrays.equals(element, other.element)) {
			return false;
		}
		if (size != other.size) {
			return false;
		}
		return true;
	}
}
