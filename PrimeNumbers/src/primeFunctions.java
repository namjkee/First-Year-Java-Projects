import java.util.ArrayList;

public class primeFunctions {
	
	public static boolean isPrime(int n) {
		// composite number with the exception of two - return false
		if (n == 1 || n == 0 || n < 1) return false;
		
		if (n % 2 == 0 && n != 2) return false;

		for (int i = 3; i < n; i += 2) {
			if (n % i == 0) {				// checks for odd factors of non-composite number that isn't 1
				return false;
			}
		}
		return true;
	}
	
	public static int nthPrime(int n) {
		int counter = 0;
		
		for (int i = 0; i < (int) Math.pow(2, 31); i++) {
			if (counter == n) return i - 1;
			if (isPrime(i)) {
				counter++;
			}
		}
		return 0;
	}
	
	public static ArrayList<Integer> primeSequence(int n) {
		ArrayList<Integer> primeS = new ArrayList<>();
		int counter = 0;
		
		for (int i = 0; i < (int) Math.pow(2, 31); i++) {
			if (counter == n) return primeS;
			if (isPrime(i)) {
				primeS.add(i);
				counter++;
			}
		}
		return null;
	}
	
}
