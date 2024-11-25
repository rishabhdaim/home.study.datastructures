package lists;

import java.util.NoSuchElementException;

public class LinkedList<E> implements MyList<E> {

	private Node<E> first;
	private Node<E> last;
	private int length;

	public LinkedList() {
		this.first = this.last = null;
		this.length = 0;
	}

	@Override
	public void clear() {
		this.last = this.first = null;
		this.length = 0;
	}

	@Override
	public boolean isEmpty() {
		return this.length == 0;
	}

	@Override
	public int size() {
		return this.length;
	}

	@Override
	public void add(int i, E e) {
		if (i < 0 || i > length)
			throw new NoSuchElementException("index out of bounds");

		Node<E> temp = new Node<E>(e, null);

		if (i == 0) {
			temp.next = first;
			first = temp;
		} else {
			Node<E> pred = node(i - 1);
			temp.next = pred.next;
			pred.next = temp;
		}
		if (temp.next == null)
			last = temp;
		length++;
	}

	/**
	 * @param i
	 * @return node at i position..
	 */
	private Node<E> node(int i) {
		Node<E> curr = first;

		for (int j = 0; j < i; j++)
			curr = curr.next;
		return curr;
	}

	@Override
	public void add(E e) {
		Node<E> temp = new Node<E>(e, null);

		if (first == null)
			this.first = temp;
		else
			last.next = temp;
		last = temp;
		length++;
	}

	@Override
	public E remove(int i) {
		if (i < 0 || i > length)
			throw new NoSuchElementException("index out of bounds");
		E e = null;

		if (i == 0) {
			e = first.element;
			if (first == last)
				last = null;
			first = first.next;
		} else {
			Node<E> prev = node(i - 1);
			Node<E> curr = prev.next;
			e = curr.element;
			prev.next = curr.next;
			if (curr == last)
				last = prev;
		}
		length--;
		return e;
	}

	@Override
	public E set(int i, E e) {
		if (i < 0 || i > length)
			throw new NoSuchElementException("index out of bounds");
		Node<E> curr = node(i);
		E e2 = curr.element;
		curr.element = e;
		return e2;
	}

	@Override
	public E get(int i) {
		if (i < 0 || i > length)
			throw new NoSuchElementException("index out of bounds");
		return node(i).element;
	}

	@Override
	public void addAll(MyList<E> list) {
		LinkedList<E> linkedList = (LinkedList<E>) list;
		for (Node<E> curr = linkedList.first; curr != null; curr = curr.next)
			add(curr.element);
	}

	@Override
	public MyIterator<E> iterator() {

		return new Itr();
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
	}

	private class Itr implements MyIterator<E> {

		Node<E> curr;
		Node<E> prev;

		public Itr() {
			curr = first;
			prev = null;
		}

		@Override
		public boolean hasNext() {
			return curr != null;
		}

		@Override
		public E next() {
			if (curr == null)
				throw new NoSuchElementException("index out of bounds..");
			E e = curr.element;
			prev = curr;
			curr = curr.next;
			return e;
		}

		@Override
		public void remove() {
			if (prev == null)
				throw new IllegalStateException("no such element exists");
			prev = curr;
			if (curr.next == null)
				last = curr;
			prev = null;
		}
	}
}
