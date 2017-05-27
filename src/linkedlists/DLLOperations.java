package linkedlists;


public class DLLOperations {

	// (1) Insertion in an empty linked list.
	// (2) Insertion before the first node of a nonempty linked list.
	// (3) Insertion after the last node of a nonempty linked list.
	// (4) Insertion between nodes of a nonempty linked list.

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DLL<String> dll = DLLUtil.createLinkedList();

		insertElement(dll, null, "inserted");
		deleteNode(dll, null);
		searchNode(dll, "second");
		mergeSDLL();
		
		reverseDLL();
	}

	/**
	 * @param dll
	 * @param prevNode
	 * @param element
	 */
	private static <E> void insertElement(DLL<E> dll, DLLNode<E> prevNode,
			E element) {
		DLLUtil.insert(dll, prevNode, element);
		DLLUtil.printDLL(dll);
	}

	/**
	 * @param dll
	 * @param node
	 */
	private static <E> void deleteNode(DLL<E> dll, DLLNode<E> node) {
		DLLUtil.deleteNode(dll, node);
	}

	/**
	 * @param dll
	 * @param element
	 */
	private static <E> void searchNode(DLL<E> dll, E element) {
		DLLNode<E> node = DLLUtil.searchNode(dll, element);
		System.out.println(node);
	}

	/**
	 * To merge two sorted DLL..
	 */
	private static void mergeSDLL() {
		DLL<String> dll1 = DLLUtil.createLinkedList();
		DLL<String> dll2 = DLLUtil.createSortedLinkedList();
		DLL<String> sdll = DLLUtil.mergeSDLL(dll1, dll2);

		DLLUtil.printDLL(sdll);
	}

	/**
	 * To reverse DLL..
	 */
	private static void reverseDLL() {
		DLL<String> dll = DLLUtil.createLinkedList();

		DLL<String> rdll = DLLUtil.reverseSLL(dll);

		DLLUtil.printDLL(rdll);
	}
}