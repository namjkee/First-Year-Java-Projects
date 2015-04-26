import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TextbookManager {
	private GenericHashMap<String, List<String>> map;
	
	public TextbookManager() {
		this.map = new GenericHashMap<>(10);
	}
	
	/**
	 * Saves that the given textbook is required for this subject
	 */
	public void addTextbook(String subject, String textbook) {
		if (map.get(subject) == null) {
			map.put(subject, new ArrayList<String>());
		}
		map.get(subject).add(textbook);
	}
	
	/**
	 * Returns a list of all textbooks needed for the given subject, in the order they were added
	 * Returns null if the subject doesn't exist
	 */
	public List<String> getTextbooks(String subject) {
		return map.get(subject);
	}
	
	public static void main(String[] args) {
		TextbookManager t = new TextbookManager();
		
		t.addTextbook("ENGL3633", "Anglo-Saxon Writings");
		t.addTextbook("ENGL3633", "Old English");
		
		// Returns a list of {"Anglo-Saxon Writings", "Old English"}
//		t.getTextbooks("ENGL3633");
		System.out.println(t.getTextbooks("ENGL3633"));
	}
	
}
