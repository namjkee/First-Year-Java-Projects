
public class MyLinkedList implements LinkedList{
	private Node head;
	
	public MyLinkedList() {
		head = null;
	}

	public int size() {
		int size = 0;
		Node current = head;
		while (current != null) {
			size += 1;
			current = current.getNext();
		}
		return size;
	}

	public Node getHead() {return head;}

	public boolean isEmpty() { return (head == null);}

	public Object get(int i) {
		int index = 0;
		Node current = head;
		while (current != null && index != i) {
			index += 1;
			current = current.getNext();
		}
		if (current == null) return null;
		return current.getElement();
	}

	public void add(Object o) {
		if (head == null) {
			head = new MyNode(o);
			return;
		}
		
		Node current = head;
		while (current.getNext() != null) {
			current = current.getNext();
		}
		current.setNext(new MyNode(o));
	}

	public void remove(Object o) {
		Node current = 	head;
		
		if (head == null) return;
		
		if (head.getElement() == o) {
			head = head.getNext();
			return;
		}
		
		/* The search for the removed item */
		while (current.getNext() != null && current.getNext().getElement() != o) {
			current = current.getNext();
		}
		
		/* If the while loop is false for current.getNext() != null, then we just return since we didn't find it */
		if (current.getNext() == null) {
			// Item not found\
			return;
		}
		
		/* If the while loop is false for the second condition, then current node is before the node that has the object and now we remove that node */
		/* The current node is right before the REMOVING OBJECT */
		current.setNext(current.getNext().getNext());
	}
}