package maps;

//1. It must be possible to make a map empty.
//2. It must be possible to test whether a map is empty.
//3. It must be possible to test whether a map contains an entry with a given key.
//4. It must be possible to look up the value corresponding to a given key in a map.
//5. It must be possible to add a new entry to a map or to replace an existing entry.
//6. It must be possible to remove an entry from a map, given its key.
//7. It must be possible to test whether two maps are equal.
//8. It must be possible to compute the overlay of two maps.
//9. It must be possible to traverse a map.

/**
 * 
 * Each Map object is a map in which the keys and values are arbitrary objects.
 * 
 * @author ANKIT DAIM
 * 
 * @param <K>
 * @param <V>
 */
public interface Map<K, V> {

	/**
	 * 
	 */
	void clear();

	/**
	 * @return Return true if and only if this map is empty.
	 */
	boolean isEmpty();

	/**
	 * @return Return the cardinality of this map, i.e., the number of entries.
	 */
	int size();

	/**
	 * @param key
	 * @return
	 */
	boolean containsKey(K key);

	/**
	 * @param value
	 * @return
	 */
	boolean containsValue(V value);

	/**
	 * @param key
	 * @return
	 */
	V get(K key);

	/**
	 * @param key
	 * @param value
	 * @return
	 */
	V put(K key, V value);

	/**
	 * @param key
	 * @param oldValue
	 * @param newValue
	 * @return
	 */
	V replace(K key, V oldValue, V newValue);

	/**
	 * @param key
	 * @return
	 */
	V remove(K key);

	/**
	 * @author ANKIT DAIM
	 * 
	 * @param <K>
	 * @param <V>
	 */
	public interface Entry<K, V> {
		/**
		 * @return
		 */
		V getValue();

		/**
		 * @return
		 */
		K getKey();

		/**
		 * @param newValue
		 * @return
		 */
		V setValue(V newValue);

		/**
		 * @param o
		 * @return
		 */
		boolean equals(Object o);

		/**
		 * @return
		 */
		int hashCode();
	}
}