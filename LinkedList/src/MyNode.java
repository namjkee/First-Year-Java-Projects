
public class MyNode implements Node{
	private Object data;
	private Node next;
	
	public MyNode(Object o) {
		data = o;
		this.next = null;
	}

	public Object getElement() {
		return this.data;
	}

	public Node getNext() {
		return this.next;
	}

	public void setElement(Object element) {
		this.data = element;
	}

	public void setNext(Node next) {
		this.next = next;
	}

}