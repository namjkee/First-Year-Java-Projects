import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


public class PalindromesTest {

	@Rule
	public ExpectedException exception = ExpectedException.none();
	/*
	@Test
	public void testEmptyString() throws EmptyQueueException, EmptyStackException {
		assertEquals(true , Palindromes.isPalindrome(""));
	}
	
	@Test
	public void testBasicPalindrome() throws EmptyQueueException, EmptyStackException {
		assertEquals(true, Palindromes.isPalindrome("eye"));
		assertEquals(true, Palindromes.isPalindrome("racecar"));
	}
	
	@Test
	public void testBasicNonPalindrome() throws EmptyQueueException, EmptyStackException {
		assertEquals(false, Palindromes.isPalindrome("hello"));
		assertEquals(false, Palindromes.isPalindrome("book"));
	}
	
	@Test
	public void testCase() throws EmptyQueueException, EmptyStackException {
		assertEquals(true, Palindromes.isPalindrome("EyE"));
		assertEquals(false, Palindromes.isPalindrome("Eye"));
	}
	
	@Test
	public void testBasicPalindromeSentence() throws EmptyQueueException, EmptyStackException {
		assertEquals(true, Palindromes.isPalindromeSentence("Madam, I'm Adam"));
		assertEquals(true, Palindromes.isPalindromeSentence("Never odd or even"));
		assertEquals(false, Palindromes.isPalindromeSentence("Hello world"));
		assertEquals(true, Palindromes.isPalindromeSentence("Eye"));
	}
	
	@Test
	public void testSingleCharacter() throws EmptyQueueException, EmptyStackException {
		assertEquals(true, Palindromes.isPalindrome("s"));
		assertEquals(true, Palindromes.isPalindromeSentence("s"));
	}
	*/
	
	// PASTA Test
	@Test
	public void test() throws EmptyQueueException, EmptyStackException {
		assertTrue(Palindromes.isPalindromeSentence("Madam, I'm Adam"));
		assertTrue(Palindromes.isPalindromeSentence("Never odd or even"));
		assertTrue(Palindromes.isPalindromeSentence(" , .| , "));
		assertFalse(Palindromes.isPalindromeSentence("Hello world"));
		assertTrue(Palindromes.isPalindromeSentence("Eye"));
		
	}
	
}
