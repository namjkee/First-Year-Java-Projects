import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class ChainingHashMapEntry<K, V> {
	public ChainingHashMapEntry<K, V> next;
	public K key;
	public V value;
	
	public ChainingHashMapEntry(K key, V value) {
		this.key = key;
		this.value = value;
		this.next = null;
	}
	
	public void set(ChainingHashMapEntry<K, V> entry) {
		ChainingHashMapEntry<K, V> current = this;
		while (current.next != null) {
			current = current.next;
		}
		current.next = entry;				// current's next pointer is non-null
	}
	
	public V get(K key) {
		ChainingHashMapEntry<K, V> current = this;
		if (current.next == null) return null;
		while (!current.next.key.equals(key)) {
			current = current.next;
			if (current.next == null) return null;
		}
		return current.next.value;	
	}
	
	public V remove(K key) {
		ChainingHashMapEntry<K, V> current = this;
		if (current.key == key) {
			V temp = current.value;
			if (current.next != null) {
				this.key = current.next.key;
				this.value = current.next.value;
				this.next = current.next.next;
			}
			return temp;
		}
		if (current.next == null) return null;
		while (!current.next.key.equals(key)) {
			current = current.next;
			if (current.next == null) return null;
		}
		V temp = current.next.value;
		current.next = current.next.next;
		return temp;
	}
}

public class GenericChainingHashMap<K, V> implements HashMap<K, V> {
	// instance variables
	private ChainingHashMapEntry<K, V>[] items;
	private int numOfItems;
	
	// private utility method
	private int hash(K key) {
		return Math.abs(key.hashCode());
	}
	
	// constructor
	public GenericChainingHashMap(int size) {
		this.items = new ChainingHashMapEntry[size];
		this.numOfItems = 0;
	}
	
	public int size() {
		return numOfItems;
	}

	public boolean isEmpty() {return (numOfItems == 0);}

	public V get(K key) {
		int index = hash(key) % items.length;
		ChainingHashMapEntry<K, V> entry = items[index];
		
		if (entry == null) return null;			// doesn't exist
		
		if (!entry.key.equals(key)) {			// different value chained inside entry
			return entry.get(key);
		}
		
		return (V) entry.value;					// surpassed false conditions, hence true value is obtained
	}

	public void put(K key, V value) {
		// check if full - to extend
		if (numOfItems == items.length) {
			items = Arrays.copyOf(items, 2 * items.length);
		}
		///////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		int index = hash(key) % items.length;
		
		if (items[index] == null) {		// new item is added hence increase size && no potential collision
			items[index] = new ChainingHashMapEntry<K, V>(key, value);
			numOfItems += 1;
		}
		else {							// potential collision - chain it!
			if (!items[index].key.equals(key)) {
				items[index].set(new ChainingHashMapEntry<K, V>(key, value));
			}
			else {						// same key - overwrite the value
				items[index].value = value;
			}
			
		}
	}

	public V remove(K key) {
		int index = hash(key) % items.length;
		ChainingHashMapEntry<K, V> entry = items[index];
		
		if (entry == null) return null;
		else {										// there was item at that index
			if (entry.next != null && entry.key.equals(key)) {			// removing the first element of a chained linked list
				return entry.remove(key);
			}
			if (entry.next != null) {
				return entry.remove(key);
			}
			else {
				if (entry.key.equals(key)) {
					V temp = entry.remove(key);
					items[index] = null;
					numOfItems--;
					return temp;
				}
				else {
					return null;
				}
				
			}
		}
	}

	public List<K> keys() {
		List<Object> keys = new ArrayList<Object>();
		for (ChainingHashMapEntry<K, V> entry: items) {
			if (entry != null) {
				keys.add(entry.key);
			}
		}
		return (List<K>) keys;
	}

	public List<V> values() {
		List<Object> values = new ArrayList<Object>();
		for (ChainingHashMapEntry<K, V> entry: items) {
			if (entry != null) {
				values.add(entry.value);
			}
		}
		return (List<V>) values;
	}
	
}