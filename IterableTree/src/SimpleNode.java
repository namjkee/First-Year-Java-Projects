import java.util.ArrayList;
import java.util.List;


public class SimpleNode<E> implements Node<E> {
	private E element;
	private Node<E> parent;
	private List<Node<E>> children;
	
	public SimpleNode(E element) {
		this.element = element;
		this.parent = null;
		this.children = new ArrayList<Node<E>>();
	}
	
	/* Node implementation */
	
	public E getElement() {
		return this.element;
	}
	
	public void setElement(E element) {
		this.element = element;
	}
	
	public Node<E> getParent() {
		return this.parent;
	}
	
	public void setParent(Node<E> parent) {
		this.parent = parent;
	}
	
	public List<Node<E>> getChildren() {
		return this.children;
	}
	
	public void addChild(Node<E> child) {
		children.add(child);
	}
	
	public void removeChild(Node<E> child) {
		children.remove(child);
	}
	
}
