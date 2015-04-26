public class LinkedListNode {
	// instance variables
	private Flyer flyer;
	private LinkedListNode prev = null;
	private LinkedListNode next = null;
	
	// constructor
	public LinkedListNode(Flyer flyer) {
		this.flyer = flyer;
	}

	// access methods
	public Flyer getElement() {
		return flyer;
	}

	public LinkedListNode getPrev() {
		return prev;
	}

	public LinkedListNode getNext() {
		return next;
	}

	// update methods
	public void setElement(Flyer flyer) {
		this.flyer = flyer;
	}

	public void setPrev(LinkedListNode prev) {
		this.prev = prev;
	}

	public void setNext(LinkedListNode next) {
		this.next = next;
	}

}
