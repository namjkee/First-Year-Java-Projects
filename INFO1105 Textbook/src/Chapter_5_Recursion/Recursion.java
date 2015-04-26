package Chapter_5_Recursion;

public class Recursion {
	
	public static int getMax(int[] A, int n) {
		if (n == 1) { // Base Case
			return A[0];
		}
		int m = n - 1;
		for (int i = 0; i < A.length; i++) {
			if (A[m] < A[i]) {
				break;
			}
			if (i == A.length - 1) {
				return A[m];
			}
		}
		return getMax(A, m);
	}
	
	public static void main(String[] args) {
		int[] data = new int[]{100, 10, 6, 8, 9, 5};
		long startTime = System.currentTimeMillis();
		getMax(data, 6);
		long endTime = System.currentTimeMillis();
		long elapsed = endTime - startTime;
		System.out.println(elapsed);
		
		System.out.println(getMax(data, 6));
	}
	
}
