package Chapter_3_Fundamental_Data_Structures;

import Chapter_3_Fundamental_Data_Structures.SinglyLinkedList.Node;

public class Test {
	
	public static Node getSecondToLast(Node head, SinglyLinkedList list) {
		System.out.println("Testing size, now has " + list.size());
		if (list.size() == 2) {
			System.out.println("The second last node has element of " + head.getElement());
			return head;
		} else {
			list.removeFirst();
			System.out.println("The size is now " + list.size());
			getSecondToLast(head.getNext(), list);
		}
		return null;
	}
	
	public static void main(String[] args) {
		SinglyLinkedList<Integer> ub = new SinglyLinkedList<Integer>();
		ub.addFirst(3);	//head
		ub.addLast(10);
		ub.addLast(13);
		ub.addLast(20);
		ub.addLast(22);
		ub.addLast(30); //tail
		
		getSecondToLast(ub.head, ub);
		
	}

}
