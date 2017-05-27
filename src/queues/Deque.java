package queues;

public interface Deque<E> extends Queue<E> {

	/**
	 * add value to first..
	 * 
	 * @param e
	 *            value to be added..
	 */
	void addFirst(E e);

	/**
	 * remove and return the last value..
	 * 
	 * @return value from last
	 */
	E removeLast();

	/**
	 * @return last element of this deque without removing it
	 */
	E getLast();
}
