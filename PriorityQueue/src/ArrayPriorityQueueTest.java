import static org.junit.Assert.*;

import org.junit.Test;


public class ArrayPriorityQueueTest {
	ArrayPriorityQueue test = new ArrayPriorityQueue();
	
	@Test
	public void testConstructor() {
		assertEquals(0, test.size());
		assertTrue(test.isEmpty());
	}
	
	@Test
	public void testRemoveMin() {
		test.insert(1, "B");
		test.insert(5, "D");
		test.insert(4, "C");
		test.insert(-1, "A");
		
		assertEquals("A", test.removeMin());
		assertEquals("B", test.removeMin());
		assertEquals("C", test.removeMin());
		assertEquals("D", test.removeMin());
		assertTrue(test.isEmpty());
		assertEquals(0, test.size());
	}
	
	@Test
	public void testMin() {
		test.insert(1, "B");
		test.insert(5, "D");
		test.insert(4, "C");
		test.insert(-1, "A");
		
		assertEquals("A", test.min());
		test.removeMin();
		
		assertEquals("B", test.min());
		test.removeMin();
		
		assertEquals("C", test.min());
		test.removeMin();
		
		assertEquals("D", test.min());
		test.removeMin();
		
		
		assertTrue(test.isEmpty());
		assertEquals(0, test.size());
	}
	
}
