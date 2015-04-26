import java.util.*;

class HashMapEntry<K, V> {
	public K key;
	public V value;
	
	public HashMapEntry(K key, V value) {
		this.key = key;
		this.value = value;
	}
}

public class GenericHashMap<K, V> implements HashMap<K, V> {
	// instance variables
	private HashMapEntry[] items;
	private int numOfItems;
	
	// private utility method
	private int hash(K key) {
		return Math.abs(key.hashCode());
	}
	
	// constructor
	public GenericHashMap(int size) {
		this.items = new HashMapEntry[size];
		this.numOfItems = 0;
	}
	
	public int size() {
		return numOfItems;
	}

	public boolean isEmpty() {return (numOfItems == 0);}

	public V get(K key) {
		int index = hash(key) % items.length;
		HashMapEntry entry = items[index];
		
		if (entry == null) return null;			// doesn't exist
		
		if (!entry.key.equals(key)) return null; 	// different value
		
		// surpassed false conditions, hence true value is obtained
		return (V) entry.value;
	}

	public void put(K key, V value) {
		// check if full - to extend
		if (numOfItems == items.length) {
			items = Arrays.copyOf(items, 2 * items.length);
		}
		///////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		int index = hash(key) % items.length;
		
		
		if (items[index] == null) {		// new item is added hence increase size
			numOfItems += 1;	
		}
		items[index] = new HashMapEntry(key, value);	// item is either replaced or new depending on the above condition
	}

	public V remove(K key) {
		int index = hash(key) % items.length;
		
		if (items[index] == null) return null;
		else {		// there was item at that index
			V temp = (V) items[index].value;
			items[index] = null;
			numOfItems -= 1;
			return temp;
		}
	}

	public List<K> keys() {
		List<Object> keys = new ArrayList<Object>();
		for (HashMapEntry entry: items) {
			if (entry != null) {
				keys.add(entry.key);
			}
		}
		return (List<K>) keys;
	}

	public List<V> values() {
		List<Object> values = new ArrayList<Object>();
		for (HashMapEntry entry: items) {
			if (entry != null) {
				values.add(entry.value);
			}
		}
		return (List<V>) values;
	}
	
}
