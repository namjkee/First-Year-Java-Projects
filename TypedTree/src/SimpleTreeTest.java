import static org.junit.Assert.*;

import org.junit.Test;


public class SimpleTreeTest {
	Tree test = new SimpleTree();
	
	@Test
	public void testConstruction() {
		assertNull(test.getRoot());
		assertEquals(0, test.size());
		assertTrue(test.isEmpty());
	}
	
	@Test
	public void testSetRoot() {
		Node root = new SimpleNode(10);
		test.setRoot(root);
		assertFalse(test.isEmpty());
		assertEquals(1, test.size());
		assertEquals(root, test.getRoot());
	}
	
	@Test
	public void testGetSize() {
		 Node A = new SimpleNode('A');
         Node B = new SimpleNode('B');
         Node C = new SimpleNode('C');
         
         test.setRoot(A);
         test.insert(A, B);
         test.insert(A, C);
         
         assertEquals(3, test.size());
         
         test.remove(C);
         assertEquals(2, test.size());
         
         test.insert(A, C);
         test.remove(A);
         assertEquals(0, test.size());
         assertTrue(test.isEmpty());
         
	}
	
	@Test
	public void testIsEmpty() {
		Node A = new SimpleNode('A');
        Node B = new SimpleNode('B');
        test.setRoot(A);					// If no root, i.e root = null, then size is 0 or list is empty
        test.insert(A, B);
        assertFalse(test.isEmpty());
        assertEquals(2, test.size());
        
        test.remove(A);
        assertTrue(test.isEmpty());
      
	}

}
