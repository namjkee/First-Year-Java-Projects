import static org.junit.Assert.*;

import org.junit.Test;


public class BinaryTreeTest {
	BinaryTree test = new BinaryTree();

	@Test
	public void testConstruction() {
		BinaryTree tree = new BinaryTree();
		assertNull(tree.root);
		assertEquals(0, tree.size());
		assertTrue(tree.isEmpty());
	}
	
	@Test
	public void testSizeAndIsEmpty() {
		assertEquals(0, test.size());
		assertTrue(test.isEmpty());
		test.insert(5);
		assertFalse(test.isEmpty());
		assertEquals(1, test.size());
		test.insert(3);
		assertFalse(test.isEmpty());
		assertEquals(2, test.size());
		test.insert(7);
		assertFalse(test.isEmpty());
		assertEquals(3, test.size());
	}
	
	@Test
	public void testContains() {
		test.insert(5);
		test.insert(3);
		test.insert(7);
		assertTrue(test.contains(7));
		assertFalse(test.contains(6));
		test.insert(7);
		test.insert(7);
		assertTrue(test.contains(7));
		assertFalse(test.contains(6));
		
	}
	
	@Test
	public void testTraversals() {
		test.insert(5);
		test.insert(3);
		test.insert(7);
		test.insert(2);
		test.insert(4);
		test.insert(6);
		test.insert(8);
		test.insert(1);
		test.insert(9);
		System.out.println(test.preOrderTraversal(test.root));
		System.out.println(test.postOrderTraversal(test.root));
		System.out.println(test.inOrderTraversal(test.root));
	}
	
	@Test
	public void testOneNodeTraversal() {
		test.insert(5);
		System.out.println(test.preOrderTraversal(test.root));
		System.out.println(test.inOrderTraversal(test.root));
		System.out.println(test.postOrderTraversal(test.root));
	}
	
	@Test
	public void testEmptyTraversal() {
		System.out.println(test.preOrderTraversal(test.root));
		System.out.println(test.inOrderTraversal(test.root));
		System.out.println(test.postOrderTraversal(test.root));
	}

}
