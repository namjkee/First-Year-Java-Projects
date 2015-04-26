package Chapter_6_Stacks_Queries_and_Deques;

import Chapter_3_Fundamental_Data_Structures.CircularyLinkedList;
/**
 * This class is adapted from the CircularyLinkedList from chapter 3. The class is the implementation of the CircularQueue, we want to define pre-existing and new methods
 * of the CircularQueue interface (rotate()) by using the methods of our adapted instance (CircularyLinkedList) so that the methods of this class (LinkedCircularQueue) actually
 * implement methods of it's interface (CircularQueue<E>) by adapting them to the CircularlyLinkedList. This process is known as the Adaptor Pattern
 * @author Clifford
 *
 * @param <E>
 */
public class LinkedCircularQueue<E> implements CircularQueue<E> {
	private CircularyLinkedList<E> list = new CircularyLinkedList<E>();	// Hidden field that we're trying to adapt - CircularyLinkedLists
	public LinkedCircularQueue(){}
	public int size() { return list.size();}
	public boolean isEmpty() { return list.isEmpty();}
	public E first() { return list.first();}
	public void enqueue(E e) { list.addLast(e);}
	public E dequeue() { return list.removeFirst();}
	public void rotate() { list.rotate();}
}
