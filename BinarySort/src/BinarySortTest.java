import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import java.util.*;

public class BinarySortTest {
	List<Integer> list = new ArrayList<Integer>();

	@Test
	public void testEmpty() {
		List<Integer> expected = new ArrayList<Integer>();
		list = BinarySort.sort(list);
		assertEquals(expected, list);
	}
	
	@Test
	public void testSingle() {
		list.add(1);
		list = BinarySort.sort(list);
		
		List<Integer> expected = new ArrayList<Integer>();
		expected.add(1);
		
		assertEquals(expected, list);
	}

	@Test
	public void testSample() {
		list.add(4);
		list.add(1);
		list.add(5);
		list.add(7);
		list.add(2);
		list = BinarySort.sort(list);
		
		List<Integer> expected = new ArrayList<Integer>();
		expected.add(1);
		expected.add(2);
		expected.add(4);
		expected.add(5);
		expected.add(7);
		
		assertEquals(expected, list);
	}
	
	@Test
	public void testSortedAlready() {
		list.add(3);
		list.add(4);
		list.add(5);
		list = BinarySort.sort(list);
		
		List<Integer> expected = new ArrayList<Integer>();
		expected.add(3);
		expected.add(4);
		expected.add(5);
		
		assertEquals(expected, list);
	}
	
	@Test
	public void testReverse() {
		list.add(5);
		list.add(4);
		list.add(3);
		list = BinarySort.sort(list);
		
		List<Integer> expected = new ArrayList<Integer>();
		expected.add(3);
		expected.add(4);
		expected.add(5);
		
		assertEquals(expected, list);
	}
	
	@Test
	public void testNegative() {
		list.add(4);
		list.add(-1);
		list = BinarySort.sort(list);
		
		List<Integer> expected = new ArrayList<Integer>();
		expected.add(-1);
		expected.add(4);
		
		assertEquals(expected, list);
	}
}
