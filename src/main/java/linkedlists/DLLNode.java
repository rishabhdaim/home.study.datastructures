package linkedlists;

public class DLLNode<E> {

	DLLNode<E> succ;
	DLLNode<E> pred;
	E element;

	/**
	 * @param succ
	 * @param pred
	 * @param element
	 */
	public DLLNode(DLLNode<E> succ, DLLNode<E> pred, E element) {
		super();
		this.succ = succ;
		this.pred = pred;
		this.element = element;
	}

	/**
	 * @return the succ
	 */
	public DLLNode<E> getSucc() {
		return succ;
	}

	/**
	 * @return the pred
	 */
	public DLLNode<E> getPred() {
		return pred;
	}

	/**
	 * @return the element
	 */
	public Object getElement() {
		return element;
	}

	/**
	 * @param succ
	 *            the succ to set
	 */
	public void setSucc(DLLNode<E> succ) {
		this.succ = succ;
	}

	/**
	 * @param pred
	 *            the pred to set
	 */
	public void setPred(DLLNode<E> pred) {
		this.pred = pred;
	}

	/**
	 * @param element
	 *            the element to set
	 */
	public void setElement(E element) {
		this.element = element;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DLLNode [element=" + element + "]";
	}
}