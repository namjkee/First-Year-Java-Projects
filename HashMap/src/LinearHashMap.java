import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class LinearHashMapEntry {
	public Object key;
	public Object value;
	
	public LinearHashMapEntry(Object key, Object value) {
		this.key = key;
		this.value = value;
	}
}

public class LinearHashMap {
	// instance variables
	private LinearHashMapEntry[] items;
	private int numOfItems;
	
	// private utility method
	private int hash(Object key) {
		return Math.abs(key.hashCode());
	}
	
	// constructor
	public LinearHashMap(int size) {
		this.items = new LinearHashMapEntry[size];
		this.numOfItems = 0;
	}
	
	public int size() {
		return numOfItems;
	}

	public boolean isEmpty() {return (numOfItems == 0);}

	public Object get(Object key) {
		int index = hash(key) % items.length;
		LinearHashMapEntry entry = items[index];
		
		if (entry == null) return null;			// doesn't exist
		
		if (!entry.key.equals(key)) {	// different value - traverse through array looking for the key
			int currentIndex = index;
			while (!items[++currentIndex].key.equals(key)) {
				if (currentIndex == index || items[currentIndex] == null) return null; 	// although traverse DEFUNCT nodes - come back later
				if (currentIndex == items.length - 1) {
					currentIndex = 0;
				}
			}
			return items[currentIndex].value;
		}
		
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
			items[index] = new LinearHashMapEntry(key, value);
			numOfItems += 1;	
		}
		else {
			if (items[index].key != key) {	// probe 
				
				int currentIndex = index;
				while (items[++currentIndex] != null) {
					if (currentIndex == index) return;
					if (currentIndex == items.length - 1) {		// wrap around array
						currentIndex = 0;
					}
				}
				items[currentIndex] = new LinearHashMapEntry(key, value);
			}
			else {	// same key, overwrite it
				items[index].value = value;
			}
		}
	}

	public Object remove(Object key) {
		int index = hash(key) % items.length;
		
		if (items[index] == null) return null;
		else {		// there was item at that index
			Object temp = items[index].value;
			items[index].key = null;
			items[index].value = null;
			numOfItems -= 1;
			return temp;
		}
	}

	public List<Object> keys() {
		List<Object> keys = new ArrayList<Object>();
		for (LinearHashMapEntry entry: items) {
			if (entry != null) {
				keys.add(entry.key);
			}
		}
		return keys;
	}

	public List<Object> values() {
		List<Object> values = new ArrayList<Object>();
		for (LinearHashMapEntry entry: items) {
			if (entry != null) {
				values.add(entry.value);
			}
		}
		return values;
	}	
}
