import java.util.ArrayList;
import java.util.List;


public class DirectedGraphNode implements Node {
	// instance variables
	private Object value;
	private List<Node> neighbours;
	
	public DirectedGraphNode(Object value) {
		this.value = value;
		this.neighbours = new ArrayList<>();
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public Object getValue() {
		return this.value;
	}

	public List<Node> getNeighbours() {
		return this.neighbours;
	}

	public void addNeighbours(Node n) {
		this.neighbours.add(n);
		
	}

	public void removeNeighbours(Node n) {
		this.neighbours.remove(n);
		
	}
	
}