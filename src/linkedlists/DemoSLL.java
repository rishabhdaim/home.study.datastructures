package linkedlists;

public class DemoSLL {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SLL<String> sll = new SLL<String>();
		SLLNode<String> a = new SLLNode<String>("last", null);

		System.out.println("SLL : " + sll + "::" + "SLLNode : " + a);

		sll.header = new SLLNode<String>("first", new SLLNode<String>("second", a));

		// System.out.println(sll);

		// sll.printFirstToLast();

		// sll.first = sll.first.succ.succ;

		// sll.printFirstToLast();

		SLLNode<String> second = sll.header.succ; // saved reference to second node, so
											// that node doesn't get deleted..

		sll.header.succ = second.succ; // pointing first node to third node in
										// place of second node, second got
										// deleted from list..
		second.succ = sll.header; // pointing second node to first node in place
									// of third node..
		sll.header = second; // pointing header to second node..

		sll.printFirstToLast();
	}
}
