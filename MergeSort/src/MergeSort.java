import java.util.ArrayList;
import java.util.List;

public class MergeSort {
	
	public static void sort(List<Integer> list) {
		int n = list.size();
		sort(list, 0, n);
	}
	
	// helper method
	private static void sort(List<Integer> list, int startIndex, int endIndex) {
		int length = endIndex - startIndex;
		if (length <= 1) {
			return;
		}
		
		/* Divide - split in half */
		int middleIndex = startIndex + (length / 2);
		sort(list, startIndex, middleIndex);
		sort(list, middleIndex, endIndex);
		
		/* Conquer - Merging sublists of length 1 onwards */
		int upperStart = startIndex;
		int upperEnd = startIndex + (length / 2);
		int lowerStart = upperEnd;
		int lowerEnd = endIndex;
		
		/* Traversing through list to compare smaller element - n time */
		int i = upperStart;
		int j = lowerStart;
		List<Integer> tmpList = new ArrayList<Integer>();
		while (i < upperEnd || j < lowerEnd) {
			if (i >= upperEnd) {					// check if i & j traverses past the end of the list
				tmpList.add(list.get(j));
				j++;
			}
			else if (j >= lowerEnd) {
				tmpList.add(list.get(i));
				i++;
			}
			
			else {
				if (list.get(i) < list.get(j)) {
					tmpList.add(list.get(i));
					i++;
				}
				else {
					tmpList.add(list.get(j));
					j++;
				}
			}
		}
		
		for (int k = startIndex; k < endIndex; k++) {
			list.set(k, tmpList.get(k - startIndex));
		}
		

	}
	
}
