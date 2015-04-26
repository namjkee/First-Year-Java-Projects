import java.util.ArrayList;
import java.util.List;


public class UndirectedGraph implements Graph {
	// instance variables
	private List<Node> nodes;
	
	public UndirectedGraph() {
		this.nodes = new ArrayList<>();
	}

	public int size() { return this.nodes.size(); }

	public boolean isEmpty() { return (nodes.size() == 0); }

	public List<Node> getNodes() {
		return this.nodes;
	}

	public void addNode(Node n) {
		this.nodes.add(n);
	}

	public void removeNode(Node n) {
		// remove edges assosciated with the node
		for (Node node: nodes) {
			node.removeNeighbours(n);
		}
		this.nodes.remove(n);
	}

	public void addEdge(Node source, Node destination) {
		// directed edge, so only link source to destination
		source.addNeighbours(destination);
		destination.addNeighbours(source);
	}
	
	// traversal
	public List<Node> BFS(Node startNode) {
		List<Node> visited = new ArrayList<>();
		List<Node> toVisit = new ArrayList<>();
		List<Node> visitedOrder = new ArrayList<>();
		
		// starting at root node 
		visited.add(startNode);
		toVisit.add(startNode);		// begin visit here
		
		while (!toVisit.isEmpty()) {
			Node current = toVisit.remove(0);		// expand from the oldest/closest node
			for (Node child: current.getNeighbours()) {
				if (!visited.contains(child)) {
					visited.add(child);
					toVisit.add(child);					
				}
			}
			visitedOrder.add(current);
		}
		
		return visitedOrder;
	}
	
	public List<Node> DFS(Node startNode) {
		List<Node> visited = new ArrayList<>();
		List<Node> toVisit = new ArrayList<>();
		List<Node> visitedOrder = new ArrayList<>();
		
		// starting at root node 
		visited.add(startNode);
		toVisit.add(startNode);		// begin visit here
		
		while (!toVisit.isEmpty()) {
			Node current = toVisit.remove(toVisit.size() - 1);		// expand from the oldest/closest node
			for (Node child: current.getNeighbours()) {
				if (!visited.contains(child)) {
					visited.add(child);
					System.out.println(child.getValue());
					toVisit.add(child);					
				}
			}
			visitedOrder.add(current);
		}
		
		return visitedOrder;
	}
		
}