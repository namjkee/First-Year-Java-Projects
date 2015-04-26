import java.util.ArrayList;
import java.util.Collections;


class PriorityQueueNode implements Comparable<PriorityQueueNode> {
	public Integer key;
	public Object value;
	
	public PriorityQueueNode(Integer key, Object value) {
		this.key = key;
		this.value = value;
	}
	
	/**
	 * Compares this object's key to the argument's key, returning an int based on comparison.
	 * Returns int < 1 if this this objects key < argument key. Vice versa for > 1
	 * @param arg0
	 * @return int based on comparison value
	 */
	public int compareTo(PriorityQueueNode arg0) {
		return this.key.compareTo(arg0.key);
	}
	
}

public class ArrayPriorityQueue implements PriorityQueue {
	private ArrayList<PriorityQueueNode> queue;
	
	public ArrayList<PriorityQueueNode> getQueue() {
		return queue;
	}

	public ArrayPriorityQueue() {
		queue = new ArrayList<PriorityQueueNode>();
	}
	
	public void insert(Integer key, Object value) {	
		queue.add(new PriorityQueueNode(key, value));
	}
	
	public Object removeMin() {
		if (isEmpty()) throw new IndexOutOfBoundsException();
		Collections.sort(queue);								// sorts queue from smallest to largest based on key
		PriorityQueueNode temp = queue.get(0);
		queue.remove(temp);
		return temp.value;
	}

	public Object min() {
		if (isEmpty()) throw new IndexOutOfBoundsException();
		Collections.sort(queue);								// sorts queue from smallest to largest based on key
		PriorityQueueNode temp = queue.get(0);
		return temp.value;
	}
	
	public int size() {
		return queue.size();
	}
	
	public boolean isEmpty() {
		return queue.isEmpty();
	}
	
}
