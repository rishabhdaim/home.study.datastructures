package linkedlists;

public class SLLNode<E> {

	// Each SLLNode object is an SLL node.
	// This node consists of an element (element) and a link to its
	// successor (succ).

	E element;
	SLLNode<E> succ;

	/**
	 * @param element
	 * @param succ
	 */
	public SLLNode(E element, SLLNode<E> succ) {
		// Construct an SLL node with element elem and successor succ.
		super();
		this.element = element;
		this.succ = succ;
	}

	/**
	 * @return the element
	 */
	public E getElement() {
		return element;
	}

	/**
	 * @return the succ
	 */
	public SLLNode<E> getSucc() {
		return succ;
	}

	/**
	 * @param element
	 *            the element to set
	 */
	public void setElement(E element) {
		this.element = element;
	}

	/**
	 * @param succ
	 *            the succ to set
	 */
	public void setSucc(SLLNode<E> succ) {
		this.succ = succ;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SLLNode [element=" + element + "]";
	}

}