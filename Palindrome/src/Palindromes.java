
public class Palindromes {
	
	/**
	 * Returns true if word is palindrome, and false if not
	 */
	public static boolean isPalindrome(String word) throws EmptyQueueException, EmptyStackException {
		Stack<Character> stack = new BasicStack<Character>();
		Queue<Character> queue = new BasicQueue<Character>();
		
		if (word.length() == 0) return true;
		
		for (int i = 0; i < word.length(); i++) {
			Character c = word.charAt(i);
			stack.push(c);
			queue.enqueue(c);
		}
		
		/* Prevent EmptyStackException or EmptyQueueException */
		if (stack.size() == 0 || queue.size() == 0) {
			return true;
		}
		
		while (stack.top() == queue.front()) {
			if (stack.size() == 1 || queue.size() == 1) {
				return true;
			}
			stack.pop();
			queue.dequeue();
		}
		return false;
	}
	
	public static boolean isPalindromeSentence(String sentence) throws EmptyQueueException, EmptyStackException {
		Stack<Character> stack = new BasicStack<Character>();
		Queue<Character> queue = new BasicQueue<Character>();
		
		if (sentence.length() == 0) return true;
		
		for (int i = 0; i < sentence.length(); i++) {
			Character c = sentence.charAt(i);
			
			if (Character.isAlphabetic(c)) {
				c = Character.toLowerCase(c);
				stack.push(c);
				queue.enqueue(c);
			}
			else {
				continue;
			}
		}
		
		/* Prevent EmptyStackException or EmptyQueueException */
		if (stack.size() == 0 || queue.size() == 0) {
			return true;
		}
		
		while (stack.top() == queue.front()) {
			if (stack.size() == 1 || stack.size() == 0) {
				return true;
			}
			stack.pop();
			queue.dequeue();
		}
		return false;
	}
	
	public static void main(String[] args) throws EmptyStackException, EmptyQueueException {
/*		System.out.println(isPalindromeSentence("Madam, I'm Adam"));
		System.out.println(isPalindromeSentence("Eye"));
		System.out.println(isPalindromeSentence("s"));
		System.out.println(isPalindromeSentence(""));
		System.out.println(isPalindromeSentence("Hello world"));	*/
//		System.out.println(isPalindromeSentence("#$@$"));
		System.out.println(isPalindromeSentence("@#@s"));
	}
}