import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;


public class ScoreBoardTest {
	ScoreBoard scores;
	
	@Before
	public void setup(){
		scores = new ScoreBoard(5, 1);
		scores.add("Steve", 1.2);
		scores.add("Savy", 1.01);
		scores.add("Steven", 3);
		scores.add("Convoy", 2.2);
		scores.add("Oppon", 4);
		scores.add("Zezima", 2.1);
	}
	
	@Test
	public void testConstructionOne() {
		ScoreBoard test = new ScoreBoard();
		test.add("One", 1.43);
		assertEquals(1, test.size());
		assertEquals("One", test.leader());
		assertEquals(1.43 , test.queryDistance("One"), 1e-15);
	}
	
	@Test
	public void testConstructionTwo() {
		ScoreBoard test = new ScoreBoard(5);
		test.add("Steve", 1.2);
		test.add("Savy", 1.01);
		test.add("Steven", 3);
		test.add("Convoy", 2.2);
		test.add("Oppon", 4);
		assertEquals(5, test.size());
		assertEquals("Oppon", test.leader());
		assertEquals(4, test.queryDistance("Oppon"), 1e-15 );
	}
	
	@Test
	public void testConstructionThree() {
		ScoreBoard test = new ScoreBoard(5.5);
		test.add("Shotgun", 5.5);
		test.add("Maverick", 5.6);
		assertEquals(2, test.size());
		assertEquals("Maverick", test.leader());
		assertEquals(5.5, test.queryDistance("Shotgun"), 1e-15);
	}
	
	@Test
	public void testConstructionFour() {
		ScoreBoard test = new ScoreBoard(5, 5.5);
		test.add("Steve", 1.2);
		test.add("Savy", 1.01);
		test.add("Steven", 3);
		test.add("Convoy", 2.2);
		test.add("Oppon", 4);
		assertEquals(0, test.size());
		assertEquals(null, test.leader());
		assertEquals(-1, test.queryDistance("Convoy"), 1e-15);
	}
	
	@Test
	public void testNegativeConstructor() {
		ScoreBoard test = new ScoreBoard(-13, 0);
		test.add("Steve", 1.2);
		test.add("Savy", 1.01);
		test.add("Steven", 3);
		test.add("Convoy", 2.2);
		test.add("Oppon", 4);
		System.out.println(test.size());
		assertEquals(0, test.size());
		assertEquals(null, test.leader());
		assertEquals(-1, test.queryDistance("Convoy"), 1e-15);
	}
	
	@Test
	public void testIteratorEmpty() {
		ScoreBoard test = new ScoreBoard(0);
		
		Iterator<LinkedListNode> iter = test.getList().iterator();
		while (iter.hasNext()) {
			LinkedListNode node = iter.next();
			System.out.println(node.getElement().getDistance());
		}
		
	}

}
