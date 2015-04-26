
public interface Tree {
	
	public int size();
	
	public boolean isEmpty();
	
	public void setRoot(Node root);
	
	public Node getRoot();
	
	public void insert(Node parent, Node child);
	
	public void remove(Node node);
}
