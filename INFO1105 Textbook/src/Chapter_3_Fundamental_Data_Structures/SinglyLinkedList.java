package Chapter_3_Fundamental_Data_Structures;

public class SinglyLinkedList<E> {
	protected Node<E> head = null;
	private Node<E> tail = null;
	private int size = 0;
	
	public SinglyLinkedList() {
		// Empty SinglyLinkedList
	}
	
	// Access methods
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		if (size == 0) {
			return true;
		} else
			return false;
	}
	
	public E getFirst() {
		if (isEmpty()) {
			return null;
		} else {
			return head.getElement();
		}
	} 
	
	public E getLast() {
		if (isEmpty()) {
			return null;
		} else
			return tail.getElement();
	}
	
	// Update Methods
	public void addFirst(E e) {
		head = new Node<E> (e, head);
		if (isEmpty()) {
			tail = head;
		}
		size++;
		System.out.println("Node with element " + e);
	}
	
	public void addLast(E e) {
		Node<E> newest = new Node<E> (e, null);
		if (isEmpty()) {
			head = newest;
		} else {
			tail.setNext(newest);
		}
		tail = newest;
		size++;
	}
	
	public E removeFirst() {
		if (isEmpty()) {
			return null;
		}
		E removed = head.getElement();
		head = head.getNext();
		size--;
		
		return removed;
	}
	
	protected static class Node<E> {
		private E element;
		private Node<E> next;
		
		public Node(E element, Node<E> next) {
			this.element = element;
			this.next = next;
		}
		
		// Access Methods
		public E getElement() {
			return element;
		}
		
		public Node<E> getNext() {
			return next;
		}
		
		// Update Methods
		public void setNext(Node<E> node) {
			next = node;
		}
	}

}
