import java.util.ArrayList;
import java.util.List;


public class Hailstone {

	public static List<Integer> hailstone(int n) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		if (n == 1) {
			list.add(1);
			return list;
		}
		
		while (n != 1) {
			while (n % 2 == 0) {
				list.add(n);
				if (n == 1) return list;
				n = n / 2;
			}
			
			while (n % 2 == 1) {
				list.add(n);
				if (n == 1) return list;
				n = (3 * n) + 1;
			}
		}
		return list;
	}
	
	public static void main(String[] args) {
		System.out.println(hailstone(4));
	}
	
}
