import static org.junit.Assert.*;

import org.junit.Test;


public class StatisticsCalculatorCachedTest {
	private StatisticsCalculatorCached test = new StatisticsCalculatorCached();
	
	@Test
	public void testEmpty() {
		assertEquals(0, test.getAverage(), 1e-15);
		assertEquals("Fail", test.getGrade());
	}
	
	@Test
	public void testFourScores() {
		test.addScore(70);
		test.addScore(60);
		test.addScore(50);
		test.addScore(40);
		
		assertEquals(55, test.getAverage(), 1e-15);
	}
	
	@Test
	public void testFail() {
		test.addScore(40);
		assertEquals("Fail", test.getGrade());
	}
	
	@Test
	public void testPass() {
		test.addScore(50);
		assertEquals("Pass", test.getGrade());
	}
	
	@Test
	public void testCredit() {
		test.addScore(65);
		assertEquals("Credit", test.getGrade());
	}
	
	@Test
	public void testDistinction() {
		test.addScore(75);
		assertEquals("Distinction", test.getGrade());
	}
	
	@Test
	public void testHighDistinction() {
		test.addScore(85);
		assertEquals("High Distinction", test.getGrade());
	}
	
	@Test
	public void testBorderCase() {
		test.addScore(84);
		test.addScore(85);
		assertEquals(84.5 , test.getAverage(), 1e-15);
		assertEquals("Distinction", test.getGrade());
	}
	
	@Test
	public void testSubjectScores() {
		test.addScore(99, "CS");
		test.addScore(20, "Physics");
		test.addScore(84);
		test.addScore(90, "Maths");
		test.addScore(69, "English");
		
		assertEquals("CS", test.getBestSubject());
		assertEquals("Physics", test.getWorstSubject());
	}
	
	@Test 
	public void testOneScore() {
		test.addScore(60);
		assertEquals("Unknown Subject", test.getBestSubject());
		assertEquals("Unknown Subject", test.getWorstSubject());
	}
	
	@Test
	public void testNoScores() {
		assertEquals(null, test.getBestSubject());
		assertEquals(null, test.getWorstSubject());
	}
	
	@Test
	public void testSameSubjectScores() {
		test.addScore(60, "Maths");
		test.addScore(60, "Maths");
		assertEquals("Maths", test.getWorstSubject());
		assertEquals("Maths", test.getWorstSubject());
	}
	
	@Test
	public void testEqualSubjectScores() {
		test.addScore(99, "CS");
		test.addScore(99, "Biology");
		test.addScore(99);
		
		assertEquals("CS", test.getBestSubject());
		
		test.addScore(1, "CS");
		test.addScore(1, "Biology");
		test.addScore(1);
		
		assertEquals("CS", test.getWorstSubject());
	}
	
	@Test
	public void testNonSubjectScores() {
		test.addScore(70);
		test.addScore(60);
		test.addScore(50);
		test.addScore(40);
		
		assertEquals("Unknown Subject", test.getBestSubject());
		assertEquals("Unknown Subject", test.getWorstSubject());
	}
	
	@Test
	public void statisticsLongTest() {
		StatisticsCalculatorCached yolo = new StatisticsCalculatorCached();
		for (int i = 0; i < 50000; i++) {
			yolo.addScore(10);
	                assertEquals(yolo.getAverage(), 10.0, 1e-15);
		}        
	}
	
	@Test
	public void cacheTest() {
		 for (int i = 0; i < 500000000; i ++)
			 test.addScore(10);
		    assertEquals(10.0, test.getAverage(), 1e-15);
	} 
	// LinkedList maintained = jUnit runtime = 47.317 seconds
	// Without storing scores = jUnit runtime = 0.397 seconds
	/**
	 * There's a huge difference in run time when the input size increases dramatically to 50 million iterations. The reason why the second algorithm is faster is because
	 * all of it's methods simply run in constant time and it doesn't store the scores but instead efficiently calculates the score faster.
	 * 
	 * The first algorithm implemented the LinkedList's add method which utilized space complexity by storing the nodes which was also a contributing factor to it's slower speed.
	 * At the cost of efficient time complexity, this algorithm would be used to store the scores into a database incase it needed to be accessed. 
	 * 
	 * Both algorithms are efficient in their own purposes, but for the sake of this task that's directed towards finding the best time complexity, the second algorithm clearly
	 * demonstrates higher performance in scale with the first algorithm when undergoing a "soak test".
	 * 
	 */
}
