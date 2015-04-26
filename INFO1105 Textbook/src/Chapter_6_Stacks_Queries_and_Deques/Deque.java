package Chapter_6_Stacks_Queries_and_Deques;

/**
 * Interface for a doubled-ended queue: a collection of elements that can be inserted and removed at both ends; this interface is a simplified version of java.util.Deque.
 * @author Clifford
 *
 * @param <E>
 */
public interface Deque<E> {
	/**
	 * Returns the number of elements in the deque
	 */
	int size();
	
	/**
	 * Tests whether the deque is empty
	 */
	boolean isEmpty();
	
	/**
	 * Returns, but does not remove, the first element of the deque (null if empty)
	 */
	E first();
	
	/**
	 * Returns, but does not remove, the last element of the deque (null if empty)
	 */
	E last();

	/**
	 * Inserts an element at the front of the deque
	 */
	void first(E e);
	
	/**
	 * Inserts an element at the back of the deque
	 */
	void last(E e);
	
	/**
	 * Removes and returns the first element of the deque (null if empty)
	 */
	E removeFirst();
	
	/**
	 * Removes and returns the last element of the deque (null if empty)
	 */
	E removeLast();
}	
