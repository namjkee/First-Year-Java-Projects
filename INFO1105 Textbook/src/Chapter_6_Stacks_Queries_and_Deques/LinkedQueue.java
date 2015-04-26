package Chapter_6_Stacks_Queries_and_Deques;

import Chapter_3_Fundamental_Data_Structures.SinglyLinkedList;

/**
 * Adapted from SinglyLinkedList, to build a LinkedQueue that implements Queue methods
 * @author Clifford
 *
 * @param <E>
 */
public class LinkedQueue<E> implements Queue<E> {
	private SinglyLinkedList<E> list = new SinglyLinkedList<>(); // empty list
	public LinkedQueue() { } // new queue relies on empty list above
	public int size() { return list.size(); }
	public boolean isEmpty() {return (list.size() == 0);}
	public E first() { return list.getFirst();}
	public void enqueue(E e) {list.addLast(e);}
	public E dequeue() {return list.removeFirst(); }
	
}
