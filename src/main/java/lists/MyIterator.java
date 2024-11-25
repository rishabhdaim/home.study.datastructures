package lists;

/**
 * @author ANKIT DAIM
 * 
 * @param <E>
 */
public interface MyIterator<E> {

	/**
	 * @return
	 */
	boolean hasNext();

	/**
	 * @return
	 */
	E next();

	/**
	 * @return
	 */
	void remove();
}
