package textbuddy;

import static org.junit.Assert.*;

import org.junit.Test;

public class TextBuddyTest {

	@Test
	public void testIsAdded() {
		
		// add normal text
		TextBuddy.executeCommand("add this is a brown fox");
		assertTrue(TextBuddy.isAdded("this is a brown fox"));
		
		// add command wrong.
		TextBuddy.executeCommand("addd this cannot be added");
		assertFalse(TextBuddy.isAdded("addd this cannot be added"));
		
		//add nothing
		TextBuddy.executeCommand("add");
		assertFalse("nothing was added",TextBuddy.isAdded("add"));
		
	}
	
	@Test
	public void testDisplay() {
		// display
		TextBuddy.executeCommand("add this is line one");
		assertEquals("display 1 line.", "1. this is line one", TextBuddy.executeCommand("display"));
	}

}


