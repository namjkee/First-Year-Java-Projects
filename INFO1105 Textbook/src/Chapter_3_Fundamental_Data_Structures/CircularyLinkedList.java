package Chapter_3_Fundamental_Data_Structures;

public class CircularyLinkedList<E> {
	
	private static class Node<E> {
		private E element;
		private Node<E> next;
		
		public Node(E e, Node<E> n) {
			element = e;
			next = n;
		}

		public E getElement() {
			return element;
		}

		public Node<E> getNext() {
			return next;
		}
		
		public void setNext(Node<E> n) {
			next = n;
		}
	
	}
	
	private Node<E> tail = null;
	private int size = 0;
	public CircularyLinkedList() {
		// Empty circulary list
	}
	
	// Accessor Methods
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		if (size == 0) {
			return true;
		} else
			return false;
	}
	
	public E first() {
		if (isEmpty()) {
			return null;
		} else
			return tail.getNext().getElement();
	}
	
	public E last() {
		if (isEmpty()) {
			return null;
		} else
			return tail.getElement();
	}
	
	// Updator methods
	public void rotate() {
		if (isEmpty()) {
			return;
		} else // Rotate
			tail = tail.getNext();
	}
	
	public void addFirst(E e) {
		if (isEmpty()) {
			tail = new Node<E>(e, null);
			tail.setNext(tail);
		} else {
			Node<E> newest = new Node<E>(e, tail.getNext());
			tail.setNext(newest);
		}
		size++;
	}
	
	public void addLast(E e) {
		addFirst(e);
		tail = tail.getNext();
	}
	
	public E removeFirst() {
		if (isEmpty()) {
			return null;
		}
		Node<E> head = tail.getNext();
		if (head == tail) {
			tail = null;
		} else {
			tail.setNext(head.getNext());
		}
		size--;
		return head.getElement();
	}
	
	
	
	

}
