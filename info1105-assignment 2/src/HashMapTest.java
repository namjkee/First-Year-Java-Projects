import static org.junit.Assert.*;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class HashMapTest {
	
//	public static void exploreData(String pathToFile) throws FileNotFoundException, IOException {
//		int counter = 0;
//		
//		// instantiate hash maps
//		List<HashMap<String, Double>> list = new ArrayList<HashMap<String, Double>>();
//		
//		HashMap<String, Double> one = new HashMap<>(1, 4000);
//		HashMap<String, Double> two = new HashMap<>(10, 4000);
//		HashMap<String, Double> three = new HashMap<>(1, 4271);
//		HashMap<String, Double> four = new HashMap<>(5, 4271);
//		HashMap<String, Double> five = new HashMap<>(2000, 1, 4271);
//		HashMap<String, Double> six = new HashMap<>(11, 4000);
//		HashMap<String, Double> seven = new HashMap<>(12, 4000);
//		list.add(one);
//		list.add(two);
//		list.add(three);
//		list.add(four);
//		list.add(five);
//		list.add(six);
//		list.add(seven);
//		
//		
//		for (HashMap<String, Double> test: list) {
//			BufferedReader br = new BufferedReader(new FileReader(pathToFile));
//			try {
//				counter++;											
//				String line = br.readLine();						// store name
//				
//				while (line != null) {
//					line = line.trim().replaceAll(" +", " ");		// additional update to properly trim the line
//					String[] pieces = line.trim().split("\\s"); 	// split white spaces
//					if (pieces.length == 4) {
//						// put data into hashmaps
//						Double frequency = Double.parseDouble(pieces[1]);
//						test.put(pieces[0], frequency);
//					}
//					line = br.readLine();
//				} 
//			} finally {
//				br.close();
//			}
//			
//			// print collision statistics
//			System.out.println("                    TEST - " + counter + "                             ");
//			System.out.println("The number of insertions that had a collision were " + test.putCollisions());
//			System.out.println("The total number of collisions from insertions were " + test.totalCollisions());
//			System.out.println("The highest number of collisions in a single insertion were: " + test.maxCollisions());
//			System.out.println();
//		}
//		
//	}
//	
//	public static void main(String[] args) throws FileNotFoundException, IOException {
//		exploreData("C:\\dist.male.first");
//	}
	
	/* The tests below are used from previous tutorial exercises */
	@Test
	public void testKeys() {
		HashMap<Object, Object> test = new HashMap<>(2, 1, 4271);
		test.put("A", 1);
		test.put("B", 2);
		test.put("C", 3);
		test.remove("A");
		test.remove("B");
		System.out.println(test.keys());
	}
	@Test
	public void testConstruction() {
		HashMap<Object, Object> test = new HashMap<>(2, 1, 4271);
		assertEquals(0, test.size());
		assertTrue(test.isEmpty());
		assertEquals(Arrays.asList(), test.keys());
	}
	
	@Test
	public void testSmallCase() {
		HashMap<Object, Object> test = new HashMap<>(2, 1, 4271);
		test.put("A", 1);
		test.put("B", 2);
		test.put("C", 3);
		assertEquals(1, test.get("A"));
		assertEquals(2, test.get("B"));
		assertEquals(null, test.get("C"));
		
		test.put("A", 10);
		assertEquals(10, test.get("A"));
		test.put("B", 20);
		assertEquals(20, test.get("B"));
	}
	
	@Test
	public void testSize() {
		HashMap<Object, Object> test = new HashMap<>(5, 1, 4271);
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
	public void testLargeKey() {
		HashMap<Object, Object> test = new HashMap<>(5, 1, 4271);
		test.put(20, "A");
		assertEquals("A", test.get(20));
	}
	
	@Test 
	public void testHashFunction() {
		HashMap<Object, Object> test = new HashMap<>(5, 1, 4271);
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
	public void testHashMap() {
		HashMap<Object, Object> map = new HashMap<>(2, 1, 4271);
		map.put(0, "A");
		map.put(1, "B");
		map.put(2, "C");
		map.put(3, "D");
		assertEquals("A", map.get(0));
		assertEquals("B", map.get(1));
		assertEquals(null, map.get(2));
		assertEquals(null, map.get(3));
		assertEquals("A", map.remove(0));
		assertEquals("B", map.remove(1));
		assertEquals(null, map.remove(2));
		assertEquals(null, map.remove(3));
		assertEquals(0, map.size());
		assertTrue(map.isEmpty());
	}
	
	@Test
	public void testCollisions() {
		HashMap<Object, Object> abc = new HashMap<>(4, 1, 4271);
		abc.put(0, "A");
		abc.put(1, "B");
		abc.put(4, "C");
		abc.put(5, "D");
		
		assertEquals("A", abc.get(0));
		assertEquals("B", abc.get(1));
		assertEquals("C", abc.get(4));
		assertEquals("D", abc.get(5));
	}
}