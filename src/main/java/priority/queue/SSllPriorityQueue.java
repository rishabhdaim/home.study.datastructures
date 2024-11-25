package priority.queue;

import java.util.NoSuchElementException;

/**
 * Each SllPriorityQueue object is a priority queue whose elements are
 * Comparable objects
 * 
 * @author ANKIT DAIM
 * 
 * @param <E>
 */
public class SSllPriorityQueue<E extends Comparable<? super E>> implements
		PriorityQueue<E> {

	Node<E> first;
	Node<E> last;
	int length;

	public SSllPriorityQueue() {
		first = null;
		last = null;
		length = 0;
	}

	@Override
	public void clear() {
		first = null;
		last = null;
		length = 0;
	}

	@Override
	public boolean isEmpty() {
		return length == 0;
	}

	// Add elem to this priority queue.
	@Override
	public void addLast(E e) {
		Node<E> prev = null;
		Node<E> curr = first;
		while (curr != null && e.compareTo(curr.element) >= 0) {
			prev = curr;
			curr = curr.next;
		}
		Node<E> newNode = new Node<>(e, curr);
		if (prev == null)
			first = newNode;
		else
			prev.next = newNode;
		// to add last pointer..
		if (newNode.next == null)
			last = newNode;
		length++;
	}

	@Override
	public E removeFirst() {
		if (first == null)
			throw new NoSuchElementException("Queue is empty..");
		Node<E> n = first;
		first = first.next;
		length--;
		return n.element;
	}

	@Override
	public E getFirst() {
		if (first == null)
			throw new NoSuchElementException("Queue is empty..");
		return first.element;
	}

	@Override
	public int size() {
		return length;
	}

	@Override
	public E getGreatest() {
		if (first == null)
			throw new NoSuchElementException("Queue is empty..");
		return last.element;
	}

	static class Node<E> {
		private E element;
		private Node<E> next;

		/**
		 * @param element
		 * @param next
		 */
		public Node(E element, Node<E> next) {
			super();
			this.element = element;
			this.next = next;
		}

		/**
		 * @return the element
		 */
		public E getElement() {
			return element;
		}

		/**
		 * @return the next
		 */
		public Node<E> getNext() {
			return next;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "" + element + " ";
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
		return "SSllPriorityQueue [length=" + length + " : " + sb.toString()
				+ " ]";
	}
}
