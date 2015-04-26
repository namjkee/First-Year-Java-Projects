import static org.junit.Assert.*;

import org.junit.Test;


public class PhoneBookTest {
	private PhoneBook test = new PhoneBook();

	@Test
	public void testCaseOne() {
		assertNull(test.getNumber("Steve"));
		assertNull(test.getNumber("Dave"));
		assertNull(test.getNumber("Bob"));
	}
	
	@Test
	public void testCaseTwo() {
		test.addNumber("Joe", 92450184);
		assertEquals((Integer) 92450184, test.getNumber("Joe"));
	}
	
	@Test
	public void testCaseThree() {
		test.addNumber("Joe", 92450184);
		test.addNumber("Joe", 91112222);
		assertEquals((Integer) 91112222,test.getNumber("Joe"));
	}
	
	@Test
	public void testCaseFour() {
		test.addNumber("Joe", 92450184);
		test.deleteContact("Joe");
		assertNull(test.getNumber("Joe"));
	}

}
