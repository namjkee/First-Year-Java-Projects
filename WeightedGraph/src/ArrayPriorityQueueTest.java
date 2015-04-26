import static org.junit.Assert.*;

import java.util.Arrays;

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
	
	@Test
	public void testDecreaseKeys() {
		test.insert(1, "B");
		test.insert(5, "D");
		test.insert(4, "C");
		test.insert(-1, "A");
		
		test.decreaseKeys(4, 3);
		assertEquals("A", test.removeMin());
		assertEquals("B", test.removeMin());
		assertEquals("C", test.removeMin());
		assertEquals("D", test.removeMin());
		
		test.insert(1, "B");
		test.insert(5, "D");
		test.insert(4, "C");
		test.insert(-1, "A");
		
		test.decreaseKeys(-1, 3);
		assertEquals("B", test.removeMin());
		assertEquals("A", test.removeMin());
		assertEquals("C", test.removeMin());
		assertEquals("D", test.removeMin());
		
		test.insert(1, "B");
		test.insert(5, "D");
		test.insert(4, "C");
		test.insert(-1, "A");
		
		test.decreaseKeys(2, 3);
		assertEquals("A", test.removeMin());
		assertEquals("B", test.removeMin());
		assertEquals("C", test.removeMin());
		assertEquals("D", test.removeMin());
		
		test.insert(1, "B");
		test.insert(5, "D");
		test.insert(4, "C");
		test.insert(-1, "A");
		
		test.decreaseKeys(1, 5 - 1);
		test.decreaseKeys(5, 5 - 5);
		test.decreaseKeys(4, 5 - 4);
		test.decreaseKeys(-1, 5 - - 1);
		assertEquals("D", test.removeMin());
		assertEquals("C", test.removeMin());
		assertEquals("B", test.removeMin());
		assertEquals("A", test.removeMin());
		
	}
	
}
