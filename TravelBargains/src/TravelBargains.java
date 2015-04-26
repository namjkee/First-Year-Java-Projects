import java.util.List;
import java.util.Collections;

public class TravelBargains {
	// instance variables
	WeightedGraph graph = new WeightedDirectedGraph();
	
	
	/**
	 * Construct a new TravelBargains object with the given graph as the flight plan
	 */
	public TravelBargains(WeightedGraph g) {
		this.graph = g;
	}
	
	/**
	 * Given a country's name, find the name of the cheapest country you can fly directly to from here
	 */
	public String getCheapestDestination(String currentCountry) {
		for (Node node: graph.getNodes()) {
			if (node.value.equals(currentCountry)) {
				List<Integer> weightList = node.edges;
				
				// set all 0's to high value in a temp list to find index with min weight
				for (int i = 0; i < weightList.size(); i++) {
					if (weightList.get(i).equals(0)) weightList.set(i, Integer.MAX_VALUE);
				}
				
				if (Collections.min(weightList).equals(Integer.MAX_VALUE)) return null;
				int minIndex = weightList.indexOf(Collections.min(weightList));
				
				return  (String) graph.getNodes().get(minIndex).value;
			}
			// iterate till its found
		}
		
		// exit loop - didn't find country: invalid
		return null;
	}
}
