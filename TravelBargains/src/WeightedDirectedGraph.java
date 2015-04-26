import java.util.ArrayList;
import java.util.List;


public class WeightedDirectedGraph implements WeightedGraph {
	// instance variables
	private List<Node> nodes;
	
	public WeightedDirectedGraph() {
		this.nodes = new ArrayList<>();
	}
	
	public int size() {
		return nodes.size();
	}

	public boolean isEmpty() {
		return (this.size() == 0);
	}
	
	public List<Node> getNodes() {
		return this.nodes;
	}

	public void addNode(Node n) {
		/* matrix is increased by 1 row and 1 column */
		
		// add each node to this node
		for (Node m: nodes) {
			n.edges.add(0);				// add m vertices (represents columns)
		}
		n.edges.add(0);					// add itself
		
		// each node is updated by another column (which is this node - default weight 0)
		for (Node m: nodes) {
			m.edges.add(0);	
		}
		
		// now we add to our accessable list
		nodes.add(n);		
	}

	public void removeNode(Node n) {
		int index = this.nodes.indexOf(n);
		
		for (Node m: nodes) {
			m.edges.remove(index);
		}
		
		// remove from data structure itself
		this.nodes.remove(index);
	}

	public void addEdge(Node source, Node destination, int weight) {
		int index = this.nodes.indexOf(destination);
		
		source.edges.set(index, weight);
	}
	
}
