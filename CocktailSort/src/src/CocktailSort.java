import java.util.List;

public class CocktailSort {
	
	public static void sort(List<Integer> list) {
		if (list.size() <= 1) return;
		int size = list.size();
		boolean swapped = false;
		boolean singleSwap = true;
		boolean forward = false;
		
		while (singleSwap)  {
			singleSwap = false;
			forward = true;
			for (int i = 0; i < size;) {					// forward swapping
				if ((list.get(i) > list.get(i + 1)) && forward) {
					int temp = list.get(i);
					list.set(i, list.get(i + 1));
					list.set(i + 1, temp);
					singleSwap = true;
					if (i == size - 2 && forward) {
						if (!singleSwap) return;
						forward = false;
						size--;
						continue;
					}
					else if (i == 1 && !forward) {
						if (!singleSwap) return;
						forward = true;
						size--;
						continue;
					}
					// UPDATE section
					if (forward) i++;
					else i--;
					continue;
				}
				
				else if (!forward) {					// reverse swapping
					if (list.get(i) < list.get(i - 1)) {
						int temp = list.get(i);
						list.set(i, list.get(i - 1));
						list.set(i - 1, temp);
						singleSwap = true;
					}
					if (i == size - 2 && forward) {
						if (!singleSwap) return;
						forward = false;
						continue;
					}
					else if (i == 1 && !forward) {
						if (!singleSwap) return;
						forward = true;
						continue;
					}
					// UPDATE section
					if (forward) i++;
					else i--;
					continue;
				}
				//////////////////////////////////////////////////////////////
				if (i == size - 2 && forward) {
					if (!singleSwap) return;
					forward = false;
					size--;
					continue;
				}
				else if (i == 1 && !forward) {
					if (!singleSwap) return;
					forward = true;
					continue;
				}
				// UPDATE section
				if (forward) i++;
				else i--;
			}
		}
					
	}
	
}
