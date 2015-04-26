import java.util.Arrays;

public class ArrayStringStack implements Stack<String> {
	private String[] items;
	private int itemSize;
	int currentLength;
	
	public ArrayStringStack(int size) {
		itemSize = size;
		items = new String[size];
		currentLength = 0;
	}

	public int size() {
		return currentLength;
	}

	public boolean isEmpty() {
		if (currentLength == 0) return true;
		else {
			return false;
		}
	}

	public String top() throws EmptyStackException {
		if (size() <= 0) throw new EmptyStackException();
		return items[currentLength - 1];
	}

	public void push(String element) {
		/* Array is now full */
		if (currentLength == itemSize) {
			items = Arrays.copyOf(items, 2 * itemSize);
			itemSize = itemSize * 2;
			items[currentLength] = element;	
			currentLength++;
		} else {
			items[currentLength] = element;
			currentLength++;
		}
	}

	public String pop() throws EmptyStackException {
		if (size() <= 0) throw new EmptyStackException();
		String temp = items[currentLength - 1];
		items[currentLength - 1] = null;
		currentLength--;
		return temp;
	}
}
