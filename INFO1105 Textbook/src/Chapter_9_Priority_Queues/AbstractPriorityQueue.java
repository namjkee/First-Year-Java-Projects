package Chapter_9_Priority_Queues;

import java.util.Comparator;

public abstract class AbstractPriorityQueue<K, V> implements PriorityQueue<K, V> {
	
	// ----------- nested PQEntry class ---------
	protected static class PQEntry<K, V> implements Entry<K, V> {
		private K k; 				// key
		private V v; 				// value
		
		public PQEntry(K key, V value) {
			k = key;
			v = value;
		}
		
		public K getKey() { return k; }
		
		public V getValue() { return v; }
		
		protected void setKey(K key) {k = key; }
		
		protected void setValue(V value) {v = value; }
		
	}
	// ------------ end of nested PQEntry class ---------
	
	// Instance variable for an AbstractPriorityQueue
	/**
	 * The comparator defining the order of keys in the priority queue
	 */
	private Comparator<K> comp;
	
	/**
	 * Creates an empty priority queue using the given comprator to order keys
	 */
	protected AbstractPriorityQueue(Comparator<K> c) {
		comp = c; 
	}
	
	/**
	 * Creates an empty priority queue based on the natural ordering of its keys
	 */
	protected AbstractPriorityQueue() {
		this(new DefaultComparator<K>());
	}
	
	protected int compare(Entry<K, V> a, Entry<K, V> b) {
		return comp.compare(a.getKey(), b.getKey());
	}
	
	/**
	 * Determines whether a key is valid
	 */
	protected boolean checkKey(K key) throws IllegalArgumentException {
		try {
			return (comp.compare(key, key) == 0);			// see if key can be compared to itself
		} catch (ClassCastException e) {
			throw new IllegalArgumentException("Incompatible key");
		}
	}
	
	/**
	 * Tests whether the priority queue is empty
	 */
	public boolean isEmpty() {
		return (size() == 0);
	}

}
