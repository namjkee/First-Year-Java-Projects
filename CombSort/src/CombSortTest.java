import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;


public class CombSortTest {

	@Test
	public void testShortList() {
		List<Integer> one = new ArrayList<Integer>(Arrays.asList(2, 3, 1, 5, 4));
		CombSort.sort(one);
		assertEquals(Arrays.asList(1, 2, 3, 4 , 5), one);
	}
	
	@Test
	public void testReversedList() {
		List<Integer> two = new ArrayList<Integer>(Arrays.asList(5, 4, 3, 2, 1));
		CombSort.sort(two);
		assertEquals(Arrays.asList(1, 2, 3, 4, 5), two);
	}
	
	@Test
	public void testEmptyList() {
		List<Integer> three = new ArrayList<Integer>();
		CombSort.sort(three);
		assertEquals(Arrays.asList(), three);
	}
	
	@Test
	public void testSingleElement() {
		List<Integer> four = new ArrayList<Integer>(Arrays.asList(1));
		CombSort.sort(four);
		assertEquals(Arrays.asList(1), four);
	}
	
	@Test
	public void testTies() {
		List<Integer> five = new ArrayList<Integer>(Arrays.asList(4, 2, 3, 2, 1));
		CombSort.sort(five);
		assertEquals(Arrays.asList(1, 2, 2, 3, 4), five);
	}
	
	@Test
	public void testLargeList() {
		List<Integer> large = new ArrayList<Integer>();
		for (int i = 0; i < 10; i++) {
			large.add(i);
		}
		CombSort.sort(large);
		for (int j = 0; j < 10; j++) {
			assertEquals(new Integer(j), large.get(j));
		}
	}
	
	// unoptimised run time = 6.712
	// optimised run time = 4.168
	
	@Test
	public void testExtraLargeList() {
		List<Integer> l = new ArrayList<Integer>();
		for (int i = 0; i < 30000; i++) {
			l.add(30000 - i - 1);
		}
		CombSort.sort(l);
		for (int j = 0; j < 30000; j++) {
			assertEquals(new Integer(j), l.get(j));
		}
	}

}
