package maps;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author ANKIT DAIM
 * 
 * @param <K>
 * @param <V>
 */
public class HashMap<K, V> implements Map<K, V> {

	/**
	 * to store entries inside this HashMap..
	 */
	Entry<K, V>[] entries;
	/**
	 * size of this HashMap i.e. total no. of entries..
	 */
	int size;
	/**
	 * limit of this HashMap after which resizing should be done..
	 */
	int threshHold;
	/**
	 * load Factor for this HashMap..
	 */
	final float loadFactor;
	private static final int DEFAULT_INITIAL_CAPACITY = 16;
	private static final float DEFAULT_INITIAL_LOAD_FACTOR = 0.75f;

	/**
	 * creates hashMap with defaultSize and loadFactor..
	 */
	public HashMap() {
		this(DEFAULT_INITIAL_CAPACITY, DEFAULT_INITIAL_LOAD_FACTOR);
	}

	@SuppressWarnings("unchecked")
	public HashMap(int capacity, float loadFactor) {
		this.loadFactor = loadFactor;
		this.threshHold = (int) (capacity * loadFactor);
		this.size = 0;
		entries = new Entry[capacity];
		init();
	}

	void init() {
	}

	@Override
	public void clear() {
		for (int i = 0, l = entries.length; i < l; i++)
			entries[i] = null;
		size = 0;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public V get(K key) {
		return getEntry(key).getValue();
	}

	@Override
	public V put(K key, V value) {
		// check for resize..
		int index = indexFor(key.hashCode(), entries.length);
		int hash = key.hashCode();
		for (Entry<K, V> e = entries[index]; e != null; e = e.next) {
			K k;
			if (e.hash == hash && ((k = e.key) == key || key.equals(k))) {
				V oldValue = replace(key, e.value, value);
				e.recordAccess(this);
				return oldValue;
			}
		}
		addEntry(hash, key, value, index);
		return null;
	}

	@Override
	public V replace(K key, V oldValue, V newValue) {
		V temp = oldValue;
		oldValue = newValue;
		return temp;
	}

	@Override
	public V remove(K key) {
		Entry<K, V> e = removeEntry(key);
		return e == null ? null : e.value;
	}

	@Override
	public boolean containsKey(K key) {
		return getEntry(key) != null;
	}

	@Override
	public boolean containsValue(V value) {
		Entry<K, V>[] temp = entries;
		for (int i = 0, l = temp.length; i < l; i++)
			for (Entry<K, V> e = temp[i]; e != null; e = e.next)
				if (value.equals(e.value))
					return true;
		return false;
	}

	/**
	 * @return
	 */
	public Iterator<V> valueIterator() {
		return new ValueIterator();
	}

	/**
	 * @return
	 */
	public Iterator<K> keyIterator() {
		return new KeyIterator();
	}

	/**
	 * @return
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
	static class Entry<K, V> implements Map.Entry<K, V> {

		Entry<K, V> next;
		K key;
		V value;
		int hash;

		/**
		 * @param next
		 * @param key
		 * @param value
		 * @param hash
		 */
		public Entry(Entry<K, V> next, K key, V value, int hash) {
			super();
			this.next = next;
			this.key = key;
			this.value = value;
			this.hash = hash;
		}

		@Override
		public V getValue() {
			return this.value;
		}

		@Override
		public K getKey() {
			return this.key;
		}

		@Override
		public V setValue(V newValue) {
			V oldValue = this.value;
			this.value = newValue;
			return oldValue;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((key == null) ? 0 : key.hashCode());
			result = prime * result + ((value == null) ? 0 : value.hashCode());
			return result;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (getClass() != obj.getClass()) {
				return false;
			}
			Entry<?, ?> other = (Entry<?, ?>) obj;
			if (key == null) {
				if (other.key != null) {
					return false;
				}
			} else if (!key.equals(other.key)) {
				return false;
			}
			if (value == null) {
				if (other.value != null) {
					return false;
				}
			} else if (!value.equals(other.value)) {
				return false;
			}
			return true;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "Entry [key=" + key + ", value=" + value + ", hashCode="
					+ hash + "]";
		}

		/**
		 * This method is invoked whenever the value in an entry is overwritten
		 * by an invocation of put(k,v) for a key k that's already in the
		 * HashMap.
		 */
		void recordAccess(HashMap<K, V> m) {
		}

		/**
		 * This method is invoked whenever the entry is removed from the table.
		 */
		void recordRemoval(HashMap<K, V> m) {
		}
	}

	// helper methods..

