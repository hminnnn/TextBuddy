package textbuddy;

import static org.junit.Assert.*;

import org.junit.Test;

public class TextBuddyTest {
/*	
	@Test
	public void testIsAdded() {
		TextBuddy.executeCommand("add this is a brown fox");
		assertTrue(TextBuddy.isAdded("this is a brown fox"));
		
		TextBuddy.executeCommand("addd this cannot be added");
		assertFalse(TextBuddy.isAdded("addd this cannot be added"));
	}
	*/
	@Test
	public void testDisplay() {
		// display
		TextBuddy.executeCommand("add this is line one");
		assertEquals("display 1 line.", "1. this is line one", TextBuddy.executeCommand("display"));
	}



}


