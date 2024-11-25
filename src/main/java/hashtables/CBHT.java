package hashtables;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class CBHT<K, V> {

	private Entry<K, V>[] table;
	private int size = 0;
	private final int capacity = 20;

	@SuppressWarnings("unchecked")
	public CBHT() {
		table = new Entry[capacity];
	}

	@SuppressWarnings("unchecked")
	public CBHT(int capacity) {
		table = new Entry[capacity];
	}

	// CHBT methods..

	// To find which if any node of a CBHT contains an entry whose key is equal
	// to targetkey:
	// 1. Set b to hash(target-key).
	// 2. Find which if any node of the SLL of bucket b contains an entry whose
	// key is equal to target-key, and terminate with that node as answer..

	/**
	 * @param key
	 *            key to be found
	 * @return Entry<K,V> with key equal to input parameter, or null if input
	 *         key is not present in this table..
	 */
	public Entry<K, V> getEntry(K key) {
		int hash = key.hashCode();
		int index = indexFor(key);

		for (Entry<K, V> e = table[index]; e != null; e = e.next)
			if (e.hash == hash
					&& (e.key == key || (key != null && key.equals(e.key))))
				return e;
		return null;
	}

	// To insert the entry <key, val> into a CBHT:
	// 1. Set b to hash(key).
	// 2. Insert the entry <key, val> into the SLL of bucket b, replacing any
	// existing entry with key.
	// 3. Terminate.

	/**
	 * @param key
	 * @param value
	 * @return
	 */
	public V put(K key, V value) {
		int hash = key.hashCode();
		int index = indexFor(key);

		// check if same key exists..

		for (Entry<K, V> e = table[index]; e != null; e = e.next) {
			if (e.hash == hash && (e.key == key || key.equals(e.key))) {
				V oldValue = e.value;
				e.value = value;
				return oldValue;
			}
		}

		// if key doesn't exists..

		addEntry(key, value, index, hash);
		size++;
		return null;
	}

	/**
	 * @param key
	 * @param value
	 * @param index
	 * @param hash
	 */
	private void addEntry(K key, V value, int index, int hash) {
		Entry<K, V> e = table[index];
		table[index] = new Entry<K, V>(key, value, e, hash);
	}

	// To delete the entry (if any) whose key is equal to key from a CBHT:
	// 1. Set b to hash(key).
	// 2. Delete the entry (if any) whose key is equal to key from the SLL of
	// bucket b.
	// 3. Terminate.

	/**
	 * @param key
	 * @return
	 */
	public Entry<K, V> removeEntry(K key) {
		int hash = key.hashCode();
		int index = indexFor(key);
		Entry<K, V> prev = table[index];
		Entry<K, V> e = prev;

		while (e != null) {
			Entry<K, V> next = e.next;
			if (e.hash == hash
					&& (e.key == key || (key != null && key.equals(e.key)))) {
				size--;
				if (prev == e)
					table[index] = next;
				else
					prev.next = next;
				return e;
			}
			prev = e;
			e = next;
		}
		return null;
	}

	/*
	 * Internal utilities..
	 */
	// Translate key to an index of the array bucket s, such that
	// x.equals (y) implies hash (x) =hash(y).
	/**
	 * @param key
	 *            key for which index had to be found..
	 * @return index for this current key in Entry[]..
	 */
	private int indexFor(K key) {
		return Math.abs(key.hashCode()) & table.length - 1;
	}

	static class Entry<K, V> {
		K key;
		V value;
		Entry<K, V> next;
		int hash;

		/**
		 * @param key
		 * @param value
		 * @param next
		 * @param h
		 */
		public Entry(K key, V value, Entry<K, V> next, int h) {
			super();
			this.key = key;
			this.value = value;
			this.next = next;
			this.hash = h;
		}

		/**
		 * @return the key
		 */
		public K getKey() {
			return key;
		}

		/**
		 * @return the value
		 */
		public V getValue() {
			return value;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "Entry [key=" + key + ", value=" + value + "] ";
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append("{");
		for (Entry<K, V> entry : table)
			for (Entry<K, V> e = entry; e != null; e = e.next)
				sb.append(e.toString());
		sb.append("}");

		return "CBHT [size=" + size + " : " + sb.toString() + "]";
	}

	private abstract class CBHTIterator<E> implements Iterator<E> {

		private Entry<K, V> next;
		private Entry<K, V> curr;
		int index;

		public CBHTIterator() {
			// advance to first entry..

			if (size > 0) {
				Entry<K, V>[] e = table;

				while (index < e.length && (next = e[index++]) == null)
					;
			}
		}

		@Override
		public final boolean hasNext() {
			return next != null;
		}

		final Entry<K, V> nextEntry() {
			Entry<K, V> e = next;
			if (e == null)
				throw new NoSuchElementException();

			if ((next = e.next) == null) {
				// advance to next non-empty entry..
				Entry<K, V>[] t = table;
				while (index < t.length && (next = t[index++]) == null)
					;
			}
			curr = e;
			return e;
		}

		@Override
		public void remove() {
			if (curr == null)
				throw new IllegalStateException("");
			K k = curr.key;
			curr = null;
			CBHT.this.removeEntry(k);
		}
	}

	private final class ValueIterator extends CBHTIterator<V> {
		@Override
		public V next() {
			return nextEntry().value;
		}

	}

	private final class KeyIterator extends CBHTIterator<K> {
		@Override
		public K next() {
			return nextEntry().key;
		}

	}

	private final class EntryIterator extends CBHTIterator<CBHT.Entry<K, V>> {
		@Override
		public CBHT.Entry<K, V> next() {
			return nextEntry();
		}
	}

	// Subclass overrides these to alter behaviour of views' iterator() method
	Iterator<K> newKeyIterator() {
		return new KeyIterator();
	}

	Iterator<V> newValueIterator() {
		return new ValueIterator();
	}

	Iterator<CBHT.Entry<K, V>> newEntryIterator() {
		return new EntryIterator();
	}
}
