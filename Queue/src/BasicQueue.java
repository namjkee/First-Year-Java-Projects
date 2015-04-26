import java.util.ArrayList;


public class BasicQueue<E> implements Queue<E> {
	private ArrayList<E> items;
	
	public BasicQueue() {
		items = new ArrayList<E>();
	}
	
	public int size() {
		return items.size();
	}
	
	public boolean isEmpty() {
		return items.isEmpty();
	}
	
	public void enqueue(E element) {
		items.add(element);
	}
	
	public E front() throws EmptyQueueException {
		if (this.size() <= 0) throw new EmptyQueueException();
		else {
			return items.get(0);
		}
	}
	
	public E dequeue() throws EmptyQueueException {
		if (this.size() <= 0) throw new EmptyQueueException();
		else {
			E temp = items.get(0);
			items.remove(temp);
			return temp;
		}
	}
	
}
