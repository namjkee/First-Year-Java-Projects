import java.util.ArrayList;
import java.util.List;

public class Node {
	// the value in the node itself
	public Object value;
	
	public List<Integer> edges;
	
	public Node(Object value) {
		this.value = value;
		this.edges = new ArrayList<Integer>();
	}
	
}
