import java.util.List;

public class CombSort {
	public static void sort(List<Integer> list) {
		int gap = list.size();
		boolean swapped = true;
		while (gap > 1 || !swapped) {
			swapped = false;
			gap = (int) Math.floor(Math.max(gap / 1.3, 1));

			for (int i = 0; gap + i < list.size(); i++) {
				if (list.get(i) > list.get(i + gap)) {
					int temp = list.get(i);
					list.set(i, list.get(i + gap));
					list.set(i + gap, temp);
					swapped = true;
				}
			}
		}
	}
}
