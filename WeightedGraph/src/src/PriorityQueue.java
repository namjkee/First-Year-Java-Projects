
public interface PriorityQueue {
	
	public void insert(Integer key, Object value);
	
	public Object removeMin();
	
	public Object min();
	
	public int size();
	
	public boolean isEmpty();
	
	public void decreaseKeys(Integer oldKey, Integer newKey);
	
}
