package stacks;

import java.util.NoSuchElementException;

public class LinkedStack<E> {

	private Node<E> top;
	private int size;

	/**
	 * creates empty {@link LinkedStack}..
	 */
	public LinkedStack() {
		this.top = null;
	}

	/**
	 * to clear the stack..
	 */
	public void clear() {
		top = null;
	}

	/**
	 * @param e
	 *            element to be added..
	 */
	public void addLast(E e) {
		top = new Node<E>(e, top);
		size++;
	}

	/**
	 * @return
	 */
	public E removeLast() {
		if (top == null)
			throw new NoSuchElementException("stack is empty..");
		E e = top.e;
		top = top.next;
		size--;
		return e;
	}

	/**
	 * @return
	 */
	public boolean isEmpty() {
		return top == null;
	}

	public E getLast() {

		if (top == null)
			throw new NoSuchElementException("stack is empty..");
		return top.e;
	}

	private static class Node<E> {
		E e;
		Node<E> next;

		/**
		 * @param e
		 * @param next
		 */
		public Node(E e, Node<E> next) {
			super();
			this.e = e;
			this.next = next;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		for (Node<E> node = top; node != null; node = node.next)
			builder.append(node.e + " ");
		return "LinkedStack [elements=" + builder.toString() + "]";
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
		Node<E> temp = top;
		for (int i = 0; i < size - 1; i++) {
			temp = temp.next;
		}
		return temp.e;
	}
}
