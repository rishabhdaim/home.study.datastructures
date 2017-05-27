package linkedlists;

public class DLLUtil {
	/**
	 * to make this class non-instantiable..
	 */
	private DLLUtil() {
		throw new UnsupportedOperationException(
				"Cannot instantiate this class..");
	}

	/**
	 * @return
	 */
	public static DLL<String> createLinkedList() {
		DLL<String> dll = new DLL<String>();

		DLLNode<String> firstNode = new DLLNode<String>(null, null, "a");
		DLLNode<String> secondNode = new DLLNode<String>(null, null, "d");
		DLLNode<String> thirdNode = new DLLNode<String>(null, null, "h");
		DLLNode<String> fourthNode = new DLLNode<String>(null, null, "m");
		DLLNode<String> fifthNode = new DLLNode<String>(null, null, "p");

		// setting first node..
		firstNode.succ = secondNode;
		// setting second node..
		secondNode.pred = firstNode;
		secondNode.succ = thirdNode;
		// setting third node..
		thirdNode.pred = secondNode;
		thirdNode.succ = fourthNode;
		// setting fourth node..
		fourthNode.pred = thirdNode;
		fourthNode.succ = fifthNode;
		// setting fifth node..
		fifthNode.pred = fourthNode;
		// pointing header and last element of LinkedList
		dll.header = firstNode;
		dll.last = fifthNode;
		printDLL(dll);
		return dll;
	}

	/**
	 * @return
	 */
	public static DLL<String> createSortedLinkedList() {
		DLL<String> dll = new DLL<String>();

		DLLNode<String> firstNode = new DLLNode<String>(null, null, "e");
		DLLNode<String> secondNode = new DLLNode<String>(null, null, "k");
		DLLNode<String> thirdNode = new DLLNode<String>(null, null, "r");
		DLLNode<String> fourthNode = new DLLNode<String>(null, null, "x");
		DLLNode<String> fifthNode = new DLLNode<String>(null, null, "z");

		// setting first node..
		firstNode.succ = secondNode;
		// setting second node..
		secondNode.pred = firstNode;
		secondNode.succ = thirdNode;
		// setting third node..
		thirdNode.pred = secondNode;
		thirdNode.succ = fourthNode;
		// setting fourth node..
		fourthNode.pred = thirdNode;
		fourthNode.succ = fifthNode;
		// setting fifth node..
		fifthNode.pred = fourthNode;
		// pointing header and last element of LinkedList
		dll.header = firstNode;
		dll.last = fifthNode;
		printDLL(dll);
		return dll;
	}

	/**
	 * @param dll
	 */
	public static <E> void printDLL(DLL<E> dll) {
		dll.printFirstToLast();
		dll.printLastToFirst();
	}

	// Algorithm..

	// To insert elem at a given point in a DLL headed by (first, last):
	// 1. Make ins a link to a newly-created node with element elem, predecessor
	// null, and successor null.
	// 2. Insert ins at the insertion point in the forward SLL headed by first.
	// 3. Let succ be ins's successor (or null if ins has no successor).
	// 4. Insert ins after node succ in the backward SLL headed by last.
	// 5. Terminate.
	// To insert node ins at a given point in the forward SLL headed by first:
	// 1. If the insertion point is before the first node:
	// 1.1. Set node ins's successor to first.
	// 1.2. Set first to ins.
	// 2. If the insertion point is after the node pred:
	// 2.1. Set node ins's successor to node pred's successor.
	// 2.2. Set node pred's successor to ins.
	// 3. Terminate.
	// To insert node ins after node succ in the backward SLL headed by last:
	// 1. If succ is null:
	// 1.1. Set node ins's predecessor to last.
	// 1.2. Set last to ins.
	// 2. If succ is not null:
	// 2.1. Set node ins's predecessor to node succ's predecessor.
	// 2.2. Set node succ's predecessor to ins.
	// 3. Terminate.

	/**
	 * @param dll
	 *            the DLL to which input element is to be added..
	 * @param prevNode
	 *            The node after which this element is to be added..
	 * @param element
	 *            the element to be added after prevNode..
	 */
	public static <E> void insert(DLL<E> dll, DLLNode<E> prevNode, E element) {

		// Insert elem at a given point in this DLL. The insertion point is
		// after the node pred, or before the first node if pred is null.
		DLLNode<E> node = new DLLNode<E>(null, null, element);
		insertNodeForwards(dll, prevNode, node);

		DLLNode<E> succNode = node.succ;
		insertNodeBackwards(dll, succNode, node);
	}

	/**
	 * @param dll
	 * @param prevNode
	 * @param node
	 */
	private static <E> void insertNodeForwards(DLL<E> dll, DLLNode<E> prevNode,
			DLLNode<E> node) {

		if (prevNode == null) {
			node.succ = dll.header;
			dll.header = node;
		} else {
			node.succ = prevNode.succ;
			prevNode.succ = node;
		}

	}

	/**
	 * @param dll
	 * @param succNode
	 * @param node
	 */
	private static <E> void insertNodeBackwards(DLL<E> dll,
			DLLNode<E> succNode, DLLNode<E> node) {
		if (succNode == null) {
			node.pred = dll.last;
			dll.last = node;
		} else {
			node.pred = succNode.pred;
			succNode.pred = node;
		}
	}

