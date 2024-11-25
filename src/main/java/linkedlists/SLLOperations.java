package linkedlists;

public class SLLOperations {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SLL<String> sll = SLLUtil.createLinkedList();
		insertElement(sll, null, "insertedElement");

		deleteNode(sll, sll.header.succ);

		insertElement(sll, sll.header, "first");

		searchNode(sll, "first");

		mergeSSLL();
		insertionSortSLL();

		reverseList();
	}

	// (1) Insertion in an empty linked list.
	// (2) Insertion before the first node of a nonempty linked list.
	// (3) Insertion after the last node of a nonempty linked list.
	// (4) Insertion between nodes of a nonempty linked list.

	/**
	 * @param sll
	 * @param prevNode
	 * @param element
	 */
	private static <E> void insertElement(SLL<E> sll, SLLNode<E> prevNode,
			E element) {
		SLLUtil.insert(sll, prevNode, element);
		SLLUtil.printSLL(sll);
	}

	// (1) The node to be deleted is the only node of the linked list.
	// (2) The node to be deleted is the first (but not the last) node of the
	// linked list.
	// (3) The node to be deleted is the last (but not the first) node of the
	// linked list.
	// (4) The node to be deleted is an intermediate (neither first nor last)
	// node of the linked list.

	/**
	 * @param sll
	 * @param node
	 */
	private static <E> void deleteNode(SLL<E> sll, SLLNode<E> node) {
		SLLUtil.delete(sll, node);
		SLLUtil.printSLL(sll);
	}

	/**
	 * @param sll
	 * @param element
	 */
	private static <E> void searchNode(SLL<E> sll, E element) {
		SLLNode<E> node = SLLUtil.searchNode(sll, element);
		System.out.println(node);
	}

	/**
	 * This method is used to merge Sorted Single Linked List..
	 */
	private static void mergeSSLL() {
		SLL<String> sll1 = SLLUtil.createLinkedList();
		SLL<String> sll2 = SLLUtil.createSortedLinkedList();
		SLL<String> sll3 = SLLUtil.mergeSSLL(sll1, sll2);

		SLLUtil.printSLL(sll3);
	}

	/**
	 * This method is used to sort SLL by Insertion Sort..
	 */
	private static void insertionSortSLL() {
		SLL<String> usll = SLLUtil.createUnSortedLinkedList();
		SLL<String> ssll = SLLUtil.insertionSorting(usll);

		SLLUtil.printSLL(ssll);
	}

	/**
	 * To reverse SLL..
	 */
	private static void reverseList() {
		SLL<String> sll = SLLUtil.createUnSortedLinkedList();

		SLL<String> rsll = SLLUtil.reverseSLL(sll);

		SLLUtil.printSLL(rsll);

	}
}
