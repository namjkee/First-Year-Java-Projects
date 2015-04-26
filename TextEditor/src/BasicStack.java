import java.util.ArrayList;

public class BasicStack<E> implements Stack<E> {
	private ArrayList<E> items;
	
	public BasicStack() {
		items = new ArrayList<E>();
	}
	
	public int size() {
		return items.size();
	}

	public boolean isEmpty() {
		return items.isEmpty();
	}
	
	public void push(E element) {
		items.add(element);
	}

	public E top() throws EmptyStackException {
		if (this.size() <= 0) throw new EmptyStackException();
		else {
			return items.get(items.size() - 1);
		}
	}

	public E pop() throws EmptyStackException {
		if (this.size() <= 0) throw new EmptyStackException();
		else {
			int index = items.size() - 1;
			E temp = items.get(items.size() - 1);
			items.remove(index);
			return temp;
		}
	}
	
}
