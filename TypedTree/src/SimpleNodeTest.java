import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;


public class SimpleNodeTest {
	Node test = new SimpleNode(3);

	@Test
	public void testConstruction() {
		
		assertEquals(3, test.getElement());
		assertEquals(null, test.getParent());
		assertEquals(new ArrayList<Node>(), test.getChildren());
	}
	
	@Test
	public void testSetParent() {
		Node testParent = new SimpleNode(5);
		test.setParent(testParent);
		
		assertSame(testParent, test.getParent());		/* assertSame is used over assertEquals, because AssertSame checks if two reference variables or methods, points
														 * to the same object in memory, assertEquals checks by equality which isn't preferred */
	}
	
	@Test
	public void testSetElement() {
		test.setElement(5);
		assertEquals(5, test.getElement());
	}
	
	@Test
	public void testSetChildren() {
		Node child1 = new SimpleNode(1);
		Node child2 = new SimpleNode(2);
		Node child3 = new SimpleNode(3);
		Node child4 = new SimpleNode(4);
		Node child5 = new SimpleNode(5);
		
		test.addChild(child1);
		test.addChild(child2);
		
		assertTrue(test.getChildren().contains(child1));
		assertTrue(test.getChildren().contains(child2));
		
		test.removeChild(child1);
		assertTrue(test.getChildren().contains(child2));
		
	}

}
