package linkedlists;

/**
 * @author ANKIT DAIM
 * 
 */
public class SLLUtil {
	/**
	 * 
	 */
	private SLLUtil() {
		throw new UnsupportedOperationException(
				"cannot instantiate this class..");
	}

	/**
	 * @return
	 */
	public static SLL<String> createLinkedList() {
		SLL<String> sll = new SLL<String>();

		SLLNode<String> firstNode = new SLLNode<String>("d", null);
		SLLNode<String> secondNode = new SLLNode<String>("g", null);
		SLLNode<String> thirdNode = new SLLNode<String>("n", null);
		SLLNode<String> fourthNode = new SLLNode<String>("p", null);
		SLLNode<String> fifthNode = new SLLNode<String>("t", null);

		firstNode.succ = secondNode;
		secondNode.succ = thirdNode;
		thirdNode.succ = fourthNode;
		fourthNode.succ = fifthNode;

		sll.header = firstNode;
		printSLL(sll);
		return sll;
	}

	/**
	 * @return Sorted Single LinkedList
	 */
	public static SLL<String> createSortedLinkedList() {
		SLL<String> sll = new SLL<String>();

		SLLNode<String> firstNode = new SLLNode<String>("a", null);
		SLLNode<String> secondNode = new SLLNode<String>("b", null);
		SLLNode<String> thirdNode = new SLLNode<String>("s", null);
		SLLNode<String> fourthNode = new SLLNode<String>("w", null);
		SLLNode<String> fifthNode = new SLLNode<String>("z", null);

		firstNode.succ = secondNode;
		secondNode.succ = thirdNode;
		thirdNode.succ = fourthNode;
		fourthNode.succ = fifthNode;

		sll.header = firstNode;
		printSLL(sll);
		return sll;
	}

	/**
	 * @return UnSorted SLL
	 */
	public static SLL<String> createUnSortedLinkedList() {
		SLL<String> sll = new SLL<String>();

		SLLNode<String> firstNode = new SLLNode<String>("Z", null);
		SLLNode<String> secondNode = new SLLNode<String>("a", null);
		SLLNode<String> thirdNode = new SLLNode<String>("X", null);
		SLLNode<String> fourthNode = new SLLNode<String>("w", null);
		SLLNode<String> fifthNode = new SLLNode<String>("b", null);

		firstNode.succ = secondNode;
		secondNode.succ = thirdNode;
		thirdNode.succ = fourthNode;
		fourthNode.succ = fifthNode;

		sll.header = firstNode;
		printSLL(sll);
		return sll;
	}

	public static <E> void printSLL(SLL<E> sll) {
		sll.printFirstToLast();
	}

	// Algorithm..

	// To insert elem at a given point in the SLL headed by first:
	// 1. Make ins a link to a newly-created node with element elem and
	// successor null.
	// 2. If the insertion point is before the first node:
	// 2.1. Set node ins's successor to first.
	// 2.2. Set first to ins.
	// 3. If the insertion point is after the node pred:
	// 3.1. Set node ins's successor to node pred's successor.
	// 3.2. Set node pred's successor to ins.
	// 4. Terminate.

	/**
	 * This method add the element after the prevNode to given SLL..
	 * 
	 * @param sll
	 *            the SLL to which this element is to be added..
	 * @param prevNode
	 *            The node after which this element had to be added..
	 * @param element
	 *            element to be added after prevNode
	 */
	public static <E> void insert(SLL<E> sll, SLLNode<E> prevNode, E element) {
		// Insert elem at a given point in this SLL. The insertion point is
		// after the node pred, or before the first node if pred is null.

		SLLNode<E> temp = new SLLNode<E>(element, null);

		if (prevNode == null) {
			temp.succ = sll.header;
			sll.header = temp;
		} else {
			temp.succ = prevNode.succ;
			prevNode.succ = temp;
		}
	}

	// Algorithm

	// To delete node del in the nonempty SLL headed by first:
	// 1. Let succ be node del's successor.
	// 2. If del = first:
	// 2.1. Set first to succ.
	// 3. Otherwise (if del # first):
	// 3.1. Let pred be node del's predecessor.
	// 3.2. Set node pred's successor to succ.
	// 4. Terminate.

	/**
	 * THis method is used to delete the node from the given SLL..
	 * 
	 * @param sll
	 *            SLL from which node had to be deleted..
	 * @param removeNode
	 *            SLLNode to be deleted..
	 */
	public static <E> void delete(SLL<E> sll, SLLNode<E> removeNode) {
		// Delete node del in this SLL.

		if (removeNode != null) {
			SLLNode<E> succ = removeNode.succ;

			if (removeNode == sll.header)
				sll.header = succ;
			else {
				SLLNode<E> prev = sll.header;
				while (prev.succ != removeNode)
					prev = prev.succ;
				prev.succ = succ;
			}
		}
	}

	/**
	 * @param sll
	 * @param element
	 * @return
	 */
	public static <E> SLLNode<E> searchNode(SLL<E> sll, E element) {
		for (SLLNode<E> curr = sll.header; curr != null; curr = curr.succ)
			if (element == null ? curr.element == null : element
					.equals(curr.element))
				return curr;
		return null;
	}

	// Algorithm

