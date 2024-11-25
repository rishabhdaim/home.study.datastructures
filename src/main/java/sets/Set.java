package sets;

import java.util.Iterator;

public interface Set<E> {

	/**
	 * 
	 */
	void clear();

	/**
	 * @return
	 */
	int size();

	/**
	 * @return
	 */
	boolean isEmpty();

	/**
	 * @param e
	 * @return
	 */
	boolean contains(E e);

	/**
	 * @param e
	 * @return
	 */
	boolean add(E e);

	/**
	 * @param e
	 * @return
	 */
	boolean remove(E e);

	/**
	 * @return
	 */
	Iterator<E> iterator();
}
