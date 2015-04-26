import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


public class BasicQueueTest {
	Queue<String> test = new BasicQueue<String>();
	
	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void testConstruction() throws EmptyQueueException {
		Queue<String> theQueue = new BasicQueue<String>();
		assertEquals(0, theQueue.size());
		assertEquals(true, theQueue.isEmpty());
		
		/* Exception Handling */
		exception.expect(EmptyQueueException.class);
		theQueue.front();
		theQueue.dequeue();
		/*  				 */
	}
	
	@Test
	public void testSize() {
		assertEquals(0, test.size());
		test.enqueue("A");
		assertEquals(1, test.size());
		test.enqueue("B");
		assertEquals(2, test.size());
		test.enqueue("C");
		assertEquals(3, test.size());
	}
	
	@Test
	public void testIsEmpty() throws EmptyQueueException {
		test.enqueue("A");
		test.enqueue("B");
		test.dequeue();
		test.dequeue();
		assertEquals(0, test.size());
		assertEquals(true, test.isEmpty());
	}
	
	@Test
	public void testPushAndPop() throws EmptyQueueException {
		test.enqueue("A");
		test.enqueue("B");
		test.enqueue("C");
		assertEquals("A", test.dequeue());
		assertEquals("B", test.dequeue());
		assertEquals("C", test.dequeue());
		
		/* Exception Test */
		exception.expect(EmptyQueueException.class);
		test.dequeue();
		/* 				*/
	}
	
	@Test
	public void testTop() throws EmptyQueueException {
		test.enqueue("A");
		test.enqueue("B");
		test.enqueue("C");
		assertEquals("A" , test.front());
	}

}
