package queues;

public class PersonStack {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[] strings = { "as", "ds", "gr", "gfdg", "fdg", "jj", "rer",
				"gng", "hy", "jy", "jk", "w" };

		ArrayQueue<String> arrayQueue = new ArrayQueue<String>();
		System.out.println(arrayQueue);
		arrayQueue.addLast(strings[0]);
		arrayQueue.addLast(strings[1]);
		arrayQueue.addLast(strings[2]);
		System.out.println(arrayQueue);
		arrayQueue.addLast(strings[3]);
		arrayQueue.addLast(strings[4]);
		arrayQueue.addLast(strings[5]);
		System.out.println(arrayQueue);

		arrayQueue.removeFirst();
		System.out.println(arrayQueue);
		arrayQueue.addLast(strings[6]);
		System.out.println(arrayQueue);
		arrayQueue.addLast(strings[7]);
		System.out.println(arrayQueue);

		LinkedQueue<String> linkedQueue = new LinkedQueue<String>();
		System.out.println(linkedQueue);
		linkedQueue.addLast(strings[0]);
		linkedQueue.addLast(strings[1]);
		linkedQueue.addLast(strings[2]);
		System.out.println(linkedQueue);
		linkedQueue.addLast(strings[3]);
		linkedQueue.addLast(strings[4]);
		linkedQueue.addLast(strings[5]);
		System.out.println(linkedQueue);

		linkedQueue.removeFirst();
		System.out.println(linkedQueue);
		linkedQueue.addLast(strings[6]);
		System.out.println(linkedQueue);
		linkedQueue.addLast(strings[7]);
		System.out.println(linkedQueue);

		ArrayDeque<String> arrayDeque = new ArrayDeque<String>();
		System.out.println(arrayDeque);
		arrayDeque.addLast(strings[0]);
		arrayDeque.addLast(strings[1]);
		arrayDeque.addLast(strings[2]);
		System.out.println(arrayDeque);
		arrayDeque.addLast(strings[3]);
		arrayDeque.addLast(strings[4]);
		arrayDeque.addLast(strings[5]);
		System.out.println(arrayDeque);

		arrayDeque.removeFirst();
		System.out.println(arrayDeque);
		arrayDeque.addLast(strings[6]);
		System.out.println(arrayDeque);
		arrayDeque.addLast(strings[7]);
		System.out.println(arrayDeque);
		arrayDeque.addFirst(strings[8]);
		System.out.println(arrayDeque);
		arrayDeque.removeLast();
		System.out.println(arrayDeque);
	}
}
