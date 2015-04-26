import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;


public class WeightedDirectedGraphTest {
	private WeightedDirectedGraph test = new WeightedDirectedGraph();
	
	@Test
	public void testConstruction() {
		WeightedDirectedGraph g = new WeightedDirectedGraph();
		assertEquals(0, g.size());
		assertEquals(Arrays.asList(), g.getNodes());
		assertTrue(g.isEmpty());
	}
	
	@Test
	public void testSize() {
		Node a = new Node(1);
		Node b = new Node(2);
		Node c = new Node(3);
		
		assertEquals(0, test.size());
		test.addNode(a);
		assertEquals(1, test.size());
		test.addNode(b);
		assertEquals(2, test.size());
		test.addNode(c);
		assertEquals(3, test.size());
		test.removeNode(a);
		assertEquals(2, test.size());
		test.removeNode(b);
		assertEquals(1, test.size());
		test.removeNode(c);
		assertTrue(test.isEmpty());
	}
	
	@Test
	public void testAddNode() {
		Node a = new Node(1);
		Node b = new Node(2);
		Node c = new Node(3);
		
		test.addNode(a);
		test.addNode(b);
		test.addNode(c);
		
		assertEquals(Arrays.asList(a, b, c), test.getNodes());
	}
	
	@Test
	public void testRemoveNode() {
		Node a = new Node(1);
		Node b = new Node(2);
		Node c = new Node(3);
		
		test.addNode(a);
		test.addNode(b);
		test.addNode(c);
		
		test.removeNode(b);
		
		assertEquals(Arrays.asList(a, c), test.getNodes());
		test.removeNode(a);
		test.removeNode(c);
		assertEquals(Arrays.asList(), test.getNodes());
	}
	
	@Test
	public void testSmallGraph() {
		WeightedGraph g = new WeightedDirectedGraph();
        Node A = new Node("A");
        Node B = new Node("B");
        Node C = new Node("C");
        Node D = new Node("D");
        Node E = new Node("E");
        
        g.addNode(A);
        g.addNode(B);
        g.addNode(C);
        g.addNode(D);
        g.addNode(E);
        
        g.addEdge(A, B, 2);
        g.addEdge(A, C, 5);
        g.addEdge(B, D, 3);
        g.addEdge(C, D, 6);
        g.addEdge(D, E, 9);
        
        assertEquals(Arrays.asList(0, 2, 5, 0, 0), A.edges);
        assertEquals(Arrays.asList(0, 0, 0, 3, 0), B.edges);
        assertEquals(Arrays.asList(0, 0, 0, 6, 0), C.edges);
        assertEquals(Arrays.asList(0, 0, 0, 0, 9), D.edges);
        assertEquals(Arrays.asList(0, 0, 0, 0, 0), E.edges);
        
        // TEST BFS
        assertEquals(Arrays.asList(A, B, C, D, E), g.BFS(A));
        WeightedGraph abc = new WeightedDirectedGraph();
        Node one = new Node("A");
        assertEquals(Arrays.asList(one), abc.BFS(one));
	}
	
	@Test
	public void testFindShortestPathCost() {
		Node A = new Node("A");
        Node B = new Node("B");
        
        test.addNode(A);
        test.addNode(B);
        
        test.addEdge(A, B, 1);
        assertEquals(1, test.findShortestPathCost(A, B));
	}
	
	@Test
	public void testFindShortestPathCost2() {
		Node A = new Node("A");
        Node B = new Node("B");
        
        test.addNode(A);
        test.addNode(B);
        
        test.addEdge(A, B, 1);
        assertEquals(Integer.MAX_VALUE, test.findShortestPathCost(B, A));
	}
	
	@Test
	public void testFindShortestPath3() {
        Node A = new Node("A");
        Node B = new Node("B");
        Node C = new Node("C");
        Node D = new Node("D");
        Node E = new Node("E");
        
        test.addNode(A);
        test.addNode(B);
        test.addNode(C);
        test.addNode(D);
        test.addNode(E);
        
        test.addEdge(A, B, 2);
        test.addEdge(A, C, 5);
        test.addEdge(B, D, 3);
        test.addEdge(C, D, 6);
        test.addEdge(D, E, 9);
        
        assertEquals(14, test.findShortestPathCost(A, E));
	}
}
