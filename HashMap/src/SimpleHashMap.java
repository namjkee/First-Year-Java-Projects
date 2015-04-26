import java.util.*;

class HashMapEntry {
	public Object key;
	public Object value;
	
	public HashMapEntry(Object key, Object value) {
		this.key = key;
		this.value = value;
	}
}

public class SimpleHashMap implements HashMap {
	// instance variables
	private HashMapEntry[] items;
	private int numOfItems;
	
	// private utility method
	private int hash(Object key) {
		return Math.abs(key.hashCode());
	}
	
	// constructor
	public SimpleHashMap(int size) {
		this.items = new HashMapEntry[size];
		this.numOfItems = 0;
	}
	
	public int size() {
		return numOfItems;
	}

	public boolean isEmpty() {return (numOfItems == 0);}

	public Object get(Object key) {
		int index = hash(key) % items.length;
		HashMapEntry entry = items[index];
		
		if (entry == null) return null;			// doesn't exist
		
		if (!entry.key.equals(key)) return null; 	// different value
		
		// surpassed false conditions, hence true value is obtained
		return entry.value;
	}

	public void put(Object key, Object value) {
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

	public Object remove(Object key) {
		int index = hash(key) % items.length;
		
		if (items[index] == null) return null;
		else {		// there was item at that index
			Object temp = items[index].value;
			items[index] = null;
			numOfItems -= 1;
			return temp;
		}
	}

	public List<Object> keys() {
		List<Object> keys = new ArrayList<Object>();
		for (HashMapEntry entry: items) {
			if (entry != null) {
				keys.add(entry.key);
			}
		}
		return keys;
	}

	public List<Object> values() {
		List<Object> values = new ArrayList<Object>();
		for (HashMapEntry entry: items) {
			if (entry != null) {
				values.add(entry.value);
			}
		}
		return values;
	}
	
}
