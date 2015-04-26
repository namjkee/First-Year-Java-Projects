package Chapter_6_Stacks_Queries_and_Deques;

public class Josephus {
	/**
	 * Computes the winner of the Josephus problem using a ciruclar queue (hot potato)
	 * @param queue
	 * @param k
	 * @return
	 */
	public static <E> E Josephus(CircularQueue<E> queue, int k) {
		if (queue.isEmpty()) return null;
		while (queue.size() > 1) {
			for (int i = 0; i < k-1; i++) {				// k is a constant pattern that the gamemaster(hot potato) uses it eliminate a player, thus to dequeue a element 
				queue.rotate();
			}
			E e = queue.dequeue();						// storing the element that is dequeued in order to display message below
			System.out.println("	" + e +	" is out");
		}
		return queue.dequeue();							// Winner, last person after the while loop
	}
	
	public static <E> CircularQueue<E> buildQueue(E a[]) {	// returns an element of a circularqueue implementation
		CircularQueue<E> queue = new LinkedCircularQueue<>();
		for (int i = 0; i <a.length; i++) {
			queue.enqueue(a[i]);
		}
		return queue;
	}
	
	public static void main(String[] args) {
		String[] a1 = {"Alice", "Bob", "Cindy", "Doug", "Ed", "Fred"};
		String[] a2 = {"Gene", "Hope", "Irene", "Jack", "Kim", "Lance"};
		String[] a3 = {"Mike", "Roberto"};
		System.out.println("First winner is " + Josephus(buildQueue(a1), 3));		// 6 people, k = 3 when eliminate
		System.out.println("Second winner is " + Josephus(buildQueue(a2), 10));
		System.out.println("Third winner is " + Josephus(buildQueue(a3), 7));
	}
	
}
