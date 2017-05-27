package queues;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * 
 * elems [front . . .rear — I],
 * 
 * @author ANKIT DAIM
 * 
 * @param <E>
 */
public class ArrayQueue<E> implements Queue<E> {

	private E[] element;
	private int length;
	private int front;
	private int rear;

	@SuppressWarnings("unchecked")
	public ArrayQueue() {
		element = (E[]) new Object[3];
		length = 0;
		front = 0;
		rear = 0;
	}

	@Override
	public void clear() {
		for (int i = 0, l = element.length; i < l; i++)
			element[i] = null;
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
	 * this method is used to resize the queue..
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ArrayQueue [element=" + Arrays.toString(element) + ", length="
				+ length + ", front=" + front + ", rear=" + rear + "]";
	}
}
