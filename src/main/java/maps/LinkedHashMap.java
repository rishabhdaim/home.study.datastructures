package maps;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedHashMap<K, V> extends HashMap<K, V> {

	/**
	 * to create cache from this linked HashMap..
	 */
	private final boolean accessOrder;

	/**
	 * head of doubly link list..
	 */
	private Entry<K, V> header;

	/**
	 * 
	 */
	public LinkedHashMap() {
		super();
		accessOrder = false;
	}

	/**
	 * @param capacity
	 * @param loadFactor
	 */
	public LinkedHashMap(int capacity, float loadFactor) {
		super(capacity, loadFactor);
		accessOrder = false;
	}

	/**
	 * @param capacity
	 * @param loadFactor
	 * @param accessOrder
	 */
	public LinkedHashMap(int capacity, float loadFactor, boolean accessOrder) {
		super(capacity, loadFactor);
		this.accessOrder = accessOrder;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see maps.HashMap#init()
	 */
	@Override
	void init() {
		header = new Entry<>(null, null, null, -1);
		header.after = header.before = header;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see maps.HashMap#get(java.lang.Object)
	 */
	@Override
	public V get(K key) {
		Entry<K, V> e = (Entry<K, V>) getEntry(key);
		if (e == null)
			return null;
		e.recordAccess(this);
		return e.value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see maps.HashMap#containsValue(java.lang.Object)
	 */
	@Override
	public boolean containsValue(V value) {
		if (value == null) {
			for (Entry<K, V> e = header.after; e != header; e = e.after)
				if (e.value == null)
					return true;
		} else
			for (Entry<K, V> e = header.after; e != header; e = e.after)
				if (value.equals(e.value))
					return true;
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see maps.HashMap#clear()
	 */
	@Override
	public void clear() {
		super.clear();
		header.after = header.before = header;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see maps.HashMap#addEntry(int, java.lang.Object, java.lang.Object, int)
	 */
	@Override
	void addEntry(int hash, K key, V value, int index) {
		super.addEntry(hash, key, value, index);

		// Remove eldest entry if instructed
		Entry<K, V> last = header.after;
		if (removeEldestEntry(last))
			removeEntry(last.key);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see maps.HashMap#createEntry(int, java.lang.Object, java.lang.Object,
	 * int)
	 */
	@Override
	void createEntry(int hash, K key, V value, int index) {
		HashMap.Entry<K, V> entry = entries[index];
		Entry<K, V> e = new Entry<>(entry, key, value, hash);
		entries[index] = e;
		e.addBefore(header);
		size++;
	}

	/**
	 * @param last
	 * @return
	 */
	private boolean removeEldestEntry(Map.Entry<K, V> last) {
		return false;
	}

	@Override
	void transfer(maps.HashMap.Entry<K, V>[] newEntry) {
		int newCapacity = newEntry.length;

		for (Entry<K, V> e = header.after; e != header; e = e.after) {
			int index = indexFor(e.hash, newCapacity);
			e.next = newEntry[index];
			newEntry[index] = e;
		}
	}

	/**
	 * @author ANKIT DAIM
	 * 
	 * @param <E>
	 */
	private abstract class LinkedHashItr<E> implements Iterator<E> {
		Entry<K, V> next = header.after;
		Entry<K, V> lastReturned = null;

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Iterator#hasNext()
		 */
		@Override
		public boolean hasNext() {
			return next != header;
		}

		/**
		 * @return
		 */
		final Entry<K, V> nextEntry() {
			if (next == header)
				throw new NoSuchElementException();
			Entry<K, V> e = lastReturned = next;
			next = next.after;
			return e;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Iterator#remove()
		 */
		@Override
		public void remove() {
			if (lastReturned == null)
				throw new IllegalStateException();
			LinkedHashMap.this.removeEntry(lastReturned.key);
			lastReturned = null;
		}
	}

	/**
	 * @author ANKIT DAIM
	 * 
	 */
	private class EntryIterator extends LinkedHashItr<Map.Entry<K, V>> {

		@Override
		public Map.Entry<K, V> next() {
			return nextEntry();
		}
	}

	/**
	 * @author ANKIT DAIM
	 * 
	 */
	private class KeyIterator extends LinkedHashItr<K> {

		@Override
		public K next() {
			return nextEntry().key;
		}
	}

	/**
	 * @author ANKIT DAIM
	 * 
	 */
	private class ValueIterator extends LinkedHashItr<V> {
		@Override
		public V next() {
			return nextEntry().value;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see maps.HashMap#keyIterator()
	 */
	public Iterator<K> keyIterator() {
		return new KeyIterator();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see maps.HashMap#valueIterator()
	 */
	public Iterator<V> valueIterator() {
		return new ValueIterator();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see maps.HashMap#entryIterator()
	 */
	public Iterator<Map.Entry<K, V>> entryIterator() {
		return new EntryIterator();
	}

	/**
	 * @author ANKIT DAIM
	 * 
	 * @param <K>
	 * @param <V>
	 */
	static class Entry<K, V> extends HashMap.Entry<K, V> {

		// to use this doubly list..
		Entry<K, V> before;
		Entry<K, V> after;

		/**
		 * @param next
		 * @param key
		 * @param value
		 * @param hash
		 */
		public Entry(maps.HashMap.Entry<K, V> next, K key, V value, int hash) {
			super(next, key, value, hash);
		}

		/**
		 * This method is invoked whenever the value in an entry is overwritten
		 * by an invocation of put(k,v) for a key k that's already in the
		 * HashMap.
		 */
		void recordAccess(HashMap<K, V> m) {
			LinkedHashMap<K, V> map = (LinkedHashMap<K, V>) m;
			if (map.accessOrder) {
				remove();
				addBefore(map.header);
			}
		}

		/**
		 * This method is invoked whenever the entry is removed from the table.
		 */
		void recordRemoval(HashMap<K, V> m) {
			remove();
		}

		/**
		 * to remove this entry..
		 */
		private void remove() {
			this.before.after = this.after;
			this.after.before = this.before;
		}

		/**
		 * @param prevEntry
		 */
		private void addBefore(Entry<K, V> prevEntry) {
			this.after = prevEntry;
			this.before = prevEntry.before;
			prevEntry.before.after = this;
			prevEntry.before = this;
		}
	}
}