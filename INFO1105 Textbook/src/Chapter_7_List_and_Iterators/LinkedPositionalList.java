package Chapter_7_List_and_Iterators;

/**
 * Implementation of a positional list stored as a doubly linked list 
 * @author Clifford
 *
 * @param <E>
 */
public class LinkedPositionalList<E> implements PositionalList<E>{
	// Nested Node class 
	private static class Node<E> implements Position<E> {
		// instance variables
		private E element;
		private Node<E> prev;
		private Node<E> next;
		
		public Node(E e, Node<E> p, Node<E> n) {
			element = e;
			prev = p;
			next = n;
		}

		public E getElement() throws IllegalStateException {
			if (next == null) throw new IllegalStateException("Position no longer valid");
			else return element;
		}
		
		public Node<E> getPrev() {
			return prev;
		}
		
		public Node<E> getNext() {
			return next;
		}

		public void setElement(E e) {
			element = e;
		}

		public void setPrev(Node<E> p) {
			prev = p;
		}

		public void setNext(Node<E> n) {
			next = n;
		}
		
	}
	// End of nested class
	
	// instance variables of the LinkedPositionalList
	private Node<E> header;
	private Node<E> trailer;
	private int size = 0;
	
	/**
	 * Constructs a new empty list
	 */
	public LinkedPositionalList() {
		header = new Node<>(null, null, null);
		trailer = new Node<>(null, header, null);
		header.setNext(trailer);
	}
	
	// Utility Methods
	/**
	 * Valides the position and return it as a node
	 */
	private Node<E> validate(Position<E> p) throws IllegalArgumentException {
		if ((p instanceof Node)) throw new IllegalArgumentException("Invalid p");
		Node<E> node = (Node<E>) p;
		if (node.getNext() == null) throw new IllegalArgumentException("p is no longer in the list");
		return node;
	}
	
	/**
	 * Returns the given node as a Position (or null, if it is a sentinel)
	 */
	public Position<E> position(Node<E> node) {
		if (node == header || node == trailer) return null; 	// to not expose user to the sentinels
		else return node;
	}
	
	// Accessor Methods
	/**
	 * Returns the number of elements in the linked list
	 */
	public int size() { return size; }
	
	/**
	 * Tests whether the linked list is empty
	 */
	public boolean isEmpty() { return size == 0; }
	
	/**
	 * Returns the first Position in the linked list (or null, if empty)
	 */
	public Position<E> first() {
		return position(header.getNext());
	}
	
	/**
	 * Returns the last Position in the linked list (or null, if empty)
	 */
	public Position<E> last() {
		return position(trailer.getPrev());
	}
	
	/**
	 * Returns the Position immediately before Position p (or null, if p is first)
	 */
	public Position<E> before(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return position(node.getPrev());
	}
	
	/**
	 * Returns the Position immediately after Position p (or null, if p is first)
	 */
	public Position<E> after(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return position(node.getNext());
	}
	
	// More private utility methods
	/**
	 * Adds element e to the linkedlist between the given nodes
	 */
	private Position<E> addBetween(E e, Node<E> pred, Node<E> succ) {
		Node<E> newest = new Node<>(e, pred, succ);
		pred.setNext(newest);
		succ.setPrev(newest);
		size++;
		return newest;
	}

	// Update methods
	/**
	 * Inserts element e at the front of the linked list and returns its new Position
	 */
	public Position<E> addFirst(E e) {
		return addBetween(e, header, header.getNext());
	}
	
	/**
	 * Inserts element e at the back of the linked list and returns its new Position
	 */
	public Position<E> addLast(E e) {
		return addBetween(e, trailer.getPrev(), trailer);
	}

	/**
	 * Inserts element e immediately before Position p, and returns its new Position
	 */
	public Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return addBetween(e, node.getPrev(), node);
	}

	/**
	 * Inserts element e immediately after Position p, and returns its new Position 
	 */
	public Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return addBetween(e, node, node.getNext());
	}

	/**
	 * Replaces the element stored at Position p and returns the replaced element
	 */
	public E set(Position<E> p, E e) throws IllegalArgumentException {
		Node<E> node = validate(p);
		E answer = node.getElement();
		node.setElement(e);
		return answer;
	}

	/**
	 * Removes the element stored at Position p and returns it (invalidating p)
	 */
	public E remove(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		Node<E> predecessor = node.getPrev();
		Node<E> successor = node.getNext();
		predecessor.setNext(successor);
		successor.setPrev(predecessor);
		size--;
		
		E answer = node.getElement();
		node.setElement(null);
		node.setNext(null);
		node.setPrev(null);
		return answer;
		
	}
	
}
