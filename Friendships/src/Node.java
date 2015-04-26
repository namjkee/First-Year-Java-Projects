import java.util.List;


public interface Node {
	
	public void setValue(Object value);
	
	public Object getValue();
	
	public List<Node> getNeighbours();
	
	public void addNeighbours(Node n);
	
	public void removeNeighbours(Node n);
	
}
