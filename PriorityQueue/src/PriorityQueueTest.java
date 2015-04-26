import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class PriorityQueueTest {
	PriorityQueue test = new ArrayPriorityQueue();

	@Test
	public void testConstruction() {
		PriorityQueue construct = new ArrayPriorityQueue();
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

}
