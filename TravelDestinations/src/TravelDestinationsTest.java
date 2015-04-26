import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;


public class TravelDestinationsTest {
	private DirectedGraph g = new DirectedGraph();
	private TravelDestinations test = new TravelDestinations(g);

	@Test
	public void testCases() {
		Node Australia = new DirectedGraphNode("Australia");
		Node USA = new DirectedGraphNode("USA");
		Node UK = new DirectedGraphNode("UK");
		Node France = new DirectedGraphNode("France");
		Node Germany = new DirectedGraphNode("Germany");
		
		this.g.addNode(Australia);
		this.g.addNode(USA);
		this.g.addNode(UK);
		this.g.addNode(France);
		this.g.addNode(Germany);
		
		this.g.addEdge(Australia, USA);
		this.g.addEdge(Australia, UK);
		this.g.addEdge(UK, France);
		this.g.addEdge(UK, Australia);
		this.g.addEdge(USA, France);
		this.g.addEdge(USA, Germany);
		
		
        List<String> neighbours = test.getAvailableCountries("Australia");
        assertEquals(2, neighbours.size());
        assertTrue(neighbours.contains("USA"));
        assertTrue(neighbours.contains("UK"));
        
        assertTrue(test.canFlyTo("Australia", "USA"));
        assertTrue(test.canFlyTo("Australia", "UK"));
        assertTrue(test.canFlyTo("UK", "France"));
        assertTrue(test.canFlyTo("UK", "Australia"));
        assertTrue(test.canFlyTo("USA", "France"));
        assertTrue(test.canFlyTo("USA", "Germany"));
        
        assertFalse(test.canFlyTo("New Zealand", "USA"));
        assertFalse(test.canFlyTo("Germany", "USA"));
        assertFalse(test.canFlyTo("USA", "USA"));
		
	}
	
	@Test
	public void testgetAllAvailableCountries() {
		Node Australia = new DirectedGraphNode("Australia");
		Node USA = new DirectedGraphNode("USA");
		Node UK = new DirectedGraphNode("UK");
		Node France = new DirectedGraphNode("France");
		Node Germany = new DirectedGraphNode("Germany");
		
		this.g.addNode(Australia);
		this.g.addNode(USA);
		this.g.addNode(UK);
		this.g.addNode(France);
		this.g.addNode(Germany);
		
		this.g.addEdge(Australia, USA);
		this.g.addEdge(Australia, UK);
		this.g.addEdge(UK, France);
		this.g.addEdge(UK, Australia);
		this.g.addEdge(USA, France);
		this.g.addEdge(USA, Germany);
		
		List<String> result1 = test.getAllAvailableCountries("Australia");
		assertEquals(4, result1.size());
		assertTrue(result1.contains("USA"));
		assertTrue(result1.contains("Germany"));
		assertTrue(result1.contains("UK"));
		assertTrue(result1.contains("France"));
		
		List<String> result2 = test.getAllAvailableCountries("USA");
		assertEquals(2, result2.size());
		assertTrue(result2.contains("Germany"));
		assertTrue(result2.contains("France"));
		
		List<String> result3 = test.getAllAvailableCountries("UK");
		assertEquals(4, result3.size());
		assertTrue(result3.contains("France"));
		assertTrue(result3.contains("Australia"));
		assertTrue(result3.contains("USA"));
		assertTrue(result3.contains("Germany"));
		
		List<String> result5 = test.getAllAvailableCountries("UKA");
		assertEquals(Arrays.asList(), result5);
		
	}
	
	@Test
	public void cheaperDestination() {
		Node Australia = new DirectedGraphNode("Australia");
		Node USA = new DirectedGraphNode("USA");
		Node UK = new DirectedGraphNode("UK");
		Node France = new DirectedGraphNode("France");
		Node Germany = new DirectedGraphNode("Germany");
		
		this.g.addNode(Australia);
		this.g.addNode(USA);
		this.g.addNode(UK);
		this.g.addNode(France);
		this.g.addNode(Germany);
		
		this.g.addEdge(Australia, USA);
		this.g.addEdge(Australia, UK);
		this.g.addEdge(UK, France);
		this.g.addEdge(UK, Australia);
		this.g.addEdge(USA, France);
		this.g.addEdge(USA, Germany);
		
		assertEquals("USA", test.getCheaperDestination("Australia", "USA", "France"));
		assertEquals("Germany", test.getCheaperDestination("USA", "Australia", "Germany"));
		assertEquals(null, test.getCheaperDestination("USA", "Australia", "UK"));
		assertEquals(null, test.getCheaperDestination("c", "a", "b"));
		String result = test.getCheaperDestination("Australia", "USA", "UK");
		assertTrue(result.equals("USA") || result.equals("UK"));
	}

}
