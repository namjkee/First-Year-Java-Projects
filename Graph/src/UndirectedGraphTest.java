import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;


public class UndirectedGraphTest {
private UndirectedGraph test = new UndirectedGraph();
	
	@Test
	public void testConstruction() {
		UndirectedGraph abc = new UndirectedGraph();
		assertEquals(0, abc.size());
		assertTrue(abc.isEmpty());
		assertEquals(Arrays.asList(), abc.getNodes());
	}
	
	@Test
	public void testCombined() {
		UndirectedGraphNode a = new UndirectedGraphNode("A");
		UndirectedGraphNode b = new UndirectedGraphNode("B");
		UndirectedGraphNode c = new UndirectedGraphNode("C");
		test.addNode(a);
		assertEquals(1, test.size());
		test.addNode(b);
		assertEquals(2, test.size());
		test.addNode(c);
		assertEquals(3, test.size());
		assertEquals(Arrays.asList(a,b,c), test.getNodes());
		test.removeNode(a);
//		System.out.println(test.getNodes());
		assertEquals(2, test.size());
		test.removeNode(b);
//		System.out.println(test.getNodes());
		assertEquals(1, test.size());
		test.removeNode(c);
//		System.out.println(test.getNodes());
		assertEquals(0, test.size());
		assertTrue(test.isEmpty());
	}
	
	@Test
	public void smallGraph() {
	   Node A = new UndirectedGraphNode("A");
       Node B = new UndirectedGraphNode("B");
       Node C = new UndirectedGraphNode("C");
       Node D = new UndirectedGraphNode("D");
       Node E = new UndirectedGraphNode("E");
       
       test.addNode(A);
       test.addNode(B);
       test.addNode(C);
       test.addNode(D);
       test.addNode(E);
       
       test.addEdge(A, B);
       assertEquals(Arrays.asList(B), A.getNeighbours());
       test.addEdge(A, C);
       assertEquals(Arrays.asList(B, C), A.getNeighbours());
       test.addEdge(B, D);
       assertEquals(Arrays.asList(A, D), B.getNeighbours());
       test.addEdge(C, D);
       assertEquals(Arrays.asList(A, D), C.getNeighbours());
       test.addEdge(D, E);
       assertEquals(Arrays.asList(B, C, E), D.getNeighbours());
       assertEquals(Arrays.asList(D), E.getNeighbours());
	}
}
