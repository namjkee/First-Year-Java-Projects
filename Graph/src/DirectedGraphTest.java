import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;


public class DirectedGraphTest {
	private DirectedGraph test = new DirectedGraph();
	
	@Test
	public void testConstruction() {
		DirectedGraph abc = new DirectedGraph();
		assertEquals(0, abc.size());
		assertTrue(abc.isEmpty());
		assertEquals(Arrays.asList(), abc.getNodes());
	}
	
	@Test
	public void testCombined() {
		DirectedGraphNode a = new DirectedGraphNode("A");
		DirectedGraphNode b = new DirectedGraphNode("B");
		DirectedGraphNode c = new DirectedGraphNode("C");
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
	   Node A = new DirectedGraphNode("A");
       Node B = new DirectedGraphNode("B");
       Node C = new DirectedGraphNode("C");
       Node D = new DirectedGraphNode("D");
       Node E = new DirectedGraphNode("E");
       
       test.addNode(A);
       test.addNode(B);
       test.addNode(C);
       test.addNode(D);
       test.addNode(E);
       
       test.addEdge(A, B);
       assertEquals(Arrays.asList(B), A.getNeighbours());
       test.addEdge(A, C);
       assertEquals(Arrays.asList(B,C), A.getNeighbours());
       test.addEdge(B, D);
       assertEquals(Arrays.asList(D), B.getNeighbours());
       test.addEdge(C, D);
       assertEquals(Arrays.asList(D), C.getNeighbours());
       test.addEdge(D, E);
       assertEquals(Arrays.asList(E), D.getNeighbours());
	}
	
	@Test
	public void testBFS() {
	   Node A = new DirectedGraphNode("A");
       Node B = new DirectedGraphNode("B");
       Node C = new DirectedGraphNode("C");
       Node D = new DirectedGraphNode("D");
       Node E = new DirectedGraphNode("E");
       
       // single node BFS
       test.addNode(A);
       assertEquals(Arrays.asList(A), test.BFS(A));
       
       test.addNode(B);
       test.addNode(C);
       test.addNode(D);
       test.addNode(E);
       
       test.addEdge(A, B);
       test.addEdge(A, C);
       test.addEdge(B, D);
       test.addEdge(C, D);
       test.addEdge(D, E);
       
       assertEquals(Arrays.asList(A, B, C, D, E), test.BFS(A));
	}
	
	@Test
	public void testDFS() {
		 Node A = new DirectedGraphNode("A");
	       Node B = new DirectedGraphNode("B");
	       Node C = new DirectedGraphNode("C");
	       Node D = new DirectedGraphNode("D");
	       Node E = new DirectedGraphNode("E");
	       
	       // single node BFS
	       test.addNode(A);
	       assertEquals(Arrays.asList(A), test.DFS(A));
	       
	       test.addNode(B);
	       test.addNode(C);
	       test.addNode(D);
	       test.addNode(E);
	       
	       test.addEdge(A, B);
	       test.addEdge(A, C);
	       test.addEdge(B, D);
	       test.addEdge(C, D);
	       test.addEdge(D, E);
	       
	       assertEquals(Arrays.asList(A, C, D, E, B), test.DFS(A));
	}
}
