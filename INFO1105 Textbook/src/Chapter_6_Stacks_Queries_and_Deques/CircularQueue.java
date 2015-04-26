package Chapter_6_Stacks_Queries_and_Deques;

public interface CircularQueue<E> extends Queue<E> {
	/**
	 * Rotates the front element of the queue to the back of the queue.
	 * This does nothing if the queue is empty.
	 */
	void rotate();
}
