package sets;

import java.util.Iterator;

import maps.HashMap;
import maps.LinkedHashMap;

/**
 * @author ANKIT DAIM
 * 
 * @param <E>
 */
public class HashSet<E> implements Set<E> {

	private HashMap<E, Object> map;

	private static final Object VALUE = new Object();

	public HashSet() {
		map = new HashMap<>();
	}

	public HashSet(int initialCapacity, float loadFactor) {
		map = new HashMap<>(initialCapacity, loadFactor);
	}

	HashSet(int initialCapacity, float loadFactor, boolean accessOrder) {
		map = new LinkedHashMap<>(initialCapacity, loadFactor, accessOrder);
	}

	@Override
	public void clear() {
		map.clear();
	}

	@Override
	public int size() {
		return map.size();
	}

	@Override
	public boolean isEmpty() {
		return map.isEmpty();
	}

	@Override
	public boolean contains(E e) {
		return map.containsKey(e);
	}

	@Override
	public boolean add(E e) {
		return map.put(e, VALUE) == null;
	}

	@Override
	public boolean remove(E e) {
		return map.remove(e) == VALUE;
	}

	@Override
	public Iterator<E> iterator() {
		return map.keyIterator();
	}
}