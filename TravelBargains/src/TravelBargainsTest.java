import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class TravelBargainsTest {
	private WeightedGraph g; 
	private TravelBargains test;
	
	@Before
	public void setup() {
		this.g = new WeightedDirectedGraph();;
		
		Node Australia = new Node("Australia");
        Node UK = new Node("UK");
        Node USA = new Node("USA");
        Node Germany = new Node("Germany");
        Node France = new Node("France");
        
        g.addNode(Australia);
        g.addNode(UK);
        g.addNode(USA);
        g.addNode(Germany);
        g.addNode(France);
        
        g.addEdge(Australia, USA, 1128);
        g.addEdge(Australia, UK, 2239);
        g.addEdge(UK, France, 102);
        g.addEdge(UK, Australia , 2102);
        g.addEdge(USA , France , 930);
        g.addEdge(USA , Germany , 930);
	}
	
	@Test
	public void testTravelBargains() {
		test = new TravelBargains(g);
        
        assertEquals(null, test.getCheapestDestination("S"));
        assertEquals("USA", test.getCheapestDestination("Australia"));
        assertEquals("France", test.getCheapestDestination("UK"));
        assertEquals("Germany", test.getCheapestDestination("USA"));
        assertNull(test.getCheapestDestination("France"));
        WeightedGraph emptyG = new WeightedDirectedGraph();
        TravelBargains empty = new TravelBargains(emptyG);
        assertNull(empty.getCheapestDestination("SDFS"));
	}

}
