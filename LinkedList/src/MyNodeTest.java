import static org.junit.Assert.*;

import org.junit.Test;


public class MyNodeTest {
	private Object data = new Object();
	private MyNode test = new MyNode(data);
	
	@Test
	public void testConstruction() {
		/* Test if data is correctly stored */
		assertEquals(data, test.getElement());
		
		/* Test if next pointer of object is null */
		assertNull(test.getNext());
	}
	
	@Test 
	public void testSetElement() {
		Object newData = new Object();
		
		/* Test the setElement method */
		test.setElement(newData);
		
		assertEquals(newData, test.getElement());
	}
	
	@Test
	public void testSetNext() {

		Object nextData = new Object();
		MyNode nextNode = new MyNode(nextData);
		
		/* Test the setNext method */
		test.setNext(nextNode);
		
		assertEquals(nextNode, test.getNext());
	}
	
	@Test
	public void testChaining() {
		Object nextData = new Object();
		MyNode nextNode = new MyNode(nextData);
		
		/* Set next pointer of test node to itself 'test' */
		test.setNext(test);
		
		assertEquals(test, test.getNext().getNext().getNext().getNext());
	}

}