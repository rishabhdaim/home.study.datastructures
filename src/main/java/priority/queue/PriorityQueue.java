package priority.queue;

import queues.Queue;

//1. It must be possible to make a priority queue empty.
//2. It must be possible to test whether a priority queue is empty.
//3. It must be possible to obtain the length of a priority queue.
//4. It must be possible to add an element to a priority queue.
//5. It must be possible to remove the least element in a priority queue.
//6. It must be possible to access the least element in a priority queue without removing it.

public interface PriorityQueue<E> extends Queue<E> {

	/**
	 * @return greatest element in this queue..
	 */
	E getGreatest();

}
