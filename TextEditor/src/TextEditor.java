
public class TextEditor {
	// instance variables
	private Stack<String> text;
	
	public TextEditor() {
		this.text = new BasicStack<>();
	}
	
	// Called when the given character is typed into the editor
    public void typeCharacter(char c) {
//    	if (text.isEmpty()) {
//			String s = "";
//			s += c;
//			text.push(s);
//			return;
//		}
//    	
//		try {
//			
//			String s = text.pop();
//	    	s += c;
//	    	this.text.push(s);
//		} catch (EmptyStackException e) {
//			return;
//		}
    	if (text.isEmpty()) {
    		String s = "";
    		s += c;
    		text.push(s);
    		return;
    	}
    	
    	String s = "";
    	s += c;
    	text.push(s);
    }
    
    // Called when the backspace key is pressed; has no effect if the document is empty
    public void deleteLastCharacter() {
//    	try {
//    	   	String s = text.pop();
//        	s = s.substring(0, s.length() - 1);
//    		this.text.push(s);
//    	} catch (EmptyStackException e) {
//    		// empty string
//    		return;	
//    	}
    	String s = text.top();
	}
    
    // Returns the text the editor should display on the screen
    public String readText() {
		try {
			return text.top();
		} catch (EmptyStackException e) {
			return "";
		}
	}
    
    // Undoes the previous action
    public void undo() {
    	try {
			this.text.pop();
		} catch (EmptyStackException e) {
			return;
		}
	}
}
