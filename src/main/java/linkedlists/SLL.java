package linkedlists;

public class SLL<E> {
	// Each SLL object is an SLL header.
	// This SLL is represented by a reference to its first node (first).
	public SLLNode<E> header;

	public SLL() {
		// Construct an empty SLL.
		this.header = null;
		// SLL methods (see below).
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SLL [first=" + header + "]";
	}

	/**
	 * Print all elements of this list..
	 */
	public void printFirstToLast() {
		for (SLLNode<E> curr = header; curr != null; curr = curr.succ)
			System.out.print(curr.element + " ");
		System.out.println();
	}

	/**
	 * size of this linked list..
	 */
	private int size;

	/**
	 * @return size of this linked list..
	 */
	public int size() {
		for (SLLNode<E> curr = header; curr != null; curr = curr.succ)
			size++;
		return size;
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
		result = prime * result + ((header == null) ? 0 : header.hashCode());
		result = prime * result + size;
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
		@SuppressWarnings("unchecked")
		SLL<E> other = (SLL<E>) obj;
		if (size != other.size)
			return false;

		for (SLLNode<E> curr = header, objCurr = other.header; curr != null
				&& objCurr != null; curr = curr.succ, objCurr = objCurr.succ) {
			if (!(curr.element == null ? objCurr.element == null : curr.element
					.equals(objCurr.element)))
				return false;
		}

		return true;
	}
}