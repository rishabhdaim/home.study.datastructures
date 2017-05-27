package lists;

//1. It must be possible to make a list empty.
//2. It must be possible to test whether a list is empty.
//3. It must be possible to obtain the length of a list.
//4. It must be possible to add an element anywhere in a list.
//5. It must be possible to remove an element anywhere in a list.
//6. It must be possible to inspect or update an element anywhere in a list.
//7. It must be possible to concatenate lists.
//8. It must be possible to test lists for equality.
//9. It must be possible to traverse a list (i.e., visit each list element in turn).

/**
 * @author ANKIT DAIM
 * 
 * @param <E>
 */
public interface MyList<E> {

	/**
	 * 
	 */
	void clear();

	/**
	 * @return
	 */
	boolean isEmpty();

	/**
	 * @return
	 */
	int size();

	/**
	 * @param i
	 * @param e
	 */
	void add(int i, E e);
	/**
	 * @param e
	 */
	void add(E e);

	/**
	 * @param i
	 * @return
	 */
	E remove(int i);

	/**
	 * @param i
	 * @param e
	 * @return
	 */
	E set(int i,E e);

	/**
	 * @param i
	 * @return
	 */
	E get(int i);

	/**
	 * @param list
	 */
	void addAll(MyList<E> list);
	
	/**
	 * @return
	 */
	MyIterator<E> iterator();
}