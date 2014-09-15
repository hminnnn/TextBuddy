package textbuddy;

import static org.junit.Assert.*;

import java.util.ArrayList;

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
	public void testSearch1() {
		
		TextBuddy.executeCommand("add hello there");
		TextBuddy.executeCommand("add huimin is here!");
		TextBuddy.executeCommand("add abcdefg");
		TextBuddy.executeCommand("add qwerty");
		TextBuddy.executeCommand("search huimin");
		
		assertEquals("[huimin is here!]", TextBuddy.searched().toString());
		
	}
	
	@Test
	public void testSearch2() {
		
		ArrayList<String> testArray = new ArrayList<String>();
		ArrayList<String> actualOutput = TextBuddy.searched();
		
		TextBuddy.executeCommand("add hello there");
		TextBuddy.executeCommand("add hello here");
		TextBuddy.executeCommand("add hello everywhere");
		TextBuddy.executeCommand("add hello again");
		TextBuddy.executeCommand("search hello");
		
		testArray.add("hello there");
		testArray.add("hello here");
		testArray.add("hello everywhere");
		testArray.add("hello again");
		
		
		for(int i = 0; i < testArray.size(); i++) {
			   if(!testArray.get(i).equals(actualOutput.get(i))) {
				   fail();
		//assertEquals(testArray.get(i), actualOutput.get(i));
			   }
		}
		
	}
	

}


