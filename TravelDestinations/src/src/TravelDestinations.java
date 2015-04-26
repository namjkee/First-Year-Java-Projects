import java.util.ArrayList;
import java.util.List;


public class TravelDestinations {
	// instance variables
	private DirectedGraph graph;
	
	public TravelDestinations(DirectedGraph g) {
		this.graph = g;
	}
	
	/**
	 * Returns all the countries that are a single flight away from the given country, in any order
	 */
	public List<String> getAvailableCountries(String fromCountry) {
		List<String> list = new ArrayList<>();
		
		for (Node node: graph.getNodes()) {
			if (node.getValue().equals(fromCountry)) {
				for (Node n: node.getNeighbours()) {
					list.add((String) n.getValue());
				}
			}
		}
		
		return list;
	}
	
	/**
	 * Returns whether there is a flight from 'fromCountry' to 'toCountry'
	 */
	public boolean canFlyTo(String fromCountry, String toCountry) {
		List<String> countries = this.getAvailableCountries(fromCountry);
		if (countries.contains(toCountry)) return true;
		else return false;
	}
	
	 /**
     * Returns all the countries that are reachable from the given country, with any number of flights
     */
    public List<String> getAllAvailableCountries(String country) {
    	List<String> list = new ArrayList<>();
    	
    	for (Node node: graph.getNodes()) {
    		if (node.getValue().equals(country)) {
    			for (Node n: graph.DFS(node)) {
    				list.add((String) n.getValue());
    			}
    		}
    	}
		
    	// remove argument if in list
		if (list.contains(country)) list.remove(country);
		
		return list;
    }
    
    /**
     * Returns the country ('destinationA' or 'destinationB') that has fewer flights to get to from country 'current'
     */
    public String getCheaperDestination(String current, String destinationA, String destinationB) {
    	List<String> list = new ArrayList<>();
    	
    	for (Node node: graph.getNodes()) {
    		if (node.getValue().equals(current)) {
    			for (Node n: graph.BFS(node)) {
    				list.add((String) n.getValue());
    			}
    		}
    	}
		
    	if (!(list.contains(destinationA) || list.contains(destinationB))) return null;
    	int destA = list.indexOf(destinationA);
    	if (!list.contains(destinationA)) destA = Integer.MAX_VALUE;
    	int destB = list.indexOf(destinationB);
    	if (!list.contains(destinationB)) destB = Integer.MAX_VALUE;
    	
		// compare
		if (destA < destB) return destinationA;
		else return destinationB;
    }
	
}
