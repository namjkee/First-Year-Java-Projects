import static org.junit.Assert.*;

import org.junit.Test;


public class MyLinkedListTest {
	private MyLinkedList test = new MyLinkedList();
 	Object one = new Object();
	Object two = new Object();
	Object three = new Object();
	
	@Test
	public void testConstruction() {
		/* Test size of this new LinkedList */
		assertEquals(0, test.size());
		assertEquals(true, test.isEmpty());
		assertEquals(null, test.get(0));
	}
	
	@Test
	public void testSize() {
		test.add(one);
		test.add(two);
		test.add(three);
		
		/* Test after add */
		assertEquals(3, test.size());
		
		/* Test after remove */
		test.remove(three);
		assertEquals(2, test.size());
		
		test.remove(two);
		assertEquals(1, test.size());
		
		test.remove(one);
		assertEquals(0, test.size());
	}
	
	@Test
	public void testIsEmpty() {
		test.add(one);
		assertEquals(false, test.isEmpty());
		
		test.remove(one);
		assertEquals(true, test.isEmpty());
	}
	
	@Test
	public void testGet() {
		test.add(one);
		test.add(two);
		test.add(three);
		
		assertEquals(one, test.get(0));
		assertEquals(three, test.get(2));
		assertEquals(two, test.get(1));
		
		assertEquals(null, test.get(3));
	}
	
	@Test
	public void testRemove() {
		test.add(one);
		test.add(two);
		test.add(three);
		
		/* Removing an element that doesn't exist */
		Object four = new Object();
		test.remove(four);
		
		/* Removing the head means the node after it takes it's reference */
		test.remove(one);
		assertEquals(2, test.size());
		assertEquals(false, test.isEmpty());
		assertEquals(two, test.get(0));
		
		test.remove(three);
		assertEquals(1, test.size());
		assertEquals(false, test.isEmpty());
		assertEquals(two, test.get(0));
		
		test.remove(two);
		assertEquals(0, test.size());
		assertEquals(true, test.isEmpty());
		assertEquals(null, test.get(0));
	}
}
