package queues;

import java.util.NoSuchElementException;

/**
 * @author ANKIT DAIM
 * 
 * @param <E>
 */
public class LinkedQueue<E> implements Queue<E> {

	private Node<E> front;
	private Node<E> rear;
	private int length;

	public LinkedQueue() {
		front = null;
		rear = null;
		length = 0;
	}

	@Override
	public void clear() {
		front = rear = null;
		length = 0;
	}

	@Override
	public boolean isEmpty() {
		return front == null;
	}

	@Override
	public void addLast(E e) {
		Node<E> temp = new Node<E>(e, null);
		if (rear != null)
			rear.next = temp;
		else
			front = temp;
		rear = temp;
		length++;
	}

	@Override
	public E removeFirst() {
		if (front == null)
			throw new NoSuchElementException("queue is empty..");
		E e = front.element;
		front = front.next;
		if (front == null)
			rear = null;
		length--;
		return e;
	}

	@Override
	public E getFirst() {
		if (front == null)
			throw new NoSuchElementException("queue is empty..");
		return front.element;
	}

	@Override
	public int size() {
		return length;
	}

	/**
	 * @author ANKIT DAIM
	 * 
	 * @param <E>
	 */
	private static class Node<E> {
		E element;
		Node<E> next;

		/**
		 * @param element
		 * @param next
		 */
		public Node(E element, Node<E> next) {
			super();
			this.element = element;
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
		String temp = "";
		for (Node<E> node = front; node != null; node = node.next)
			temp += node.element + " ";
		return "LinkedQueue [front=" + temp + ", length=" + length + "]";
	}

}
