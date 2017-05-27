package queues;

//1. It must be possible to make a queue empty.
//2. It must be possible to test whether a queue is empty.
//3. It must be possible to obtain the length of a queue.
//4. It must be possible to add an element at the rear of a queue.
//5. It must be possible to remove the element at the front of a queue.
//6. It must be possible to access the element at the front of a queue without removing it.
/**
 * @author ANKIT DAIM
 * 
 * @param <E>
 */
public interface Queue<E> {

	/**
	 * to clear the queue..
	 */
	void clear();

	/**
	 * @return return true if and only if queue is empty..
	 */
	boolean isEmpty();

	/**
	 * to add element at the end of the queue..
	 * 
	 * @param e
	 *            element to be added
	 */
	void addLast(E e);

	/**
	 * to remove and obtain element at the top of the queue..
	 * 
	 * @return element at top of this queue..
	 */
	E removeFirst();

	/**
	 * to obtain element at the top of the queue without removing it..
	 * 
	 * @return element at top of the queue..
	 */
	E getFirst();

	/**
	 * @return size of this queue..
	 */
	int size();

}
