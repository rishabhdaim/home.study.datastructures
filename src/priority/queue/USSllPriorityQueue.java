package priority.queue;

import java.util.NoSuchElementException;

public class USSllPriorityQueue<E extends Comparable<? super E>> implements
		PriorityQueue<E> {

	private Node<E> first;
	private int length;
	Node<E> last;

	public USSllPriorityQueue() {
		first = null;
		length = 0;
		last = null;
	}

	@Override
	public void clear() {
		first = null;
		length = 0;
		last = null;
	}

	@Override
	public boolean isEmpty() {
		return length == 0;
	}

	@Override
	public void addLast(E e) {
		Node<E> newNode = new Node<E>(e, null);
		if (last == null)
			first = newNode;
		else
			last.next = newNode;
		last = newNode;
		length++;
	}

	@Override
	public E removeFirst() {

		if (first == null)
			throw new NoSuchElementException("queue is empty..");
		E least = first.element;
		Node<E> prev = null;
		Node<E> temp = null;
		Node<E> curr = first;

		for (; curr != null; prev = curr, curr = curr.next)
			if (least.compareTo(curr.element) > 0) {
				least = curr.element;
				temp = prev;
			}
		if (temp == null)
			first = first.next;
		else
			temp.next = temp.next.next;
		length--;
		return least;
	}

	@Override
	public E getFirst() {
		if (first == null)
			throw new NoSuchElementException("queue is empty..");
		E least = first.element;
		Node<E> curr = first;

		for (; curr != null; curr = curr.next)
			if (least.compareTo(curr.element) > 0) {
				least = curr.element;
			}
		return least;
	}

	@Override
	public int size() {
		return length;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see priority.queue.MyPriorityQueue#getGreatest()
	 */
	@Override
	public E getGreatest() {
		if (first == null)
			throw new NoSuchElementException("Queue is empty..");
		E greatest = first.element;
		for (Node<E> curr = first; curr != null; curr = curr.next)
			if (greatest.compareTo(curr.element) < 0)
				greatest = curr.element;

		return greatest;
	}

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

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return " " + element + " ";
		}
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
		for (Node<E> n = first; n != null; n = n.next)
			sb.append(n.toString());
		sb.append("}");
		return "USSllPriorityQueue [length=" + length + " : " + sb.toString()
				+ " ]";
	}
}