	// To merge two sorted SLLs, headed by first1 and first2 respectively:
	// 1. Set cur1 to first1, set cur2 to first2, set first to null, and set
	// last to null.
	// 2. While cur1 is not null and cur2 is not null, repeat:
	// 2.1. If curl's element is less than or equal to cur2's element:
	// 2.1.1. Append cur1's element to the SLL headed by (first, last).
	// 2.1.2. Set curl to curl's successor.
	// 2.2. If curl's element is greater than or equal to cur2's element:
	// 2.2.1. Append cur2's element to the SLL headed by (first, last).
	// 2.2.2. Set cur2 to cur2's successor.
	// 3. While curl is not null, repeat:
	// 3.1. Append cur1's element to the SLL headed by (first, last).
	// 3.2. Set curl to curl's successor.
	// 4. While cur2 is not null, repeat:
	// 4.1. Append cur2's element to the SLL headed by (first, last).
	// 4.2. Set cur2 to cur2's successor.
	// 5. Terminate with the SLL headed by first as answer.
	// To append elem to the SLL headed by (first, last):
	// 1. Make app a link to a newly-created node with element elem and
	// successor null.
	// 2. If last is null:
	// 2.1. Set first to app.
	// 3. If last is not null:
	// 3.1. Set node last's successor to app.
	// 4. Set last to app.
	// 5. Terminate.
	/**
	 * @param sll1
	 * @param sll2
	 * @return
	 */
	public static <E extends Comparable<? super E>> SLL<E> mergeSSLL(
			SLL<E> sll1, SLL<E> sll2) {

		SSLL<E> ssll = new SSLL<E>();
		SLLNode<E> curr1 = sll1.header, curr2 = sll2.header;
		while (curr1 != null && curr2 != null) {
			int comp = curr1.element.compareTo(curr2.element);

			if (comp <= 0) {
				appendNode(ssll, curr1.element);
				curr1 = curr1.succ;
			} else {
				appendNode(ssll, curr2.element);
				curr2 = curr2.succ;
			}
		}
		while (curr1 != null) {
			appendNode(ssll, curr1.element);
			curr1 = curr1.succ;
		}
		while (curr2 != null) {
			appendNode(ssll, curr2.element);
			curr2 = curr2.succ;
		}

		SLL<E> sll = new SLL<E>();
		sll.header = ssll.header;
		return sll;
	}

	/**
	 * @param ssll
	 * @param element
	 */
	private static <E> void appendNode(SSLL<E> ssll, E element) {
		SLLNode<E> temp = new SLLNode<E>(element, null);

		if (ssll.last == null)
			ssll.header = temp;
		else
			ssll.last.succ = temp;
		ssll.last = temp;
	}

	// Algorithm Insertion Sort..

	// To sort the SLL headed by first:
	// 1. Make sorted empty.
	// 2. Set unsorted to first.
	// 3. While unsorted is not null, repeat:
	// 3.1. Set next to unsorted.
	// 3.2. Set unsorted to node next's successor.
	// 3.3. Insert node next into the sorted SLL headed by sorted.
	// 4. Terminate with answer sorted.
	// To insert node next into the sorted SLL headed by sorted:
	// 1. Set pred to null, and set curr to sorted.
	// 2. While curr is not null and node next's element is less than or equal
	// to node curr's element, repeat:
	// 2.1. Set pred to curr, and set curr to node curr's successor.
	// 3. Set node next's successor to curr.
	// 4. If pred is null, set sorted to next.
	// 5. If pred is not null, set node pred's successor to next.
	// 6. Terminate.

	public static <E extends Comparable<? super E>> SLL<E> insertionSorting(
			SLL<E> usll) {
		SLL<E> ssll = new SLL<E>();

		SLLNode<E> curr = usll.header;

		while (curr != null) {
			fillSSLL(ssll, curr.element);
			curr = curr.succ;
		}
		return ssll;
	}

	/**
	 * @param ssll
	 * @param element
	 */
	private static <E extends Comparable<? super E>> void fillSSLL(SLL<E> ssll,
			E element) {
		SLLNode<E> pred = null;
		SLLNode<E> curr = ssll.header;
		// new SLLNode created so that original one didn't get modified..
		SLLNode<E> temp = new SLLNode<E>(element, null);

		while (curr != null && curr.element.compareTo(element) <= 0) {
			pred = curr;
			curr = curr.succ;
		}

		if (pred != null)
			pred.succ = temp;
		else
			ssll.header = temp;
		temp.succ = curr;
	}

	/**
	 * @param sll
	 * @return
	 */
	public static <E> SLL<E> reverseSLL(SLL<E> sll) {
		SLL<E> reverseSll = new SLL<E>();

		SLLNode<E> curr = sll.header;

		while (curr != null) {
			fillReverseSLL(reverseSll, curr.element);
			curr = curr.succ;
		}
		return reverseSll;
	}

	/**
	 * @param reverseSll
	 * @param element
	 */
	private static <E> void fillReverseSLL(SLL<E> reverseSll, E element) {

		SLLNode<E> curr = new SLLNode<E>(element, null);
		if (reverseSll.header != null)
			curr.succ = reverseSll.header;
		reverseSll.header = curr;

	}
}
