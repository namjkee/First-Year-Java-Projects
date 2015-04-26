import java.util.*;

public class BinarySort {
	/**
	 * Returns the given numbers in ascending order
	 */
	public static List<Integer> sort(List<Integer> numbers) {
		BinaryTree tree = new BinaryTree();
		
		for (Integer i: numbers) {	// add numbers from list in order to the binary search tree
			tree.insert(i);
		}
		
		numbers = tree.inOrderTraversal(tree.root);
		
		return numbers;
	}
}
