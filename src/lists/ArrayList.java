package lists;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * @author ANKIT DAIM
 * 
 * @param <E>
 * 
 */
public class ArrayList<E> implements MyList<E> {

	private E[] elem;
	private int length;

	@SuppressWarnings("unchecked")
	public ArrayList() {
		elem = (E[]) new Object[10];
		length = 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ArrayList [elem=" + Arrays.toString(elem) + ", length="
				+ length + "]";
	}

	/**
	 * @see lists.MyList#clear()
	 */
	@Override
	public void clear() {
		for (int i = 0; i < length; i++)
			elem[i] = null;
		length = 0;
	}

	@Override
	public boolean isEmpty() {
		return length == 0;
	}

	@Override
	public int size() {
		return length;
	}

	@Override
	public void add(int i, E e) {
		if (i < 0 || i >= length)
			throw new IndexOutOfBoundsException();
		if (length == elem.length)
			resize();
		for (int j = length; j > i; j--)
			elem[j] = elem[j - 1];
		elem[i] = e;
		length++;
	}

	@Override
	public void add(E e) {
		if (length == elem.length)
			resize();
		elem[length++] = e;
	}

	/**
	 * to resize this list..
	 */
	private void resize() {
		@SuppressWarnings("unchecked")
		E[] e = (E[]) new Object[2 * elem.length];
		for (int i = 0; i < length; i++)
			e[i] = elem[i];
		elem = e;
	}

	@Override
	public E remove(int i) {
		if (i < 0 || i >= length)
			throw new IndexOutOfBoundsException();
		E e = elem[i];
		for (int j = i + 1; j < length; j++)
			elem[j - 1] = elem[j];
		length--;
		elem[length] = null;
		return e;
	}

	@Override
	public E set(int i, E e) {
		if (i < 0 || i >= length)
			throw new IndexOutOfBoundsException();
		E temp = elem[i];
		elem[i] = e;
		return temp;
	}

	@Override
	public E get(int i) {
		if (i < 0 || i >= length)
			throw new IndexOutOfBoundsException();
		return elem[i];
	}

	@Override
	public void addAll(MyList<E> list) {
		ArrayList<E> e = (ArrayList<E>) list;
		for (int i = 0, l = list.size(); i < l; i++)
			add(e.elem[i]);
	}

	@Override
	public MyIterator<E> iterator() {
		return new ListIterator();
	}

	/**
	 * @author ANKIT DAIM
	 * 
	 * @param <E>
	 */
	private class ListIterator implements MyIterator<E> {

		private int index;
		private int lastRef = -1;

		public ListIterator() {
			index = 0;
		}

		@Override
		public boolean hasNext() {
			return index < length;
		}

		@Override
		public E next() {
			if (index >= length)
				throw new NoSuchElementException();
			lastRef = index;
			return (E) elem[index++];
		}

		@Override
		public void remove() {
			if (lastRef == -1)
				throw new IllegalStateException();
			ArrayList.this.remove(lastRef);
			lastRef = -1;
			index--;
		}
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
		result = prime * result + Arrays.hashCode(elem);
		result = prime * result + length;
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
		ArrayList<?> other = (ArrayList<?>) obj;
		if (length != other.length) {
			return false;
		}
		if (!Arrays.equals(elem, other.elem)) {
			return false;
		}
		return true;
	}
}
