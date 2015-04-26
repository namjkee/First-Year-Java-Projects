import java.util.ArrayList;
import java.util.List;
    
public class Friendships {
	// instance variables
	UndirectedGraph graph;
	
    public Friendships() {
    	this.graph = new UndirectedGraph();
	}
    
    /**
     * Adds a person to the system with the given name
     */
    public void addPerson(String name) {
    	this.graph.addNode(new UndirectedGraphNode(name));
	}
    
    /**
     * States that personA and personB are friends
     */
    public void setFriends(String personA, String personB) {
    	for (Node node: graph.getNodes()) {
    		if (node.getValue().equals(personA)) {
    			for (Node n: graph.getNodes()) {
    				if (n.getValue().equals(personB)) {
    					this.graph.addEdge(node, n);
    				}
    			}
    		}
    	}
	}
    
    /**
     * Retrieves a list of names of the given person's direct friends
     */
    public List<String> getFriends(String name) {
    	List<String> list = new ArrayList<>();
    	
		for (Node node: graph.getNodes()) {
			if (node.getValue().equals(name)) {
				for (Node n: node.getNeighbours()) {
					list.add((String) n.getValue());
				}
			}
		}
		if (list.isEmpty()) return null;
		return list;
	}
    
    /**
     * Retrieves a list of names of all the given person's friends (direct or indirect)
     */
    public List<String> getAllFriends(String name) {
    	List<String> list = new ArrayList<>();
    	
    	for (Node node: graph.getNodes()) {
    		if (node.getValue().equals(name)) {
    			for (Node n: graph.DFS(node)) {
    				list.add((String) n.getValue());
    			}
    		}
    	}
    	
    	if (list.contains(name)) list.remove(name);
    	if (list.isEmpty()) return null;
    	return list;
    }
}