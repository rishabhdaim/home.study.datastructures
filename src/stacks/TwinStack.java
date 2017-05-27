package stacks;

import java.util.NoSuchElementException;

public class TwinStack<E> {

	private E[] element;
	private int forwardSize, backwardSize;

	/**
	 * creates an empty array of 20 elements..
	 */
	@SuppressWarnings("unchecked")
	public TwinStack() {
		element = (E[]) new Object[10];
		forwardSize = backwardSize = 0;
	}

	/**
	 * @return returns true if and only if size is zero i.e. ArrayStack<E> is
	 *         empty..
	 */
	public boolean isEmpty() {
		return forwardSize + backwardSize == 0;
	}

	public E getLast() {
		return element[forwardSize - 1];
	}

	public E getFirst() {
		return element[element.length - backwardSize - 1];
	}

	public void addLast(E e) {
		if (forwardSize + backwardSize == element.length)
			expand();
		element[forwardSize++] = e;
	}

	public void addFirst(E e) {
		if (forwardSize + backwardSize == element.length)
			expand();
		element[element.length - 1 - backwardSize++] = e;
	}

	public E removeLast() {
		if (forwardSize == 0)
			throw new NoSuchElementException("stack is emtpy..");
		E e = element[--forwardSize];
		element[forwardSize] = null;
		return e;
	}

	public E removeFirst() {
		if (backwardSize == 0)
			throw new NoSuchElementException("stack is emtpy..");
		E e = element[element.length - 1 - --backwardSize];
		element[element.length - 1 - backwardSize] = null;
		return e;
	}

	private void expand() {
		@SuppressWarnings("unchecked")
		E[] e = (E[]) new Object[2 * element.length];
		for (int i = 0, l = forwardSize; i < l; i++)
			e[i] = element[i];
		for (int i = element.length - 1, j = e.length - 1, l = backwardSize; l > 0; l--)
			e[j--] = element[i--];
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
		for (int i = element.length; i > 0; i--)
			builder.append(element[i - 1] + " ");
		return "TwinStack [element=" + builder.toString() + ", forwardSize="
				+ forwardSize + ", backwardSize=" + backwardSize + "]";
	}
}
