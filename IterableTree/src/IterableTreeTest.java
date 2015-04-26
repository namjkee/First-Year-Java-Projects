import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;


public class IterableTreeTest<E> {

	@Test
	public void testPreorderTraversal() {
		IterableTree<E> t = new IterableTree<E>();
		
		Node<E> A = new SimpleNode("A");
		Node<E> B = new SimpleNode("B");
		Node<E> C = new SimpleNode("C");
		Node<E> D = new SimpleNode("D");
		Node<E> E = new SimpleNode("E");
		Node<E> F = new SimpleNode("F");
		
		t.setRoot(A);
		t.insert(A, B);
		t.insert(A, C);
		t.insert(C, D);
		t.insert(C, E);
		t.insert(C, F);
		
		Iterator<E> iter = t.iterator();
		while(iter.hasNext()) {
			E next = iter.next();
			System.out.println(next);
			
		}
		
		
		
	}

}
