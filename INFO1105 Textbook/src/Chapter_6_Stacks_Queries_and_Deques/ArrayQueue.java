package Chapter_6_Stacks_Queries_and_Deques;

public class ArrayQueue<E> implements Queue<E> {
	public static final int CAPACITY = 1000;
	private E[] data;
	private int f = 0;
	private int sz = 0;
	
	
	public ArrayQueue() {this(CAPACITY);}
	public ArrayQueue(int capacity) {
		data = (E[]) new Object[capacity];
	}
	
	// Access methods
	public int size() {return sz;}
	
	public boolean isEmpty() { return (sz ==0);}
	
	public E first() {
		if (sz == 0) return null;
		return data[f];
	}
	
	public void enqueue(E e) throws IllegalStateException {
		if (sz == 0) throw new IllegalStateException("Queue is full");
		int avail = (f + sz) % data.length;
		data[avail] = e;
		sz++;
	}
	
	public E dequeue() {
		if (isEmpty()) return null;
		E answer = data[f];
		data[f + 1] = null; // Garbage Collection saves this space
		f = (f + 1) % data.length;
		sz--;
		return answer;
	}
	
}