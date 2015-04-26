package Chapter_3_Fundamental_Data_Structures;

public class DoublyLinkedList<E> {
	private Node<E> header;
	private Node<E> trailer;
	private int size = 0;
	
	public DoublyLinkedList() {
		header = new Node<E>(null, null,  null);
		trailer = new Node<E>(null, header, null);
		header.setNext(trailer);
	}
	
	// Access Methods
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public E First() {
		if (isEmpty()) {
			return null;
		} else
		return header.getNext().getElement();
	}
	
	public E Last() {
		if (isEmpty()) {
			return null;
		} else
			return trailer.getPrev().getElement();
	}
	
	// Update Methods
	public void addBetween(E e, Node<E> predecessor, Node<E> successor) {
		Node<E> newest = new Node<E> (e, predecessor, successor);
		predecessor.setNext(newest);
		successor.setPrev(newest);
		size++;
	}
	
	public E remove(Node<E> node) {
		Node<E> predecessor = node.getPrev();
		Node<E> successor = node.getNext();
		predecessor.setNext(successor);
		successor.setPrev(predecessor);
		size--;
		return node.getElement();
	}
	
	public void addFirst(E e) {
		addBetween(e, header, header.getNext());
	}
	
	public void addLast(E e) {
		addBetween(e, trailer.getPrev(), trailer);
	}
	
	public E removeFirst() {
		if (isEmpty()) return null;
		return remove(header.getNext());
	}
	
	public E removeLast() {
		if (isEmpty()) return null;
		return remove(trailer.getPrev());
	}
	
	private static class Node<E> {
		private Node<E> prev;
		private E element;
		private Node<E> next;
		
		public Node(E element, Node<E> prev, Node<E> next) {
			this.prev = prev;
			this.element = element;
			this.next = next;
		}
		
		// Access Methods
		public Node<E> getPrev() {
			return prev;
		}

		public E getElement() {
			return element;
		}

		public Node<E> getNext() {
			return next;
		}

		public void setPrev(Node<E> prev) {
			this.prev = prev;
		}

		public void setNext(Node<E> next) {
			this.next = next;
		}
	}
	
	
}
