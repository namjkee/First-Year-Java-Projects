package Chapter_6_Stacks_Queries_and_Deques;

import java.util.Arrays;

public class reverseArrays {
	
	public static <E> void reverse(E[] a) {
		Stack<E> buffer = new ArrayStack<>(a.length);
		for (int i = 0; i < a.length; i++) {
			buffer.push(a[i]);
		}
		for (int i = 0; i < a.length; i++) {
			a[i] = buffer.pop();
		}
	}
	
	public static void main(String[] args) {
		Integer[] a = {4, 8, 15, 16, 23, 42};
		String[] s = {"Jack", "Kate", "Hurley", "Jin", "Michael"};
		System.out.println("a = " + Arrays.toString(a));
		System.out.println("s = " + Arrays.toString(s));
		System.out.println("reversing...");
		reverse(a);
		reverse(s);
		System.out.println("a = " + Arrays.toString(a));
		System.out.println("s = " + Arrays.toString(s));
	}

}
