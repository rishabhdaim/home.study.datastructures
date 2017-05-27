package priority.queue;

public class TestPriorityQueue {
	public static void main(String[] args) {
		// HeapPriorityQueue<Integer> sSllPriorityQueue = new
		// HeapPriorityQueue<>();

		// SSllPriorityQueue<Integer> sSllPriorityQueue = new
		// SSllPriorityQueue<>();
		USSllPriorityQueue<Integer> sSllPriorityQueue = new USSllPriorityQueue<>();

		sSllPriorityQueue.addLast(1);
		sSllPriorityQueue.addLast(11);
		sSllPriorityQueue.addLast(41);
		sSllPriorityQueue.addLast(441);
		sSllPriorityQueue.addLast(15);
		sSllPriorityQueue.addLast(16);
		sSllPriorityQueue.addLast(176);
		sSllPriorityQueue.addLast(341);
		sSllPriorityQueue.addLast(761);
		sSllPriorityQueue.addLast(12);

		System.out.println(sSllPriorityQueue.last);
		System.out.println(sSllPriorityQueue.getGreatest());
	}
}
