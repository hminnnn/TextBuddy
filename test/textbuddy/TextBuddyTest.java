package textbuddy;

import static org.junit.Assert.*;

import org.junit.Test;

public class TextBuddyTest {
	
	@Test
	public void testCommand() {
		// isAdded
		TextBuddy.executeCommand("add this is a brown fox");
		assertTrue(TextBuddy.isAdded("this is a brown fox"));
	}



}


