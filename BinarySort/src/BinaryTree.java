import java.util.*;

class BinaryTreeNode {
	// member variables
	public int value;
	public BinaryTreeNode parent;
	
	public BinaryTreeNode left;
	public BinaryTreeNode right;
	
	public BinaryTreeNode(int value) {
		this.value = value;
		this.parent = null;
		this.left = null;
		this.right = null;
	}
}


public class BinaryTree {
	// instance variables
	public BinaryTreeNode root;
	
	public BinaryTree() {
		root = null;
	}
	
	public void insertLeft(BinaryTreeNode parent, BinaryTreeNode child) {
		parent.left = child;
		child.parent = parent;
	}
	
	public void insertRight(BinaryTreeNode parent, BinaryTreeNode child) {
		parent.right = child;
		child.parent = parent;
	}
	
	public void insert(int n) {
		/* Adding the first root into the binary search tree */
		if (root == null) {
			root = new BinaryTreeNode(n);
			return;
		}
		
		BinaryTreeNode current = root;
		while (true) {
			if (n < current.value) {			// goes left
				if (current.left == null) {		// adds left child of the current node 
					insertLeft(current, new BinaryTreeNode(n));
					return;
				}
				current = current.left;
			}
			else if (n > current.value) {
				if (current.right == null) {
					insertRight(current, new BinaryTreeNode(n));
					return;
				}
				current = current.right;
			} else return;						// node that is equal to this number
		}
	}
	
	public boolean contains(int n) {
		if (root == null) {
			return false;
		}
		
		// traversal
		BinaryTreeNode current = root;
		while (current != null) {
			if (n < current.value) {
				current = current.left;
			} else if (n > current.value) {
				current = current.right;
			}
			else return true;		// n is found, i.e equal to current.value
		}
		
		// not found
		return false;
	}
	
	// access methods
	public int size() {
		return size(root);
	}
	
	public boolean isEmpty() { 
		return (root == null);
	}
	
	// extra methods
	/* Preoder- visit children and add value, then go left child of the root and then right child of the root */
	public List<Integer> preOrderTraversal(BinaryTreeNode root) {
		List<Integer> list = new ArrayList<Integer>();
		
		if (root == null) return list;
		
		// visit action of algorithm
		list.add(root.value);
		
		// recursive traversal of the children from left to right
		if (root.left != null) {
			list.addAll(preOrderTraversal(root.left));
		}
		if (root.right != null) {
			list.addAll(preOrderTraversal(root.right));
		}
		
		return list;
	}
	
	public List<Integer> inOrderTraversal(BinaryTreeNode root) {
		List<Integer> list = new ArrayList<Integer>();
		
		if (root == null) return list;
		
		// recursive traversal of the children from left to right
		if (root.left != null) {
			list.addAll(inOrderTraversal(root.left));
		}
		// visit action of algorithm
		list.add(root.value);
		
		if (root.right != null) {
			list.addAll(inOrderTraversal(root.right));
		}
		
		return list;
	}
	
	public List<Integer> postOrderTraversal(BinaryTreeNode root) {
		List<Integer> list = new ArrayList<Integer>();
		
		if (root == null) return list;
		
		// recursive traversal of the children from left to right
		if (root.left != null) {
			list.addAll(postOrderTraversal(root.left));
		}
		if (root.right != null) {
			list.addAll(postOrderTraversal(root.right));
		}
		// visit action of the algorithm
		list.add(root.value);
		
		return list;
	}
	
	
	
	private int size(BinaryTreeNode root) {
		if (root == null) return 0;
		
		// else there's at least a root
		int total = 1;
		
		// recursively accumulates the size of every node
		if (root.left != null) {
			total += size(root.left);
		}
		if (root.right != null) {
			total += size(root.right);
		}
		// then returns this total
		return total;
	}
}
