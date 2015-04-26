package Chapter_7_List_and_Iterators;

public class ArrayList<E> implements List<E> {
	// Private instance variables
	public static final int CAPACITY = 16;
	private E[] data;
	private int size = 0;
	
	public ArrayList(){ this(CAPACITY); }
	
	public ArrayList(int capacity) {
		data = (E[]) new Object[capacity];
	}
	
	// Access Methods
	/**
	 * Returns the number of elements in the array list
	 */
	public int size() { return size; }
	
	/**
	 * Returns whether the array list is empty
	 */
	public boolean isEmpty() { return size == 0; }
	
	/**
	 * Returns (but does not remove) the element at index i
	 */
	public E get(int i) throws IndexOutOfBoundsException {
		checkIndex(i, size);
		return data[i];
	}
	
	// Update Methods
	/**
	 * Replaces the element at index i with e, and returns the replaced element
	 */
	public E set(int i, E e) throws IndexOutOfBoundsException {
		checkIndex(i, size);
		E temp = data[i];
		data[i] = e;
		return temp;
	}
	
	/**
	 * Inserts element e to be at index i, shifting all subsequent elments later
	 */
	public void add(int i ,E e) throws IndexOutOfBoundsException {
		// Check if out of bounds, size + 1 since we are adding a element to the array so we don't throw exception yet
		checkIndex(i, size + 1);
		// Resize if overlimit capacity
		if (size == data.length) {
			resize(2 * data.length);
		}
		// Check size limit
		if (size == data.length) throw new IllegalStateException("Array is full");
		
		/* Move subsequent elements after i, one index to the right - as shown in data[k + 1] */
		for (int k = size - 1; k >= i; k--) {
			data[k + 1] = data[k];
		}
		data[i] = e;
		size++;		
	}
	
	/**
	 * Resizes the internal array to have given capacity >= size
	 */
	protected void resize(int capacity) {
		/* New resized array to be reassigned */
		E[] temp = (E[]) new Object[capacity];
		/* Copying each element of original array to new resized array above - Deep Copy */
		for (int k = 0; k < size; k++) {
			temp[k] = data[k];
		}
		/* Referenceing the new resized array over the old array */
		data = temp;
	}
	
	
	/**
	 * Removes/returns the element at index i, shifting subsequent elements earlier
	 */
	public E remove(int i) throws IndexOutOfBoundsException {
		checkIndex(i, size);
		E temp = data[i];
		
		/* Move subsequent elements after i, one index to the left */
		for (int k = i; k < size - 1; k++) {
			data[k] = data[k + 1];
		}
		data[size - 1] = null;			// Help garbage collection
		size--;
		return temp;
	}
	
	// Utility Methods - for exceptions
	/**
	 * Checks whether the given index is in the range [0, n -1]
	 * @param i
	 * @param n
	 * @throws IndexOutOfBoundsException
	 */
	protected void checkIndex(int i, int n) throws IndexOutOfBoundsException {
		if (i < 0 || i >= n) throw new IndexOutOfBoundsException("Illegal index: " + i);
	}
}
