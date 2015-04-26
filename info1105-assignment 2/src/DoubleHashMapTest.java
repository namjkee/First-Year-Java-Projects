import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;


public class DoubleHashMapTest {
	
//	public static void exploreData(String pathToFile) throws FileNotFoundException, IOException {
//		int counter = 0;
//		
//		// instantiate hash maps
//		List<DoubleHashMap<String, Double>> list = new ArrayList<DoubleHashMap<String, Double>>();
//		
//		DoubleHashMap<String, Double> one = new DoubleHashMap<>(2000, 1, 4271, 1);
//		DoubleHashMap<String, Double> two = new DoubleHashMap<>(2000, 1, 4271, 223);
//		DoubleHashMap<String, Double> three = new DoubleHashMap<>(2000, 1, 4271, 647);
//		DoubleHashMap<String, Double> four = new DoubleHashMap<>(1, 4271, 1);
//		DoubleHashMap<String, Double> five = new DoubleHashMap<>(1, 4271, 223);
//		DoubleHashMap<String, Double> six = new DoubleHashMap<>(1, 4271, 647);
//		DoubleHashMap<String, Double> seven = new DoubleHashMap<>(2000, 1, 4271, 6);
//		list.add(one);
//		list.add(two);
//		list.add(three);
//		list.add(four);
//		list.add(five);
//		list.add(six);
//		list.add(seven);
//		
//		for (DoubleHashMap<String, Double> test: list) {
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
		DoubleHashMap<Object, Object> test = new DoubleHashMap<>(2, 1, 4271, 1);
		test.put("A", 1);
		test.put("B", 2);
		test.put("C", 3);
		test.remove("A");
		test.remove("B");
		System.out.println(test.keys());
	}
	@Test
	public void testConstruction() {
		DoubleHashMap<Object, Object> test = new DoubleHashMap<>(2, 1, 4271, 1);
		assertEquals(0, test.size());
		assertTrue(test.isEmpty());
		assertEquals(Arrays.asList(), test.keys());
	}
	
	@Test
	public void testSmallCase() {
		DoubleHashMap<Object, Object> test = new DoubleHashMap<>(3, 1, 4271, 1);
		test.put("A", 1);
		test.put("B", 2);
		test.put("D", 3);
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
		DoubleHashMap<Object, Object> test = new DoubleHashMap<>(5, 1, 4271, 1);
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
		DoubleHashMap<Object, Object> test = new DoubleHashMap<>(5, 1, 4271, 1);
		test.put(20, "A");
		assertEquals("A", test.get(20));
	}
	
	@Test 
	public void testHashFunction() {
		DoubleHashMap<Object, Object> test = new DoubleHashMap<>(5, 1, 4271, 1);
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
		DoubleHashMap<Object, Object> map = new DoubleHashMap<>(2, 1, 4271, 1);
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
		DoubleHashMap<Object, Object> abc = new DoubleHashMap<>(4, 1, 4271, 1);
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
