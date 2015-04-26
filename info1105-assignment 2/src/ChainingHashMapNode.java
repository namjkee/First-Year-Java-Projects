
public class ChainingHashMapNode {
	// instance variables
	private Object key;
	private Object value;
	private ChainingHashMapNode next;
	
	// construction
	public ChainingHashMapNode(Object key, Object value) {
		this.key = key;
		this.value = value;
		this.next = null;
	}
	
	// get methods
	public Object getKey() {
		return this.key;
	}
	
	public Object getValue() {
		return this.value;
	}
	
	public ChainingHashMapNode getNext() {
		return this.next;
	}
	
	// set methods
	public void setValue(Object newValue) {
		this.value = newValue;
	}
	
	public void setNext(ChainingHashMapNode next) {
		this.next = next;
	}
}
