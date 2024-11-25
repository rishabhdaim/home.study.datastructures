package stacks;

public class StringStack {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TwinStack<Integer> twinStack = new TwinStack<Integer>();
		twinStack.addLast(1);
		twinStack.addLast(2);
		twinStack.addLast(3);
		twinStack.addLast(4);
		twinStack.addFirst(20);
		twinStack.addFirst(19);
		twinStack.addFirst(18);
		twinStack.addLast(5);
		twinStack.addLast(6);
		twinStack.addLast(7);
		twinStack.addLast(8);
		twinStack.addLast(9);
		System.out.println(twinStack);
		System.out.println(twinStack);
		
		System.out.println(twinStack.removeFirst());
		System.out.println(twinStack.removeFirst());
		System.out.println(twinStack.removeFirst());
		System.out.println(twinStack);

	}

}
