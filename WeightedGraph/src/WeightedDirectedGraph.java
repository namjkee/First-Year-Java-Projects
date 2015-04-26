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

	public List<Node> BFS(Node startNode) {
		List<Node> visited = new ArrayList<>();
		List<Node> toVisit = new ArrayList<>();
		List<Node> visitedOrder = new ArrayList<>();
		
		// starting at root node 
		visited.add(startNode);
		toVisit.add(startNode);		// begin visit here
		
		while (!toVisit.isEmpty()) {
			Node current = toVisit.remove(0);			// expand from the oldest/closest node
			for (Node child: this.getNodes()) {
				if (!visited.contains(child)) {
					visited.add(child);
					toVisit.add(child);					
				}
			}
			visitedOrder.add(current);
		}
		
		return visitedOrder;
	}
	
	public int findShortestPathCost(Node sourceNode, Node destinationNode) {
		List<Integer> currentShortestDistance = new ArrayList<Integer>();
        ArrayPriorityQueue pq = new ArrayPriorityQueue();
        
        int source = this.nodes.indexOf(sourceNode);
        int destination = this.nodes.indexOf(destinationNode);
        
        for (int n = 0; n < this.size(); n++) {
        	if (n == source) {
        		currentShortestDistance.add(0);
        	}
        	else {
        		currentShortestDistance.add(Integer.MAX_VALUE);
        	}
        }
        
        for (int n = 0; n < this.size(); n++) {
        	pq.insert(currentShortestDistance.get(n), n);
        }
        
        while (!pq.isEmpty()) {
        	// removing node at front of priority queue
        	int n = (Integer) pq.removeMin();
        	
        	// finding its neighbours by looping through its edges
        	List<Integer> nEdges = nodes.get(n).edges;
        	for (int m = 0; m < nEdges.size(); m++) {
        		if (nEdges.get(m) != 0) {
        			int shortestDistanceThroughN = currentShortestDistance.get(n) + nEdges.get(m);
        			if (shortestDistanceThroughN < currentShortestDistance.get(m)) {
        				currentShortestDistance.set(m, shortestDistanceThroughN);
        			}
        		}
        	}
        }
        
        return currentShortestDistance.get(destination);
	}
}
