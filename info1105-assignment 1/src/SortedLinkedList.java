
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SortedLinkedList implements Iterable<LinkedListNode> {
	//--------------------Nested SortedLinkedListIterator class ---------------
	private class SortedLinkedListIterator implements Iterator<LinkedListNode> {
		// Instance variables
		private LinkedListNode cursor = getFirst();
		private LinkedListNode recent = null;
		private int iterModCount;
		
		// constructor
		public SortedLinkedListIterator(int modCount) {
			iterModCount = modCount;
		}
		
		// interface implementation
		public boolean hasNext() throws RuntimeException {
			if (iterModCount != modCount) throw new RuntimeException("Linked list changed during iteration");
			if (cursor == null) return false;						// prevent null pointer exception by checking if iterator is empty
			else if (cursor.getElement() != null) return true;		// true if cursor on non-sentinel nodes
			else return false;										// the tail sentinel node has a null element
		}

		public LinkedListNode next() throws NoSuchElementException, RuntimeException {
			if (iterModCount != modCount) throw new RuntimeException("Linked list changed during iteration");
			recent = cursor;
			cursor = cursor.getNext();
			return recent;
		}

		public void remove() throws IllegalStateException, RuntimeException {

			if (iterModCount != modCount) throw new RuntimeException("Linked list changed during iteration");
			SortedLinkedList.this.remove(recent.getElement());
			recent = null;
		} 
		
	}
	//--------------------Nested SortedLinkedListIterator class ---------------
	
	// Returns a iterable collection of the list
	public Iterator<LinkedListNode> iterator() throws RuntimeException {
		return new SortedLinkedListIterator(modCount);
	}
	
	// SortedLinkList's instance variables
	private LinkedListNode head = new LinkedListNode(null);
	private LinkedListNode tail = new LinkedListNode(null);
	protected int modCount = 0;
	private int capacity;
	private int size = 0;
	
	// constructors
	public SortedLinkedList() {
		this.capacity = 10;			// default capacity should be 10	
		head.setNext(tail);
		tail.setPrev(head);
	}

	public SortedLinkedList(int capacity) {
		/* Handling negative capacity */
		if (capacity < 0) {
			capacity = 0;
			head.setNext(tail);
			tail.setPrev(head);
		}
		else {
			this.capacity = capacity;	// else it takes argument capacity
			head.setNext(tail);
			tail.setPrev(head);
		}	
	}

	// access methods
	public int size() {
		return this.size;
	}

	public boolean isEmpty() {
		return (size() == 0);
	}

	public LinkedListNode getHead() {
		return head;
	}

	public LinkedListNode getTail() {
		return tail;
	}

	public LinkedListNode getFirst() {
		if (size == 0)				// would've returned tail, now returns null to prevent returning sentinel node
			return null;
		else {
			return head.getNext();
		}
	}

	public LinkedListNode getLast() {
		if (size == 0)				// would've returned head, now returns null to prevent returning sentinel node
			return null;
		else {
			return tail.getPrev();
		}
	}

	// update methods
	public void add(Flyer flyer) {
		/* Dynamic modification count to compare to iterator's modCount during it's construction  */
		modCount++;
		
		/* Adding to an empty list if capacity isn't set to zero */
		if (isEmpty() && !capacityBreached()) {
			addFirst(flyer);											
			return;										
		}
		
		LinkedListNode currentNode = getFirst();
		/* Traverse through list to find it's position based on the current scores/distances */
		for (int i = 0; i < capacity; i++) {
			if (flyer.getDistance() > currentNode.getElement().getDistance()) {
				if (!capacityBreached()) {										// add normally if within capacity
					addBetween(flyer, currentNode.getPrev(), currentNode);
					return;
				}
				else {															// add new flyer and remove the last one
					addBetween(flyer, currentNode.getPrev(), currentNode);
					remove(getLast().getElement());
					return;
				}
			}
			currentNode = currentNode.getNext();
			if (currentNode == tail) {					// traversed to the end of the list
				if (!capacityBreached()) {				// add normally if within capacity
					addLast(flyer);
					return;
				}
				else {									// this means it's less than all scores in the list and above capacity
					return;								// so it doesn't get added
				}
			}
		}
		// if capacity is 0, execution ends here
	}

	public void remove(Flyer flyer) {
		/* Dynamic modification count to compare to iterator's modCount during it's construction  */
		modCount++;
		
		/* Quick return incase empty list */
		if (isEmpty()) return;
		
		/* Traverse through the list and find the flyer to remove */
		LinkedListNode currentNode = getFirst();
		for (int i = 0; i < this.size; i++) {
			if (currentNode.getElement() == flyer) {				// flyer found and removed by linked it's neighbors together
				removeBetween(flyer, currentNode.getPrev(), currentNode.getNext());
			}
			currentNode = currentNode.getNext();
			if (currentNode == tail) {								// traversed through entire list and found nothing
				return;
			}
		}
		
	}

	// utility and shortcut methods
	public void addBetween(Flyer flyer, LinkedListNode beforeNewest, LinkedListNode afterNewest ) {
		LinkedListNode newestNode = new LinkedListNode(flyer);
		
		/* Linking the node to it's neigbours and the neighbours to the node */
		beforeNewest.setNext(newestNode);
		afterNewest.setPrev(newestNode);
		newestNode.setPrev(beforeNewest);
		newestNode.setNext(afterNewest);
		this.size++;
	}
	
	/* Adds flyer after the head */
	public void addFirst(Flyer flyer) {
		addBetween(flyer, head, head.getNext());
	}
	
	/* Adds flyer before the tail */
	public void addLast(Flyer flyer) {
		addBetween(flyer, tail.getPrev(), tail);
	}
	
	public void removeBetween(Flyer flyer, LinkedListNode beforeFlyer, LinkedListNode afterFlyer) {
		beforeFlyer.setNext(afterFlyer);
		afterFlyer.setPrev(beforeFlyer);
		this.size--;
	}
	
	public boolean capacityBreached() {
		if (this.size == capacity) return true;
		else return false;
	}
	
	// Print method - for main testing
	public void printList() {
		LinkedListNode currentNode = getFirst();
		System.out.println("List: ");
		while (currentNode != null && currentNode.getElement() != null) {
			currentNode.getElement().printNode();
			currentNode = currentNode.getNext();
		}
		
	}
	
}
