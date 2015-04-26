import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;


public class ChainingHashMapTest {
	
	public static void exploreData(String pathToFile) throws FileNotFoundException, IOException {
		int counter = 0;
		
		// instantiate hash maps
		List<ChainingHashMap<String, Double>> list = new ArrayList<ChainingHashMap<String, Double>>();
		
		ChainingHashMap<String, Double> one = new ChainingHashMap<>(1, 4000);
		ChainingHashMap<String, Double> two = new ChainingHashMap<>(10, 4000);
		ChainingHashMap<String, Double> three = new ChainingHashMap<>(1, 4271);
		ChainingHashMap<String, Double> four = new ChainingHashMap<>(5, 4271);
		ChainingHashMap<String, Double> five = new ChainingHashMap<>(2000, 1, 4271);
		list.add(one);
		list.add(two);
		list.add(three);
		list.add(four);
		list.add(five);
		
		
		for (ChainingHashMap<String, Double> test: list) {
			BufferedReader br = new BufferedReader(new FileReader(pathToFile));
			try {
				counter++;											
				String line = br.readLine();						// store name
				
				while (line != null) {
					line = line.trim().replaceAll(" +", " ");		// additional update to properly trim the line
					String[] pieces = line.trim().split("\\s"); 	// split white spaces
					if (pieces.length == 4) {
						// put data into hashmaps
						Double frequency = Double.parseDouble(pieces[1]);
						test.put(pieces[0], frequency);
					}
					line = br.readLine();
				} 
			} finally {
				br.close();
			}
			
			// print bucket statistics
			System.out.println("                    TEST - " + counter + "                             ");
			System.out.println("The number of nodes stored in the fullest hashmap is " + test.getFullestBuckets()[0]);
			System.out.println("The number of hashmap cells containing this number of nodes are " + test.getFullestBuckets()[1]);
			System.out.println();
		}
		
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		exploreData("C:\\dist.male.first");
	}
//	
//	ChainingHashMap<Object, Object> test = new ChainingHashMap<>(2, 1, 4000);
//	
//	@Test
//	public void testKeys() {
//		test.put(1, "A");
//		test.put(2, "B");
//		test.put(3, "C");
//		test.put(4, "D");
//		System.out.println(test.keys());
//	}
//	
//	@Test
//	public void testConstruction() {
//		assertEquals(0, test.size());
//		assertTrue(test.isEmpty());
//		
//		assertEquals(Arrays.asList(), test.keys());
//	}
//	
//	@Test
//	public void testSize() {
//		test.put(1, "A");
//		test.put(2, "B");
//		test.put(3, "C");
//		assertEquals(3, test.size());
//		test.remove(2);
//		assertEquals(2, test.size());
//		test.remove(3);
//		assertEquals(1, test.size());
//		test.remove(1);
//		assertTrue(test.isEmpty());
//	}
//	
//	@Test
//	public void testSmallHashMap() {
//		test.put(1, "A");
//		test.put(2, "B");
//		test.put(3, "C");
//		test.remove(2);
//		assertNull(test.get(2));
//		test.put(1, "NEW");
//		assertEquals("NEW", test.get(1));
//		
//	}
//	
//	@Test
//	public void testLargeKey() {
//		test.put(20, "A");
//		assertEquals("A", test.get(20));
//	}
//	
//	@Test
//	public void testHashFunction() {
//		test.put("A", 1);
//		test.put("B", 2);
//		
//		assertEquals(1, test.get("A"));
//		assertEquals(2, test.get("B"));
//		
//		test.remove("A");
//		test.remove("B");
//		
//		test.put(-3, "A");
//		assertEquals("A", test.get(-3));
//		
//	}
//	
//	@Test
//	public void simpleHashMapTest() {
//		ChainingHashMap<Integer, String> map = new ChainingHashMap<>(2, 1, 4000);
//		map.put(0, "A");
//		map.put(1, "B");
//		map.put(2, "C");
//		map.put(3, "D");
//		assertEquals("A", map.get(0));
//		assertEquals("B", map.get(1));
//		assertEquals("C", map.get(2));
//		assertEquals("D", map.get(3));
//		assertEquals("A", map.remove(0));
//		assertEquals("B", map.remove(1));
//		assertEquals("C", map.remove(2));
//		assertEquals("D", map.remove(3));
//		assertEquals(0, map.size());
//		assertTrue(map.isEmpty());
//		
//		
//	}
//	
//	@Test
//	public void testCollisions() {
//		ChainingHashMap<Integer, String> test = new ChainingHashMap<>(2, 1, 4000);
//		test.put(0, "A");
//		test.put(3, "B");
//		test.put(6, "C");
//		assertEquals("A", test.get(0));
//		assertEquals("B", test.get(3));
//		assertEquals("C", test.get(6));
//		assertEquals("B", test.remove(3));
//		assertNull(test.get(3));
//		assertEquals("A", test.remove(0));
//		assertNull(test.get(0));
//		assertNull(test.remove(0));
//		assertNull(test.remove(3));
//		assertEquals("C", test.remove(6));
//		assertNull(test.get(6));
//		assertEquals(0, test.size());
//	}

}