	/**
	 * this method is used to find the index for current key in this HashMap..
	 * 
	 * @param hash
	 *            whose index had to be found
	 * @param length
	 * @return index for current key in this HashMap
	 */
	int indexFor(int hash, int length) {
		return Math.abs(hash & length - 1);
	}

	/**
	 * this method is used to resize this hashMap once it reaches its threshHold
	 * value..
	 * 
	 * @param newCapacity
	 */
	void resize(int newCapacity) {
		@SuppressWarnings("unchecked")
		Entry<K, V>[] newEntry = new Entry[newCapacity];
		transfer(newEntry);
		entries = newEntry;
		this.threshHold = (int) (newCapacity * this.loadFactor);
	}

	/**
	 * @param newEntry
	 *            new Entry[] to which all previous values had to be moved..
	 */
	void transfer(Entry<K, V>[] newEntry) {
		int newCapacity = newEntry.length;
		for (Entry<K, V> e : entries) {
			while (e != null) {
				Entry<K, V> next = e.next;
				int index = indexFor(e.hash, newCapacity);
				e.next = newEntry[index];
				newEntry[index] = e;
				e = next;
			}
		}
	}

	/**
	 * @param hash
	 * @param key
	 * @param value
	 * @param index
	 */
	void addEntry(int hash, K key, V value, int index) {
		if ((size >= this.threshHold) && (null != entries[index])) {
			resize(2 * entries.length);
			hash = (null != key) ? key.hashCode() : 0;
			index = indexFor(key.hashCode(), entries.length);
		}
		createEntry(hash, key, value, index);
	}

	/**
	 * adds the new entry to front of this Entry<K,V>..
	 * 
	 * @param hash
	 * @param key
	 * @param value
	 * @param index
	 */
	void createEntry(int hash, K key, V value, int index) {
		Entry<K, V> e = entries[index];
		entries[index] = new Entry<>(e, key, value, hash);
		size++;
	}

	/**
	 * @param key
	 * @return
	 */
	final Entry<K, V> getEntry(K key) {
		int hash = key.hashCode();
		int index = indexFor(hash, entries.length);
		K k;
		for (Entry<K, V> e = entries[index]; e != null; e = e.next)
			if (e.hash == hash && ((k = e.key) == key || key.equals(k)))
				return e;
		return null;
	}

	/**
	 * @param key
	 * @return
	 */
	final Entry<K, V> removeEntry(K key) {
		int hash = (null == key) ? 0 : key.hashCode();
		int index = indexFor(hash, entries.length);
		Entry<K, V> prev = null;
		for (Entry<K, V> e = entries[index]; e != null; e = e.next) {
			K k;
			if (e.hash == hash
					&& ((k = e.key) == key || (key != null && key.equals(k)))) {
				if (prev == null)
					entries[index] = e.next;
				else
					prev.next = e.next;
				e.recordRemoval(this);
				size--;
				return e;
			}
			prev = e;
		}
		return null;
	}

	/**
	 * @author ANKIT DAIM
	 * 
	 * @param <E>
	 */
	private abstract class HashIterator<E> implements Iterator<E> {
		Entry<K, V> next;
		Entry<K, V> curr;
		int index;

		public HashIterator() {
			if (size > 0) {
				Entry<K, V>[] e = entries;
				// to reach first non-null element..
				while (index < e.length && (next = e[index++]) == null)
					;
			}
		}

		@Override
		public boolean hasNext() {
			return next != null;
		}

		final Entry<K, V> nextEntry() {
			Entry<K, V> e = next;
			if (e == null)
				throw new NoSuchElementException();
			if ((next = e.next) != null) {
				Entry<K, V>[] es = entries;
				while ((index < es.length) && (next = es[index++]) == null)
					;
			}
			curr = e;
			return curr;
		}

		@Override
		public void remove() {
			if (curr == null)
				throw new IllegalStateException();
			HashMap.this.removeEntry(curr.key);
			curr = null;
		}
	}

	/**
	 * @author ANKIT DAIM
	 * 
	 */
	private class EntryIterator extends HashIterator<Map.Entry<K, V>> {

		@Override
		public Entry<K, V> next() {
			return nextEntry();
		}
	}

	/**
	 * @author ANKIT DAIM
	 * 
	 */
	private class KeyIterator extends HashIterator<K> {

		@Override
		public K next() {
			return nextEntry().key;
		}
	}

	/**
	 * @author ANKIT DAIM
	 * 
	 */
	private class ValueIterator extends HashIterator<V> {
		@Override
		public V next() {
			return nextEntry().value;
		}
	}
}