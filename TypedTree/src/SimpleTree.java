public class SimpleTree<E> implements Tree<E> {
	private Node<E> root;

	public SimpleTree() {
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
