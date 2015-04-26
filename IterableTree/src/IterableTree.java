import java.util.ArrayList;
import java.util.Iterator;

class IterableTree<E> implements Tree<E> {
	
	//--------------------Nested preorderIterator class ---------------
	private class preorderIterator implements Iterator<E> {
		private int current = 0;
		private int lastIndex = size() - 1;
		ArrayList<Node<E>> preorderedList = positions();

		public boolean hasNext() { return (current <= lastIndex); }

		public E next() {
			return preorderedList.get(current++).getElement();
		}

		public void remove() {
			// blank as specified
		}
		
	}
	
	//--------------------Nested preorderIterator class ---------------
	
	/**
	 * Order of the nodes traversed through the preorder algorithm
	 * @return
	 */
	public ArrayList<Node<E>> positions() {
		ArrayList<Node<E>> snapshot = new ArrayList<Node<E>>();
		if (!isEmpty()) {
			preorderSubtree(getRoot(), snapshot);
		}
		// After recursive preorder traversal, positions are stored properly and returned
		return snapshot;
	}
	
	/**
	 * A recursion algorithm of preorder traversal. Add the currentNode and for each child of that currentNode, recursively traverse through it
	 * until all nodes are added from left to right through the childrens.
	 * @param currentNode
	 * @param snapshot
	 */
	private void preorderSubtree(Node<E> currentNode, ArrayList<Node<E>> snapshot) {
		snapshot.add(currentNode);		// "visit" node
		for (Node<E> child: currentNode.getChildren()) {
			preorderSubtree(child, snapshot);
		}
	}
	
	/* Iterator */
	public Iterator<E> iterator() {
		return new preorderIterator();
	}
	
	// instance variable
	private Node<E> root;

	public IterableTree() {
		root = null;
	} 

	/**
	 * Private method for calculating size of a subtree at a given root node
	 * 
	 * @param root
	 * @return size of subtree
	 */
	private int size(Node<E> root) {
		if (root == null) {
			return 0;
		}
		int total = 1; 							// The root itself is valid and total is one for now

		for (Node<E> n : root.getChildren()) {
			total += size(n);
		}
		return total;
	}

	public int size() {
		return size(root);
	}

	public boolean isEmpty() {
		return (root == null);
	}

	public void setRoot(Node<E> root) {
		this.root = root;
	}

	public Node<E> getRoot() {
		return this.root;
	}

	public void insert(Node<E> parent, Node<E> child) {
		parent.addChild(child);
		child.setParent(parent);
	}

	public void remove(Node<E> node) {
		
		while (!node.getChildren().isEmpty()) {		// removing a given node means we need to remove it's descendants first, which means the node's children iterator 
			remove(node.getChildren().get(0));		// should all be cleared, also removing index 0 means other children nodes will reshuffle to index 0 to keep removing
		}
		
		if (node.getParent() == null) {
			this.root = null;
		}
		else {
			node.getParent().removeChild(node);
			node.setParent(null);
		}
		
	}
	
}