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
	public void testIsSorted() {
		
		TextBuddy.executeCommand("add zzz");
		TextBuddy.executeCommand("add bbb");
		TextBuddy.executeCommand("add sss");
		TextBuddy.sort();
		assertEquals("[bbb, sss, zzz]", TextBuddy.isSorted());
		
	}
	
	@Test
	public void testSearch() {
		
		TextBuddy.executeCommand("add hello there");
		TextBuddy.executeCommand("add huimin is here!");
		TextBuddy.executeCommand("add abcdefg");
		TextBuddy.executeCommand("add qwerty");
		TextBuddy.executeCommand("search huimin");
		assertEquals("[huimin is here!]", TextBuddy.search());
		
	}

}


