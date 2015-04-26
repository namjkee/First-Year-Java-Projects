import java.util.Iterator;

public class Main {

	public static void main(String[] args) {
		SortedLinkedList test = new SortedLinkedList(10);	// default constructor capacity = 10;
		Flyer one = new Flyer("one", 100.5);
		Flyer two = new Flyer("two", 32.5);
		Flyer three = new Flyer("three", 73.5);
		Flyer four = new Flyer("four", 100.5);
		Flyer five = new Flyer("five", 32.5);
		Flyer six = new Flyer("six", 73.5);
		Flyer seven = new Flyer("seven", 100.5);
		Flyer eight = new Flyer("eight", 32.5);
		Flyer nine = new Flyer("nine", 73.5);
		Flyer ten = new Flyer("ten", 100.5);
		Flyer eleven = new Flyer("eleven", 32.5);
		
		test.add(one);
		test.add(two);
		test.add(nine);
		test.add(eleven);
		test.add(three);
		test.add(four);
		test.add(five);
		test.add(six);
		test.add(seven);
		test.add(eight);
		test.add(ten);
		
//		test.printList();
		
		ScoreBoard scores = new ScoreBoard(6, 1);
		scores.add("Steve", 1.2);
		scores.add("Savy", 1.01);
		scores.add("Steven", 3);
		scores.add("Convoy", 2.2);
		scores.add("Oppon", 4);
		scores.add("Zezima", 2.1);
		
//		System.out.println(scores.size());
//		scores.getList().printList();
//		System.out.println(scores.leader());
		
		Iterator<LinkedListNode> iter = scores.getList().iterator();
		while (iter.hasNext()) {
			LinkedListNode node = iter.next();
			System.out.println(node.getElement().getName());
		}
		
	}
	
}
