package hashtables;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class OBHT<K, V> {

	private int size = 0;
	// bucket s [b ] is null 1 if bucket b has never been occupied. buckets [b]
	// is BucketEntry .FORMER if bucket b is formerly-occupied by an entry that
	// has since been deleted (and not yet replaced).
	private Entry<K, V>[] table;
	private int threshold;

	// some constants..

	static final float DEFAULT_LOAD_FACTOR = 0.75f;
	static final int DEFAULT_INITIAL_CAPACITY = 16;
	// ... distinct from any bucket index
	public static final int NONE = -1;

	public OBHT() {
		this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR);
	}

	@SuppressWarnings("unchecked")
	public OBHT(int capacity, float loadFactor) {
		this.threshold = (int) (capacity * loadFactor);
		table = new Entry[capacity];
	}

	/*
	 * OBHT methods..
	 */

	// To find which if any bucket of an OBHT is occupied by an entry whose key
	// is equal to target-key:
	// 1. Set b to hash(target-key).
	// 2. Repeat:
	// 2.1. If bucket b is never-occupied:
	// 2.1.1. Terminate with answer none.
	// 2.2. If bucket b is occupied by an entry whose key is equal to
	// target-key:
	// 2.2.1. Terminate with answer b.
	// 2.3. If bucket b is formerly-occupied, or is occupied by an entry whose
	// key is not equal to target-key:
	// 2.3.1. Increment b modulo m.

	public Entry<K, V> getEntry(K key) {
		int hash = key.hashCode();
		int index = indexFor(key, table.length);

		for (;;) {
			Entry<K, V> e = table[index];
			if (e == null)
				return null;
			if (e != Entry.FORMER)
				index++;
			K k;
			if (e.hash == hash
					&& ((k = e.key) == key || (key != null && key.equals(k))))
				return e;
			else
				index++;
		}
	}

	// To insert the entry <key, val> into an OBHT:
	// 1. Set b to hash(key).
	// 2. Repeat:
	// 2.1. If bucket b is never-occupied:
	// 2.1.1. If bucket b is the last never-occupied bucket:
	// 2.1.1.1. Expand the number of buckets.
	// 2.1.1.2. Set b to hash(key).
	// 2.1.1.3. Repeat from step 2.
	// 2.1.2. Make bucket b occupied by <key, value>.
	// 2.1.3. Terminate.
	// 2.2. If bucket b is formerly-occupied, or is occupied by an entry whose
	// key is equal to key:
	// 2.2.1. Make bucket b occupied by <key, value>.
	// 2.2.2. Terminate.
	// 2.3. If bucket b is occupied by an entry whose key is not equal to key:
	// 2.3.1. Increment b modulo m.

	public V put(K key, V value) {
		int hash = key.hashCode();
		int index = indexFor(key, table.length);
		Entry<K, V> newEntry = new Entry<>(key, value, hash);
		for (;;) {
			Entry<K, V> e = table[index];
			if (e == null) { // never occupied
				if (size >= this.threshold) {
					expand();
					index = indexFor(key, table.length);
					continue;
				}
				table[index] = newEntry;
				return value;
			} else if (e == Entry.FORMER) {
				table[index] = newEntry;
				return value;
			} else if (e.hash == hash
					&& (e.key == key || (key != null && key.equals(e.key)))) {
				V oldValue = e.value;
				e.value = value;
				return oldValue;
			} else
				index++;
		}
	}

	/**
	 * to resize this hashTable..
	 */
	private void expand() {
		Entry<K, V>[] oldTable = table;
		@SuppressWarnings("unchecked")
		Entry<K, V>[] newTable = new Entry[(oldTable.length * 2) + 1];

		for (Entry<K, V> e : table) {
			int index = indexFor(e.key, newTable.length);
			transfer(newTable, index, e);
		}
		table = newTable;
	}

	/**
	 * @param newTable
	 * @param index
	 * @param e
	 */
	private void transfer(Entry<K, V>[] newTable, int index, Entry<K, V> e) {
		Entry<K, V> entry = newTable[index];
		for (;;) {
			if (entry == null) {
				newTable[index] = e;
				return;
			} else
				index++;
		}
	}

	// To delete the entry (if any) whose key is equal to key from an OBHT:
	// 1. Set b to hash(key).
	// 2. Repeat:
	// 2.1. If bucket b is never-occupied:
	// 2.1.1. Terminate.
	// 2.2. If bucket b is occupied by an entry whose key is equal to key:
	// 2.2.1. Make bucket b formerly-occupied.
	// 2.2.2. Terminate.
	// 2.3. If bucket b is formerly-occupied, or is occupied by an entry whose
	// key is not equal to key:
	// 2.3.1. Increment b modulo m.

	/**
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Entry<K, V> removeEntry(K key) {
		int hash = key.hashCode();
		int index = indexFor(key, table.length);
		for (;;) {
			Entry<K, V> e = table[index];
			if (e == null)
				return null;
			else if (e == Entry.FORMER)
				index++;
			else if (e.hash == hash && (e.key == key || key.equals(e.key))) {
				size--;
				table[index] = Entry.FORMER;
				return e;
			} else
				index++;
		}
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
	private int indexFor(K key, int length) {
		return Math.abs(key.hashCode()) & length - 1;
	}

	static class Entry<K, V> {
		K key;
		V value;
		int hash;

		/**
		 * @param key
		 * @param value
		 */
		public Entry(K key, V value, int hash) {
			super();
			this.key = key;
			this.value = value;
			this.hash = hash;
		}

		@SuppressWarnings({ "rawtypes", "unchecked" })
		static final Entry FORMER = new Entry(null, null, 0);

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

		/**
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
			if (entry != null && entry != Entry.FORMER)
				sb.append(entry.toString());
		sb.append("}");

		return "CBHT [size=" + size + " : " + sb.toString() + "]";
	}

	private abstract class OBHTIterator<E> implements Iterator<E> {
		private Entry<K, V> next;
		private Entry<K, V> curr;
		int index;

		public OBHTIterator() {
			if (size > 0) {
				// advance for first entry..
				Entry<K, V>[] e = table;
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
			Entry<K, V>[] t = table;
			if ((next = t[index++]) == null)
				while (index < t.length && (next = t[index++]) == null)
					;
			curr = e;
			return e;
		}

		@Override
		public void remove() {
			if (curr == null)
				throw new IllegalStateException();
			K k = curr.key;
			curr = null;
			OBHT.this.removeEntry(k);
		}
	}

	private final class ValueIterator extends OBHTIterator<V> {
		@Override
		public V next() {
			return nextEntry().value;
		}

	}

	private final class KeyIterator extends OBHTIterator<K> {
		@Override
		public K next() {
			return nextEntry().key;
		}

	}

	private final class EntryIterator extends OBHTIterator<OBHT.Entry<K, V>> {
		@Override
		public OBHT.Entry<K, V> next() {
			return nextEntry();
		}
	}

	// Subclass overrides these to alter behavior of views' iterator() method
	Iterator<K> newKeyIterator() {
		return new KeyIterator();
	}

	Iterator<V> newValueIterator() {
		return new ValueIterator();
	}

	Iterator<OBHT.Entry<K, V>> newEntryIterator() {
		return new EntryIterator();
	}
}