	// To delete node del in the nonempty DLL headed by (first, last):
	// 1. Let pred and succ be node del's predecessor and successor,
	// respectively.
	// 2. Delete node del, whose predecessor is pred, from the forward SLL
	// headed by first.
	// 3. Delete node del, whose successor is succ, from the backward SLL headed
	// by last.
	// 4. Terminate.
	// To delete node del, whose predecessor is pred, from the forward SLL
	// headed by first:
	// 1. If pred is null:
	// 1.1. Set first to node del's successor.
	// 2. If pred is not null:
	// 2.1. Set node pred's successor to node del's successor.
	// 3. Terminate.
	// To delete node del, whose successor is succ, from the backward SLL headed
	// by last:
	// 1. If succ is null:
	// 1.1. Set last to node del's predecessor.
	// 2. If succ is not null:
	// 2.1. Set node succ's predecessor to node del's predecessor.
	// 3. Terminate.

	/**
	 * @param dll
	 * @param node
	 */
	public static <E> void deleteNode(DLL<E> dll, DLLNode<E> node) {

		// Delete node del in this DLL.
		if (node == null)
			return;
		DLLNode<E> succ = node.succ;
		DLLNode<E> pred = node.pred;

		deleteNodeForward(dll, pred, node);
		deleteNodeBackward(dll, succ, node);

	}

	/**
	 * @param dll
	 * @param pred
	 * @param node
	 */
	private static <E> void deleteNodeForward(DLL<E> dll, DLLNode<E> pred,
			DLLNode<E> node) {

		// Delete node del, whose predecessor is pred, from the forward SLL.
		// If pred is null, then del is the first node in the forward SLL.
		if (pred == null)
			dll.header = node.succ;
		else
			pred.succ = node.succ;
	}

	/**
	 * @param dll
	 * @param succ
	 * @param node
	 */
	private static <E> void deleteNodeBackward(DLL<E> dll, DLLNode<E> succ,
			DLLNode<E> node) {
		// Delete the node del, whose successor is succ, from the backward SLL.
		// If succ is null, then del is the first node in the backward SLL.
		if (succ == null)
			dll.last = node.pred;
		else
			succ.pred = node.pred;
	}

	// To find which if any node of the SLL headed by first contains an element
	// equal to target:
	// 1. For each node curr of the SLL headed by first, repeat:
	// 1.1. If target is equal to node curr's element, terminate with answer
	// curr.
	// 2. Terminate with answer none.

	/**
	 * @param dll
	 * @param element
	 * @return
	 */
	public static <E> DLLNode<E> searchNode(DLL<E> dll, E element) {
		for (DLLNode<E> curr = dll.header; curr != null; curr = curr.succ)
			if (element == null ? curr.element == null : element
					.equals(curr.element))
				return curr;
		return null;
	}

	/**
	 * @param dll1
	 * @param dll2
	 * @return
	 */
	public static <E extends Comparable<? super E>> DLL<E> mergeSDLL(
			DLL<E> dll1, DLL<E> dll2) {

		DLL<E> sdll = new DLL<E>();
		DLLNode<E> curr1 = dll1.header, curr2 = dll2.header;
		while (curr1 != null && curr2 != null) {
			int comp = curr1.element.compareTo(curr2.element);

			if (comp <= 0) {
				appendNode(sdll, curr1.element);
				curr1 = curr1.succ;
			} else {
				appendNode(sdll, curr2.element);
				curr2 = curr2.succ;
			}
		}
		while (curr1 != null) {
			appendNode(sdll, curr1.element);
			curr1 = curr1.succ;
		}
		while (curr2 != null) {
			appendNode(sdll, curr2.element);
			curr2 = curr2.succ;
		}

		return sdll;
	}

	/**
	 * @param sdll
	 * @param element
	 */
	private static <E> void appendNode(DLL<E> sdll, E element) {
		DLLNode<E> temp = new DLLNode<E>(null, null, element);
		DLLNode<E> last = sdll.last;

		if (sdll.last == null)
			sdll.header = temp;
		else {
			last.succ = temp;
			temp.pred = last;
		}
		sdll.last = temp;
	}

	/**
	 * @param dll
	 * @return
	 */
	public static <E> DLL<E> reverseSLL(DLL<E> dll) {
		DLL<E> reverseDll = new DLL<E>();

		DLLNode<E> curr = dll.header;

		while (curr != null) {
			fillReverseDLL(reverseDll, curr.element);
			curr = curr.succ;
		}
		return reverseDll;
	}

	/**
	 * @param reverseDll
	 * @param element
	 */
	private static <E> void fillReverseDLL(DLL<E> reverseDll, E element) {
		DLLNode<E> first = reverseDll.header;
		DLLNode<E> curr = new DLLNode<E>(null, null, element);
		if (reverseDll.header == null)
			reverseDll.last = curr;
		else {
			curr.succ = first;
			first.pred = curr;
		}
		reverseDll.header = curr;
	}
}