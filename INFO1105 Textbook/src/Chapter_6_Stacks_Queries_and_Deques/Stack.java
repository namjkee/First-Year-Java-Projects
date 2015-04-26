package Chapter_6_Stacks_Queries_and_Deques;
//ADT not Java Library's Data Type
public interface Stack<E> {
	
	// Accessor methods
	
	/**
	 * Returns the number of elements in the stack
	 * @return number of elements in the stack
	 */
	int size();
	
	/**
	 * Tests whether the stack is empty
	 * @return true if the stack is empty, false otherwise
	 */
	boolean isEmpty();
	
	/**
	 * Returns, but does not remove, the element at the top of the stack
	 * @return top element in the stack (or null if empty)
	 */
	E top();
	
	// Updater methods
	
	/**
	 * Inserts an element at the top of the stack
	 * @param e the element to be inserted - The parameter is the generic of Stack Objects
	 */
	void push(E e);
	
	/**
	 * Removes and returns the top element from the stack
	 * @return element removed (or null if empty)
	 */
	E pop();

}
