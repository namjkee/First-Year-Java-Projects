package Chapter_6_Stacks_Queries_and_Deques;

import Chapter_3_Fundamental_Data_Structures.SinglyLinkedList;

public class LinkedStack<E> implements Stack<E> {
	private SinglyLinkedList<E> list = new SinglyLinkedList<>(); 	// empty list
	
	public LinkedStack() {} 		// default constructor but new stack relies on the intiially empty list
	
	public int size() { return list.size(); }
	
	public boolean isEmpty() { return list.isEmpty(); }
	
	public void push(E element) { list.addFirst(element); }
	
	public E top() { return list.getFirst(); }
	
	public E pop() { return list.removeFirst(); }
	
}
