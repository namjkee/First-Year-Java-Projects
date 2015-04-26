import static org.junit.Assert.*;

import org.junit.Test;


public class HeapPriorityQueueTest {

	HeapPriorityQueue test = new HeapPriorityQueue();

	@Test
	public void testConstruction() {
		HeapPriorityQueue construct = new HeapPriorityQueue();
		assertTrue(construct.isEmpty());
		assertEquals(0, construct.size());
	}	
	
	@Test
	public void testRemoveMin() {
		Object A = new Object();
		Object B = new Object();
		Object C = new Object();
		Object D = new Object();
		test.insert(1, B);
		test.insert(5, D);
		test.insert(4, C);
		test.insert(-1, A);
		
		assertSame(A, test.removeMin());
		assertSame(B, test.removeMin());
		assertSame(C, test.removeMin());
		assertSame(D, test.removeMin());
	}
	
	@Test
	public void testmin() {
		Object A = new Object();
		Object B = new Object();
		Object C = new Object();
		Object D = new Object();
		test.insert(-1, A);
		test.insert(1,B);
		test.insert(5,D);
		test.insert(4,C);
		
		assertEquals(A, test.min());
		test.removeMin();
		assertEquals(B, test.min());
		test.removeMin();
		assertEquals(C, test.min());
		test.removeMin();
		assertEquals(D, test.min());
		
		// returns A,B,C,D.
	}

}
