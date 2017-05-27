package linkedlists;

public class DemoDLL {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DLL<String> dll = new DLL<String>();
		System.out.println(dll);

		// First node so pred will always be null..
		DLLNode<String> first = new DLLNode<String>(null, null, "first");

		// Middle Node can have both succ & pred..
		DLLNode<String> middle = new DLLNode<String>(null, null, "middle");
		// Last Node so succ will always be null
		DLLNode<String> last = new DLLNode<String>(null, null, "last");

		DLLNode<String> afterMiddle = new DLLNode<String>(null, null, "afterMiddle");

		// Creating linked list..

		dll.header = first;
		dll.last = last;

		// Assigning elements..

		first.succ = middle;
		middle.pred = first;
		middle.succ = last;
		last.pred = middle;

		dll.printFirstToLast();

		// Delete First Node..

		dll.header = first.succ;
		first.succ.pred = null;

		dll.printFirstToLast();
		dll.printLastToFirst();

		// Add First Node..
		DLLNode<String> temp = dll.header;
		dll.header = first;
		first.succ = temp;
		temp.pred = first;

		dll.printFirstToLast();
		dll.printLastToFirst();

		// Delete Second Node..

		DLLNode<String> temp2 = dll.header.succ;
		DLLNode<String> temp3 = temp2.succ;
		dll.header.succ = temp3;
		temp3.pred = temp2.pred;
		dll.printFirstToLast();
		dll.printLastToFirst();

		// Add Middle Element..

		DLLNode<String> temp4 = dll.header.succ;
		dll.header.succ = middle;
		middle.succ = temp4;
		middle.pred = temp4.pred;
		temp4.pred = middle;

		dll.printFirstToLast();
		dll.printLastToFirst();

		// Add Element after Middle..

		DLLNode<String> temp5 = middle.succ;
		middle.succ = afterMiddle;
		afterMiddle.succ = temp5;
		afterMiddle.pred = temp5.pred;
		temp5.pred = afterMiddle;

		dll.printFirstToLast();
		dll.printLastToFirst();

		// Swapping to Nodes, let say 2nd & 3rd

		DLLNode<String> node2 = first.succ;
		DLLNode<String> node3 = node2.succ;
		DLLNode<String> node4 = node3.succ;

		node3.succ = node2;
		node4.pred = node2;
		node3.pred = node2.pred;

		first.succ = node3;
		node2.pred = node3;

		node2.succ = node4;
		dll.printFirstToLast();
		dll.printLastToFirst();
	}
}
