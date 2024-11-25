package linkedlists;

/**
 * @author ANKIT DAIM
 * 
 */
public class DLL<E> {

	DLLNode<E> header;
	DLLNode<E> last;

	/**
	 * 
	 */
	public DLL() {
		this.header = null;
		this.last = null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DLL [first=" + header + ", last=" + last + "]";
	}


	/**
	 * @return the first
	 */
	public DLLNode<E> getFirst() {
		return header;
	}

	/**
	 * @return the last
	 */
	public DLLNode<E> getLast() {
		return last;
	}

	/**
	 * @param first
	 *            the first to set
	 */
	public void setFirst(DLLNode<E> first) {
		this.header = first;
	}

	/**
	 * @param last
	 *            the last to set
	 */
	public void setLast(DLLNode<E> last) {
		this.last = last;
	}

	/**
	 * 
	 */
	public void printFirstToLast() {
		for (DLLNode<E> curr = header; curr != null; curr = curr.succ)
			System.out.print(curr.element + " ");
		System.out.println();
	}

	public void printLastToFirst() {
		for (DLLNode<E> curr = last; curr != null; curr = curr.pred)
			System.out.print(curr.element + " ");
		System.out.println();
	}

	/**
	 * Returns the size of this LinkedList..
	 */
	private int size;

	/**
	 * @return size of this linked list
	 */
	public int size() {
		for (DLLNode<E> curr = header; curr != null; curr = curr.succ)
			size++;
		return size;
	}
}