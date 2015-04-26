import java.util.ArrayList;
import java.util.List;


public class ChainingHashMap<K, V> {
	// instance variables
	private ChainingHashMapNode[] hashMap;
	private int multiplier;
	private int modulus;
	private int numOfNodes;
	// statistical variables
	private int fullestBucket;
	private int numOfFullBuckets;
	
	// construction
	public ChainingHashMap(int size) {
		this.hashMap = new ChainingHashMapNode[size];
		this.numOfNodes = 0;
		this.fullestBucket = 0;
		this.numOfFullBuckets = 0;
	}
	
	public ChainingHashMap(int multiplier, int modulus) {
		// construct a HashMap with 4000 places and given hash parameters
		this(4000);
		this.multiplier = multiplier;
		this.modulus = modulus;
	}
	
	public ChainingHashMap(int hashMapSize, int multiplier, int modulus) {
		// construct a HashMap with given capacity and given hash parameters
		this(hashMapSize);
		this.multiplier = multiplier;
		this.modulus = modulus;
	}
	
	// hashing
	public int hash(K key) {
		return Math.abs(this.multiplier * key.hashCode()) % this.modulus;
	}
	
	// size
	public int size() { return this.numOfNodes; }
	
	public boolean isEmpty() { return (size() == 0); }
	
	// interface methods
	public int[] getFullestBuckets() {
		int[] fullestBucket = new int[2];
		fullestBucket[0] = this.fullestBucket;
		fullestBucket[1] = this.numOfFullBuckets;
		return fullestBucket;
	}
	
	public List<K> keys() {
		List<K> keys = new ArrayList<K>();
		
		// adds each node and it's chained nodes
		for (ChainingHashMapNode node: hashMap) {
			ChainingHashMapNode current = node;
			while (current != null) {
				keys.add((K) current.getKey());
				current = current.getNext();
			}
			
		}
		return keys;
	}
	
	public V put(K key, V value) {
		int index = hash(key) % hashMap.length;
		ChainingHashMapNode node = hashMap[index];
		
		if (node == null) {											// if true - new key-value pair is added to an empty index & increase size
			hashMap[index] = new ChainingHashMapNode(key, value);
			numOfNodes += 1;
			return null;
		}
		
		if (node.getKey().equals(key)) {							// if same key - overwrite with new value and return old value
			V temp = (V) node.getValue();
			node.setValue(value);
			return temp;
		}
		
		int bucket = 0;											// counter to determine fullestBucket
		while (node.getNext() != null) {						// different key stored, traverse until a duplicate or null entry is found
			if (node.getNext().getKey().equals(key)) {			// if same key - overwrite with new value and return old value
				V temp = (V) node.getNext().getValue();
				node.getNext().setValue(value);
				return temp;
			}
			node = node.getNext();
			bucket += 1;
		}
		
		// found a null entry, updating statistics
		if (bucket > this.fullestBucket) {			// determine if it's the fullest bucket so far
			this.fullestBucket = bucket;
			this.numOfFullBuckets = 1;				// reset the number of fullestbuckets for the new size
		}
		if (bucket == this.fullestBucket) {			// if it's the same size as the fullest - increment
			numOfFullBuckets += 1;
		}
		
		// now updating the hashmap
		node.setNext(new ChainingHashMapNode(key, value));
		numOfNodes += 1;
		return null;
	}
	
	public V get(K key) {
		int index = hash(key) % hashMap.length;
		ChainingHashMapNode node = hashMap[index];
		
		if (node == null) return null;						// if true - doesn't exist
		
		if (node.getKey().equals(key)) {					// if same key - return value
			return (V) node.getValue();
		}
		
		while (node.getNext() != null) {					// traverse hashMap looking for the key
			if (node.getNext().getKey().equals(key)) {		// if true - found it
				return (V) node.getNext().getValue();
			}
			node = node.getNext();
		}
		return null;										// traversal finished - didn't find it
	}
	
	public V remove(K key) {
		int index = hash(key) % hashMap.length;
		ChainingHashMapNode node = hashMap[index];
		
		if (node == null) return null;						// if true - doesn't exist
		
		if (node.getKey().equals(key)) {					// if key found - replace its location with it's next node (null if last entry)
			hashMap[index] = node.getNext();
			numOfNodes -= 1;
			return (V) node.getValue();
		}
		
		while (node.getNext() != null) {					// traverse the linked list, looking for the key
			if (node.getNext().getKey().equals(key)) {		// if key found - save it's value, remove node and return value
				V temp = (V) node.getNext().getValue();
				node.setNext(node.getNext().getNext());		// the removal
				numOfNodes -= 1;
				return temp;
			}
			node = node.getNext();
		}
		
		// terminated from end of chain
		return null;										// key wasn't found
	}
}
