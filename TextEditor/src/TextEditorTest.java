import static org.junit.Assert.*;

import org.junit.Test;


public class TextEditorTest {
	TextEditor test = new TextEditor();
	
	@Test
	public void testConstruction() {
		TextEditor t = new TextEditor();
		assertEquals("", t.readText());
	}
	
	@Test
	public void testTypeCharacter() {
		test.typeCharacter('T');
		assertEquals("T", test.readText());
		test.typeCharacter('h');
		assertEquals("Th", test.readText());
		test.typeCharacter('e');
		assertEquals("The", test.readText());
	}
	
	@Test
	public void testDeleteCharacter() {
		test.typeCharacter('T');
		test.typeCharacter('h');
		test.typeCharacter('e');
		
		test.deleteLastCharacter();
		assertEquals("Th", test.readText());
		test.deleteLastCharacter();
		assertEquals("T", test.readText());
		test.deleteLastCharacter();
		assertEquals("", test.readText());
	}
	
	@Test
	public void testUndo() {
		test.typeCharacter('T');
		test.typeCharacter('h');
		test.typeCharacter('e');
		
		test.undo();
		assertEquals("Th", test.readText());
		test.undo();
		assertEquals("T", test.readText());
		test.undo();
		assertEquals("", test.readText());
	}

}
