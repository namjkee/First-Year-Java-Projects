package Chapter_6_Stacks_Queries_and_Deques;

public interface Queue<E> {
	
	int size();
	
	boolean isEmpty();
	
	E first();
	
	void enqueue(E e);
	
	E dequeue();
	
}
