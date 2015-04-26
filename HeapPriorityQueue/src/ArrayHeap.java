import java.util.ArrayList;
import java.util.List;

class HeapNode {
	public int key;
	public Object value;
	
	public HeapNode(int key, Object value) {
		this.key = key;
		this.value = value;
	}
	
}

public class ArrayHeap implements Heap {
	private List<HeapNode> tree;
	
	public ArrayHeap() {
		tree= new ArrayList<HeapNode>();
	}
	
	public int size() {
		return tree.size();
	}
	
	public boolean isEmpty() {
		return tree.isEmpty();
	}
	
	// helper methods
	private int getParentIndex(int index) {
		return (index - 1) / 2;
	}
	
	private int getLeftChildIndex(int index) {
		return (2 * index) + 1;
	}
	
	private int getRightChildIndex(int index) {
		return (2 * index) + 2;
	}
	
	// update methods
	public void insert(int key, Object value) {
		tree.add(new HeapNode(key, value));
		int currentIndex = tree.size() - 1;
		
		while (currentIndex != 0) {
			int parentIndex = getParentIndex(currentIndex);
			
			/* Assuming that the entire heap is already structured from lowest to highest key in levels - one test to check if new node is valid */
			if (tree.get(currentIndex).key > tree.get(parentIndex).key) {
				// valid heap property
				return;
			} 
			
			/* Swap the two HeapNodes */
			else {
				HeapNode temp = tree.get(currentIndex);
				tree.set(currentIndex, tree.get(parentIndex));
				tree.set(parentIndex, temp);
				
				// continue checking heap structure from newly inserted node position/indexs
				currentIndex = parentIndex;
			}
		}
		return;
	}

	public Object removeRoot() {
		if (tree.size() == 1) {
			return tree.remove(0).value;
		}
		// Remove root and last node, replace root with last node
		Object oldRoot = tree.get(0).value;
		HeapNode lastNode = tree.remove(tree.size() - 1);
		tree.set(0, lastNode);
		
		// DownHeap Bubbling
		int currentIndex = 0;
		
		while (true) {
			int leftChildIndex = getLeftChildIndex(currentIndex);
			int rightChildIndex = getRightChildIndex(currentIndex);
			
			// check if we reached the leaf of the tree thus bubbling done
			if (leftChildIndex >= tree.size() && rightChildIndex >= tree.size()) {
				return oldRoot;
			}
			
			int smallestChildIndex;
			if (leftChildIndex < tree.size() && rightChildIndex < tree.size()) {
				// both children
				if (tree.get(leftChildIndex).key < tree.get(rightChildIndex).key) {
					smallestChildIndex = leftChildIndex;
				} else {
					smallestChildIndex = rightChildIndex;
				}
			}
			else {
				// left child only since the heap is complete
				smallestChildIndex = leftChildIndex;
			}
			
			// Downheap bubbling begins
			if (tree.get(currentIndex).key < tree.get(smallestChildIndex).key) {
				return oldRoot;
			}
			else {
				// swap with child
				HeapNode temp = tree.get(currentIndex);
				tree.set(currentIndex, tree.get(smallestChildIndex));
				tree.set(smallestChildIndex, temp);
				currentIndex = smallestChildIndex;
			}
		}
		
	}
	
}
