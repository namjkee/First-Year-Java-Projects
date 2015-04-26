
public class SimpleCalc {
	public static void main(String[] args) {
		// writing argument arrays for input
		double n = Integer.parseInt(args[0]);
		// control flow statements using if/else
		if (n < 0) {
			n =  Math.pow(n,2) + n/2;
			System.out.print(n);
		}
		else if (n >= 0 && n % 2 == 0) {
			n =  (int) Math.pow(n,3)*(n-1); 
			System.out.print(n);
		}
		else if (n >= 0 && n % 2 == 1 ) {
			n =  Math.pow(n,-1) + 3*n;
			System.out.print(n);
		}
	}
}
