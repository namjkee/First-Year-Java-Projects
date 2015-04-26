import java.util.ArrayList;
import java.util.List;


public class DoubleHashMap<K, V> {
	// instance variables
	private HashMapNode[] hashMap;
	private int multiplier;
	private int modulus;
	private int secondaryModulus;
	private int numOfNodes;
	// statistical variables
	private int putCollisions;
	private int totalCollisions;
	private int maxCollisions;
	
	// updated instruction
	public DoubleHashMap(int size) {
		this.hashMap = new HashMapNode[size];
		this.numOfNodes = 0;
		this.putCollisions = 0;
		this.totalCollisions = 0;
		this.maxCollisions = 0;
	}
	
	
	public DoubleHashMap(int multiplier, int modulus, int secondaryModulus) {
		// construct a DoubleHashMap with 4000 places and given hash parameters
		this(4000);
		this.multiplier = multiplier;
		this.modulus = modulus;
		this.secondaryModulus = secondaryModulus;
	}
	
	public DoubleHashMap(int hashMapSize, int multiplier, int modulus, int secondaryModulus) {
		// construct a HashMap with given capacity and given hash parameters
		this(hashMapSize);
		this.multiplier = multiplier;
		this.modulus = modulus;
		this.secondaryModulus = secondaryModulus;
	}
	
	// hashing
	public int hash(K key) {
		return Math.abs(this.multiplier * key.hashCode()) % this.modulus;
	}
	
	public int secondaryHash(K key) {
		return this.secondaryModulus - (Math.abs(key.hashCode()) % this.secondaryModulus);
	}
	
	// size
	public int size() { return this.numOfNodes; }
	
	public boolean isEmpty() { return (size() == 0); }
	
	// interface methods
	public List<K> keys() {
		List<K> keys = new ArrayList<K>();
		for (HashMapNode node: hashMap) {
			if (node != null && node.getKey() != null) {
				keys.add((K) node.getKey());
			}
		}
		return keys;
	}
	
	public V put(K key, V value) {
		int index = hash(key) % hashMap.length;
		HashMapNode node = hashMap[index];
		
		// checks if capacity is breached
		if (this.size() == hashMap.length) {				
			if (hashMap[index].getKey().equals(key)) {		// if same key - overwrite with new value and return old one
				V temp = (V) hashMap[index].getValue();
				hashMap[index].setValue(value);
				return temp;
			}
			else {											// else, collision updated and do nothing
				putCollisions += 1;
				return null;
			}
		}
		
		// capacity isn't breached
		if (node == null) {									// if true - new key-value pair is added to an empty index & increase size
			hashMap[index] = new HashMapNode(key, value);
			numOfNodes += 1;
			return null;
		}
		
		if (node.getKey().equals(key)) {					// if same key - overwrite with new value and return old one
			V temp = (V) hashMap[index].getValue();
			hashMap[index].setValue(value);
			return temp;
		}
		
		// Collision! - different key stored so we now probe indexes
		int currentIndex = index;
		int probingSteps = 0;										// number of probing steps counter
		int j = 1;													// integer for updating index
		putCollisions += 1;											// a putCollision
		while (hashMap[currentIndex] != null) {
			if (hashMap[currentIndex].getKey() == null) {			// if defunct - overwrite it
				hashMap[currentIndex] = new HashMapNode(key, value);
				numOfNodes += 1;
				return null;
			}
			currentIndex = (index + (j++ * secondaryHash(key))) % hashMap.length;	// updating index and already wrapped
			probingSteps += 1;
			
			if (currentIndex == index) {
				return null;
			}
		}
		
		if (probingSteps > maxCollisions) maxCollisions = probingSteps;		// after probing, compares it to the max recorded whether to update it or not
		totalCollisions += probingSteps;									// add the amount of probing steps to the accrued total - totalCollision
		
		hashMap[currentIndex] = new HashMapNode(key, value);				// currentIndex is now null from probing, to store key-value pair
		numOfNodes += 1;
		return null;
	}
	
	public V get(K key) {
		int index = hash(key) % hashMap.length;
		HashMapNode node = hashMap[index];
		
		if (node == null) return null;									// if true - key doesn't exist
		
		if (node.getKey() != null && node.getKey().equals(key)) {		// if key found - return it's value
			return (V) node.getValue();
		}
		
		// traverse through hashMap in the case that it may have probed
		int j = 1;
		int currentIndex = (index + (j++ * secondaryHash(key))) % hashMap.length;
		node = hashMap[currentIndex];
		
		// check if key is found in the next double-hashed index before main traversal
		if (node != null && node.getKey().equals(key)) {							
			return (V) node.getValue();
		}
		
		// traversal
		while (currentIndex != index) {
			currentIndex = (index + (j++ * secondaryHash(key))) % hashMap.length;		// updating index and already wrapped
			
			node = hashMap[currentIndex];
			if (node != null && node.getKey() != null && node.getKey().equals(key)) {	// if key is found - return it's value
				return (V) node.getValue();
			}
		}
		
		// failed to find the key
		return null;
	}
	
	public V remove(K key) {
		int index = hash(key) % hashMap.length;
		HashMapNode node = hashMap[index];
		HashMapNode defunct = new HashMapNode(null, null);
		
		if (node == null) return null;									// if true - key doesn't exist
		
		if (node.getKey() != null && node.getKey().equals(key)) {		// if key found - remove and return value
			hashMap[index] = defunct;
			numOfNodes -= 1;
			return (V) node.getValue();	
		}
		
		// traverse through hashMap in the case that it may have probed
		int j = 1;
		int currentIndex = (index + (j++ * secondaryHash(key))) % hashMap.length;
		node = hashMap[currentIndex];
		if (node != null && node.getKey() != null && node.getKey().equals(key)) {	// if key found - remove and return value
			hashMap[currentIndex] = defunct;
			numOfNodes -= 1;
			return (V) node.getValue();
		}
		
		// traversal
		while (currentIndex != index) {
			currentIndex = (index + (j++ * secondaryHash(key))) % hashMap.length;		// updating index and already wrapped
			
			node = hashMap[currentIndex];
			if (node != null && node.getKey() != null && node.getKey().equals(key)) {	// if key found - remove and return value
				hashMap[currentIndex] = defunct;
				numOfNodes -= 1;				
				return (V) node.getValue();
			}
		}
		
		// failed to find the key
		return null;
	}
	
	// statistical methods
	public int putCollisions() {
		return this.putCollisions;
	}
	
	public int totalCollisions() {
		return this.totalCollisions;
	}
	
	public int maxCollisions() {
		return this.maxCollisions;
	}
	
	public void resetStatistics() {
		this.putCollisions = 0;
		this.totalCollisions = 0;
		this.maxCollisions = 0;
	}
}