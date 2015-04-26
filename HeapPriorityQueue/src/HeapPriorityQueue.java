
public class HeapPriorityQueue implements PriorityQueue {
	private ArrayHeap heap;
	private ArrayPriorityQueue priorityQueue;
	
	public HeapPriorityQueue() {
		heap = new ArrayHeap();
		priorityQueue = new ArrayPriorityQueue();
	}
	
	// access methods
	public int size() {
		return heap.size();
	}

	public boolean isEmpty() {
		return heap.isEmpty();
	}
	
	// update methods
	public void insert(Integer key, Object value) {
		priorityQueue.insert(key, value);
		heap.insert(key, value);
	}

	public Object removeMin() {
		priorityQueue.removeMin();
		return heap.removeRoot();
	}

	public Object min() {
		return priorityQueue.min();
	}
	
}
