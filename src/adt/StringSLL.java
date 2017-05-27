package adt;

import linkedlists.SLLNode;

public class StringSLL {

	private SLLNode<Character> node = new SLLNode<Character>(null, null);
	private SLLNode<Character> last;
	private int length;

	public StringSLL(char[] cs) {
		super();
		addAll(node, cs);
		this.length = cs.length;
	}

	public StringSLL() {
		this.node = this.last = null;
	}
	
	public StringSLL(StringSLL stringSLL) {
		this();
		last = stringSLL.last;
		node = stringSLL.node;
	}
	
	/**
	 * @param node
	 * @param cs
	 */
	private void addAll(SLLNode<Character> node, char[] cs) {

		for (int i = 0; i < cs.length; i++) {
			SLLNode<Character> sllNode = new SLLNode<Character>(cs[i], null);
			node.setSucc(sllNode);
			node = sllNode;
		}
		last = node;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StringSLL [node=" + node.getElement() + ", length=" + length
				+ "]";
	}

	/**
	 * Print all elements of this list..
	 */
	public void printFirstToLast() {
		for (SLLNode<Character> curr = node.getSucc(); curr != null; curr = curr
				.getSucc())
			System.out.print(curr.getElement() + " ");
		System.out.println();
	}

	public static void main(String[] args) {
		char[] cs = { 'a', 'b', 'c',};
		char[] cs1 = { 'r', 'q', 's',};

		StringSLL stringSLL = new StringSLL(cs);
		stringSLL.printFirstToLast();
		StringSLL stringSLL2 = new StringSLL(cs1);
		stringSLL.concat(stringSLL2);
		stringSLL.printFirstToLast();
	}

	public void concat(StringSLL stringSLL) {
		
		StringSLL temp = new StringSLL(stringSLL);
		
		last.setSucc(temp.node.getSucc());
		last = temp.last;
	}
}
