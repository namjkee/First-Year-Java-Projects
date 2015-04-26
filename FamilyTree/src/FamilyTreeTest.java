import static org.junit.Assert.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;


public class FamilyTreeTest {
	private FamilyTree f;
    private Node bob;
    private Node jane;
    private Node tim;
    private Node kate;
    
    /**
     * This method is run before every test
     */
    @Before
    public void setup() {
        Tree t = new SimpleTree();
        
        this.bob = new SimpleNode("Bob");
        this.jane = new SimpleNode("Jane");
        this.tim = new SimpleNode("Tim");
        this.kate = new SimpleNode("Kate");

        t.setRoot(bob);
        t.insert(bob, jane);
        t.insert(bob, tim);
        t.insert(tim, kate);
        
        this.f = new FamilyTree(t);
    }

	@Test
	public void testGetParent() {
		assertSame(bob.getElement().toString() ,f.getParent(tim));
		assertSame(bob.getElement().toString(),f.getParent(jane));
		assertSame(tim.getElement().toString(),f.getParent(kate));
	}
	
	@Test
	public void testGetGrandParent() {
		assertSame(bob.getElement().toString(),f.getGrandparent(kate));
	}
	
	@Test
	public void testGetChildren() {
		List<String> expected = new ArrayList<String>();
		expected.add("Jane");
		expected.add("Tim");
		assertEquals(expected ,f.getChildren(bob));
	}
	
	@Test
	public void testGrandChildren() {
		List<String> expected = new ArrayList<String>();
		expected.add("Kate");
//		expected.add("Timmone");
		assertEquals(expected ,f.getGrandchildren(bob));
	}
	
	@Test
	public void testSiblings() {
		List<String> expected = new ArrayList<String>();
		assertEquals(expected, f.getSiblings(kate));
	}

	@Test
	public void testGetSimilarityScore() {
		assertEquals(0, f.getSimilarityScore("bob", "jane"));
		assertEquals(0, f.getSimilarityScore("bob", "kate"));
		assertEquals(1, f.getSimilarityScore("jim", "jack"));
		assertEquals(2, f.getSimilarityScore("jim", "jiovanni"));
		assertEquals(3, f.getSimilarityScore("jim", "jimmy"));
		assertEquals(3, f.getSimilarityScore("jim", "jim"));
	}
	
	@Test
	public void testMostSimilarDescendant() {
		Tree test = new SimpleTree();
		
		Node ted = new SimpleNode("Ted");
		Node todd = new SimpleNode("Todd");
		Node terry = new SimpleNode("Terry");
		Node tim = new SimpleNode("Tim");
		Node tyler = new SimpleNode("Tyler");
		
		test.insert(ted, todd);
		test.insert(ted, terry);
		test.insert(ted, tim);
		test.insert(ted, tyler);
		FamilyTree tree = new FamilyTree(test);
		List<String> siblings = new ArrayList<String>();
		siblings.add("Todd");
		siblings.add("Tim");
		siblings.add("Tyler");
		assertEquals(siblings, tree.getSiblings(terry));
		
	
		
		String result = tree.mostSimilarDescendant(ted);
		
		assertEquals("Terry", result);
	}
	
	@Test
	public void testSinglePersonFamily() {
		Tree test = new SimpleTree();
		
		Node bob = new SimpleNode("Bob");
		test.setRoot(bob);
		
		FamilyTree tree = new FamilyTree(test);
		assertSame("No parent",tree.getParent(bob));
		assertSame("No grandparent", tree.getGrandparent(bob));
		List<String> children = new ArrayList<String>();
		assertEquals(children, tree.getChildren(bob));
		List<String> grandchildren = new ArrayList<String>();
		assertEquals(grandchildren, tree.getGrandchildren(bob));
		List<String> siblings = new ArrayList<String>();
		assertEquals(siblings, tree.getSiblings(bob));
		assertNull(tree.mostSimilarDescendant(bob));
		
		
	}
	
	
	@Test
	public void test() {
		assertEquals("No grandparent", f.getGrandparent(bob));
		assertEquals("No grandparent", f.getGrandparent(jane));
		assertEquals("No grandparent", f.getGrandparent(tim));
		assertEquals("Bob", f.getGrandparent(kate));
		assertEquals(Arrays.asList("Kate"), f.getGrandchildren(bob));
		assertTrue(f.getGrandchildren(jane).isEmpty());
		assertTrue(f.getGrandchildren(tim).isEmpty());
		assertTrue(f.getGrandchildren(kate).isEmpty());
		assertEquals("No grandparent", f.getGrandparent(bob));

		 // wrong case
	//	 assertTrue(f.getGrandchildren(bob).isEmpty());
	}
	
	
	
}
