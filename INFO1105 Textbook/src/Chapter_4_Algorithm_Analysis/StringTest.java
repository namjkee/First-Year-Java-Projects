package Chapter_4_Algorithm_Analysis;

public class StringTest {
	
	public static String repeat1(char c, int n) {
		String answer = "";
		for (int i = 0; i < n; i++) {
			answer += c;
		}
		return answer;
	}
	
	public static String repeat2(char c, int n) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			sb.append(c);
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {

		/* Experimental Analysis - Implementation test */

		/* String Class */
		long startTime = System.currentTimeMillis(); // Starting time of String Algorithm
		repeat1('a', 10000);
		long endTime = System.currentTimeMillis(); // Ending time of String Algorithm
		long elapsed = endTime - startTime;
		
		System.out.println("Run time of String Algorithm is " + elapsed);	
		
		long startTime1 = System.currentTimeMillis(); // Starting time of StringBuilder Algorithm
		repeat2('a', 10000);
		long endTime1 = System.currentTimeMillis(); // Ending time of StringBuilder Algorithm
		long elapsed1 = endTime1 - startTime1;
		
		System.out.println("Run time of StringBuilder algorithm is " + elapsed1); 
	}
	
}
