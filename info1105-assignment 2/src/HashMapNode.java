
public class HashMapNode {
	// instance variables
	private Object key;
	private Object value;
	
	// construction
	public HashMapNode (Object key, Object value) {
		this.key = key;
		this.value = value;
	}
	
	// get methods
	public Object getKey() {
		return this.key;
	}
	
	public Object getValue() {
		return this.value;
	}
	
	// set method
	public void setValue(Object newValue) {
		this.value = newValue;
	}
	
	
}
