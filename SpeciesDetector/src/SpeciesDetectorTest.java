import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SpeciesDetectorTest<E> {
	private Tree<String> t;
	private SpeciesDetector<String> s;
	private boolean yes;
	private boolean no;

	@Before
	public void setup() {
		t = new SimpleTree<String>();
		yes = true;
		no = false;

		Node<String> question1 = new SimpleNode("Does it have fur?");
		Node<String> question2 = new SimpleNode("Does it have ears?");
		Node<String> question3 = new SimpleNode("Does it have sharp claws?");
		Node<String> question4 = new SimpleNode("Does it have wings?");
		Node<String> question5 = new SimpleNode("Does it have a tail?");

		Node<String> answer1 = new SimpleNode("Bee");
		Node<String> answer2 = new SimpleNode("Fish");
		Node<String> answer3 = new SimpleNode("Bird");
		Node<String> answer4 = new SimpleNode("Monkey");
		Node<String> answer5 = new SimpleNode("Dog");
		Node<String> answer6 = new SimpleNode("Cat");
		
		t.setRoot(question1);
		t.insert(question1, question3);
		t.insert(question1, question2);
		
		t.insert(question3, answer6);
		t.insert(question3, question5);
		
		t.insert(question2, question4);
		t.insert(question2, answer1);
		
		
		t.insert(question4, answer3);
		t.insert(question4, answer2);
		
		t.insert(question5, answer5);
		t.insert(question5, answer4);
		s = new SpeciesDetector<String>(t);
	}
	
	@Test
	public void testBee() {
		assertEquals("Does it have fur?", s.getNextQuestion());	
		assertNull(s.submitAnswer(no));									
		assertEquals("Does it have ears?", s.getNextQuestion());
		assertEquals("Bee", s.submitAnswer(no));				
	}
	
	@Test
	public void testFish() {
		assertEquals("Does it have fur?", s.getNextQuestion()); //
		assertNull(s.submitAnswer(no));							//
		assertEquals("Does it have ears?", s.getNextQuestion());//
		assertNull(s.submitAnswer(yes));						//
		assertEquals("Does it have wings?", s.getNextQuestion());
		assertEquals("Fish", s.submitAnswer(no));
	}
	
	@Test
	public void testBird() {
		assertEquals("Does it have fur?", s.getNextQuestion());
		assertNull(s.submitAnswer(no));
		assertEquals("Does it have ears?", s.getNextQuestion());
		assertNull(s.submitAnswer(yes));
		assertEquals("Does it have wings?", s.getNextQuestion());
		assertEquals("Bird", s.submitAnswer(yes));
	}
	
	@Test
	public void testMonkey() {
		assertEquals("Does it have fur?", s.getNextQuestion());	//
		assertNull(s.submitAnswer(yes));						//
		assertEquals("Does it have sharp claws?", s.getNextQuestion());	//
		assertNull(s.submitAnswer(no));
		assertEquals("Does it have a tail?", s.getNextQuestion());
		assertEquals("Monkey", s.submitAnswer(no));
	}
	
	@Test
	public void testDog() {
		assertEquals("Does it have fur?", s.getNextQuestion());
		assertNull(s.submitAnswer(yes));
		assertEquals("Does it have sharp claws?", s.getNextQuestion());
		assertNull(s.submitAnswer(no));
		assertEquals("Does it have a tail?", s.getNextQuestion());
		assertEquals("Dog", s.submitAnswer(yes));
	}
	
	@Test
	public void testCat() {		
		assertEquals("Does it have fur?", s.getNextQuestion());
		assertNull(s.submitAnswer(yes));
		assertEquals("Does it have sharp claws?", s.getNextQuestion());
		assertEquals("Cat", s.submitAnswer(yes));
	}

}
