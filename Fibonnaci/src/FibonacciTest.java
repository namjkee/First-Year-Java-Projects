import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


public class FibonacciTest {
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void testZero() throws EmptyStackException {
		Stack<Integer> test = Fibonacci.getNumbers(0);
		assertEquals(true, test.isEmpty());
	}

	@Test
	public void testInclusiveNumbers() throws EmptyStackException {
		
		Stack<Integer> oneNumber = Fibonacci.getNumbers(1);
		assertEquals(new Integer(1), oneNumber.pop());
		
		Stack<Integer> twoNumbers = Fibonacci.getNumbers(2);
		assertEquals(new Integer(1), twoNumbers.pop());
		assertEquals(new Integer(1), twoNumbers.pop());
		
		Stack<Integer> test = Fibonacci.getNumbers(3);
		assertEquals(new Integer(2), test.pop());
		assertEquals(new Integer(1), test.pop());
		assertEquals(new Integer(1), test.pop());
		
		Stack<Integer> test2 = Fibonacci.getNumbers(4);
		assertEquals(new Integer(3), test2.pop());
		assertEquals(new Integer(2), test2.pop());
		assertEquals(new Integer(1), test2.pop());
		assertEquals(new Integer(1), test2.pop());
		
		Stack<Integer> test3 = Fibonacci.getNumbers(5);
		assertEquals(new Integer(5), test3.pop());
		assertEquals(new Integer(3), test3.pop());
		assertEquals(new Integer(2), test3.pop());
		assertEquals(new Integer(1), test3.pop());
		assertEquals(new Integer(1), test3.pop());
	}
	
	@Test
	public void aHundredNumbers() throws EmptyStackException {
		Stack<Integer> test = Fibonacci.getNumbers(100);
		for (int i = 0; i < 95; i++) {
			test.pop();
		}
		assertEquals(new Integer(5), test.pop());
		assertEquals(new Integer(3), test.pop());
		assertEquals(new Integer(2), test.pop());
		assertEquals(new Integer(1), test.pop());
		assertEquals(new Integer(1), test.pop());
		
	}

}
