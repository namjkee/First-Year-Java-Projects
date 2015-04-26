import static org.junit.Assert.*;

import org.junit.Test;


public class ArrayHeapTest {
	Heap test = new ArrayHeap();

	@Test
	public void testConstruction() {
		Heap h = new ArrayHeap();
		assertTrue(h.isEmpty());
		assertEquals(0, h.size());
	}
	
	@Test
	public void testSmallHeap() {
		test.insert(2, "A");
		test.insert(5, "B");
		test.insert(8, "D");
		test.insert(9, "E");
		test.insert(6, "C");
		
		assertEquals(5, test.size());
		assertFalse(test.isEmpty());
		assertEquals("A", test.removeRoot());
		assertFalse(test.isEmpty());
		assertEquals(4, test.size());
		assertEquals("B", test.removeRoot());
		assertFalse(test.isEmpty());
		assertEquals(3, test.size());
		assertEquals("C", test.removeRoot());
		assertFalse(test.isEmpty());
		assertEquals(2, test.size());
		assertEquals("D", test.removeRoot());
		assertFalse(test.isEmpty());
		assertEquals(1, test.size());
		assertEquals("E", test.removeRoot());
		assertEquals(0, test.size());
		assertTrue(test.isEmpty());
	}

}
