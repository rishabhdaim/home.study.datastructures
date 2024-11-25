package queues;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayDeque<E> implements Deque<E> {

	private E[] element;
	private int length;
	private int front;
	private int rear;

	@SuppressWarnings("unchecked")
	public ArrayDeque() {
		element = (E[]) new Object[4];
		length = front = rear = 0;
	}

	@Override
	public void clear() {
		for (int i = 0, l = element.length; l > i; l--)
			element[l] = null;
		length = front = rear = 0;
	}

	@Override
	public boolean isEmpty() {
		return length == 0;
	}

	@Override
	public void addLast(E e) {
		if (length == element.length)
			resize();
		element[rear++] = e;
		if (rear == element.length)
			rear = 0;
		length++;
	}

	/**
	 * to resize this deque..
	 */
	private void resize() {
		@SuppressWarnings("unchecked")
		E[] e = (E[]) new Object[2 * element.length];
		int j = 0;
		for (int i = front; i < element.length; i++)
			e[j++] = element[i];
		if (front >= rear) {
			for (int i = 0; i < rear; i++)
				e[j++] = element[i];
		}
		front = 0;
		rear = length;
		element = e;
	}

	@Override
	public E removeFirst() {
		if (length == 0)
			throw new NoSuchElementException("queue is empty..");
		E e = element[front];
		element[front++] = null;
		if (front == element.length)
			front = 0;
		length--;
		return e;
	}

	@Override
	public E getFirst() {
		if (length == 0)
			throw new NoSuchElementException("queue is empty..");
		return element[front];
	}

	@Override
	public int size() {
		return length;
	}

	@Override
	public void addFirst(E e) {
		if (length == element.length)
			resize();
		element[--front] = e;
		if (front == 0)
			front = element.length;
		length++;
	}

	@Override
	public E removeLast() {
		if (length == 0)
			throw new NoSuchElementException("queue is empty..");
		E e = element[rear];
		element[rear--] = null;
		if (rear <= 0)
			rear = element.length;
		length--;
		return e;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ArrayDeque [element=" + Arrays.toString(element) + ", length="
				+ length + ", front=" + front + ", rear=" + rear + "]";
	}

	@Override
	public E getLast() {
		if (length == 0)
			throw new NoSuchElementException("queue is empty..");
		return element[rear];
	}
}
