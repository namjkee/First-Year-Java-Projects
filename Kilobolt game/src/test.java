import java.util.ArrayList;
import java.util.List;




public class test {
	
	public static void main(String[] args) {
		List<String> C = new ArrayList<String>();
		C.add("orange");
		C.add("apples");
		C.add("bananas");
		
		List<Integer> values = new ArrayList<Integer>();
		List<Integer> copy = new ArrayList<Integer>();
		// say n = 10
		for (Integer j = 0; j < 10; j++) {
			values.add(j); //each time, add new element to collection
			copy.add(j);
		}
		System.out.println(values.toString());
		System.out.println(copy.toString());
		
		
		
	}
	
}
