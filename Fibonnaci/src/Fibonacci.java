
public class Fibonacci {
	
	/**
	 * Given a number n, return a stack of the first n fibonacci numbers
	 */
	public static Stack<Integer> getNumbers(int n) throws EmptyStackException {
		Stack<Integer> stack = new BasicStack<Integer>();
		
		/* Special cases */
		if (n == 0) return stack;
		if (n == 1) {
			stack.push(1);
			return stack;
		}
		
		/* Base Cases */
		Integer a = 1;
		Integer b = 1;
		stack.push(a);
		stack.push(b);
		
		while (stack.size() != n) {
			Integer c = a + b;
			a = b;
			b = c;
			stack.push(c);
		}
		return stack;
	}
	
	public static void main(String[] args) throws EmptyStackException {
		int fibonnaciSum = getNumbers(5).top();
		System.out.println(fibonnaciSum);
	}
}
