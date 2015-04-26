import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
 
public class ArrayStringStackTest {
	Stack<String> test = new ArrayStringStack(5);
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void testConstruction() throws EmptyStackException {
		Stack<String> theStack = new ArrayStringStack(5);
		assertEquals(0, theStack.size());
		assertEquals(true, theStack.isEmpty());
		
		/* Exception Handling */
		exception.expect(EmptyStackException.class);
		theStack.top();
		theStack.pop();
		/* 					*/
	}
	
	@Test
	public void testSize() {
		assertEquals(0, test.size());
		test.push("A");
		assertEquals(1, test.size());
		test.push("B");
		assertEquals(2, test.size());
		test.push("C");
		assertEquals(3, test.size());
	}
	
	@Test
	public void testIsEmpty() throws EmptyStackException {
		test.push("A");
		test.push("B");
		test.pop();
		test.pop();
		assertEquals(0, test.size());
		assertEquals(true, test.isEmpty());
	}
	
	@Test
	public void testPushAndPop() throws EmptyStackException {
		test.push("A");
		test.push("B");
		test.push("C");
		assertEquals("C", test.pop());
		assertEquals("B", test.pop());
		assertEquals("A", test.pop());
		
		/* Exception Test */
		exception.expect(EmptyStackException.class);
		test.pop();
		/* 				*/
	}
	
	@Test
	public void testTop() throws EmptyStackException {
		test.push("A");
		test.push("B");
		test.push("C");
		assertEquals("C" , test.top());
	}
	
	@Test
	public void test() throws EmptyStackException {
		test.push("e");
		test.push("y");
		test.push("e");
		test.pop();
		assertEquals("y", test.top());
	}
	
	@Test
	public void testMaxLimit() throws EmptyStackException {
		test.push("e");
		test.push("y");
		test.push("e");
		test.push("d");
		test.push("s");
		assertEquals(5, test.size());
		test.push("z");
		assertEquals("z", test.top());
	}

}