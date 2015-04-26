import java.util.*;


public class QuickSort {
	
	public static void sort(List<Integer> list) {
		if (list.isEmpty() || list.size() == 1) {
			return;
		}
		
		Integer pivot = list.remove(0);
		
		List<Integer> S = new ArrayList<Integer>();
		List<Integer> L = new ArrayList<Integer>();
		
		/* Divide - place elements in sublists depending on their order relation with the pivot */
		while (!list.isEmpty()) {
			Integer item = list.remove(0);
			if (item < pivot) {
				S.add(item);
			}
			else {
				L.add(item);
			}
		}
		
		/* Conquer - recursively sort through the lists */
		sort(S);
		sort(L);
		
		while (!S.isEmpty()) {
			list.add(S.remove(0));
		}
		list.add(pivot);
		while (!L.isEmpty()) {
			list.add(L.remove(0));
		}
		
	}
	
}
