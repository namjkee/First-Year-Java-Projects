import java.util.ArrayList;
import java.util.Collections;


class PriorityQueueNode<K extends Comparable<K>, V> implements Comparable<PriorityQueueNode<K, V>> {
	public K key;
	public V value;
	
	public PriorityQueueNode(K key, V value) {
		this.key = key;
		this.value = value;
	}
	
	/**
	 * Compares this object's key to the argument's key, returning an int based on comparison.
	 * Returns int < 1 if this this objects key < argument key. Vice versa for > 1
	 * @param arg0
	 * @return int based on comparison value
	 */
	public int compareTo(PriorityQueueNode<K, V> arg0) {
		return this.key.compareTo(arg0.key);
	}
	
}

public class ArrayPriorityQueue<K extends Comparable<K>, V> implements PriorityQueue<K, V> {
	private ArrayList<PriorityQueueNode<K, V>> queue;

	public ArrayPriorityQueue() {
		queue = new ArrayList<PriorityQueueNode<K, V>>();
	}
	
	public void insert(K key, V value) {	
		queue.add(new PriorityQueueNode<K, V>(key, value));
	}
	
	public V removeMin() {
		if (isEmpty()) throw new IndexOutOfBoundsException();
		Collections.sort(queue);								// sorts queue from smallest to largest based on key
		return queue.remove(0).value;
	}

	public V min() {
		if (isEmpty()) throw new IndexOutOfBoundsException();
		Collections.sort(queue);								// sorts queue from smallest to largest based on key
		return queue.get(0).value;
	}
	
	public int size() {
		return queue.size();
	}
	
	public boolean isEmpty() {
		return queue.isEmpty();
	}
	
}
