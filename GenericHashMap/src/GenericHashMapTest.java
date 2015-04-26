import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


public class GenericHashMapTest {

	GenericHashMap<Object, Object> test = new GenericHashMap(5);

	@Test
	public void testConstruction() {
		assertEquals(0, test.size());
		assertTrue(test.isEmpty());
		
		List<Object> empty = new ArrayList<Object>();
		assertEquals(empty, test.keys());
		assertEquals(empty, test.values());
	}
	
	@Test
	public void testSize() {
		test.put(1, "A");
		test.put(2, "B");
		test.put(3, "C");
		assertEquals(3, test.size());
		test.remove(2);
		assertEquals(2, test.size());
		test.remove(3);
		assertEquals(1, test.size());
		test.remove(1);
		assertTrue(test.isEmpty());
	}
	
	@Test
	public void testSmallHashMap() {
		test.put(1, "A");
		test.put(2, "B");
		test.put(3, "C");
		test.remove(2);
		assertNull(test.get(2));
		test.put(1, "NEW");
		assertEquals("NEW", test.get(1));
		
	}
	
	@Test
	public void testLargeKey() {
		test.put(20, "A");
		assertEquals("A", test.get(20));
	}
	
	@Test
	public void testHashFunction() {
		test.put("A", 1);
		test.put("B", 2);
		
		assertEquals(1, test.get("A"));
		assertEquals(2, test.get("B"));
		
		test.remove("A");
		test.remove("B");
		
		test.put(-3, "A");
		assertEquals("A", test.get(-3));
		
	}
	
	@Test
	public void simpleHashMapTest() {
		GenericHashMap map = new GenericHashMap(2);
		map.put(0, "A");
		map.put(1, "B");
		map.put(2, "C");
		map.put(3, "D");
		assertEquals("A", map.get(0));
		assertEquals("B", map.get(1));
		assertEquals("C", map.get(2));
		assertEquals("D", map.get(3));
		assertEquals("A", map.remove(0));
		assertEquals("B", map.remove(1));
		assertEquals("C", map.remove(2));
		assertEquals("D", map.remove(3));
		assertEquals(0, map.size());
		assertTrue(map.isEmpty());
		
		
	}

}
