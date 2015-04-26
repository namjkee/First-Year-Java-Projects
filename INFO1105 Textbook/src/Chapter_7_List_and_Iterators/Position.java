package Chapter_7_List_and_Iterators;

public interface Position<E> {
	
	/**
	 * Returns the element stored at this position
	 * 
	 * @return
	 * @throws IllegalStateException
	 */
	E getElement() throws IllegalStateException;
}
