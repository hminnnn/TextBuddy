package textbuddy;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Assumptions: commands are correctly entered.
 * @author huimin
 *
 */
public class TextBuddy {
	
	private static ArrayList<String> textFile = new ArrayList<String>();
	private static String restOfText;
	private static String fileName;
	private static Scanner sc = new Scanner(System.in);
	
	private static final String MESSAGE_INVALID_COMMAND = "Invalid command";
	private static final String MESSAGE_NO_FILE_NAME = "File name not specified.";
	private static final String WELCOME_MESSAGE = "Welcome to TextBuddy. %1$s is ready for use";
	private static final String MESSAGE_FILE_ADDED = "added to %1$s: \"%2$s\"";
	private static final String MESSAGE_FILE_CLEARED = "all content deleted from %1$s";
	private static final String MESSAGE_FILE_EMPTY_DISPLAY = "%1$s is empty";
	
	enum COMMAND_TYPE {
		ADD, DELETE, CLEAR, DISPLAY, EXIT, INVALID, SORT, SEARCH
	};
	
	public static void main(String[] arg) {
		String userInput;
		fileName = arg[0];
		checkFileValid();
		welcomeMsg();
		String toDisplay;
		
		while (true) {
			System.out.print("command: ");
			userInput = sc.nextLine();
			toDisplay = executeCommand(userInput);
			System.out.println(toDisplay);
			System.out.println(" ");
			saveFile();
		}
	}

	/**
	 * This operation checks if the file name is valid.  
	 */
	public static void checkFileValid() {
		if (fileName.isEmpty()) {
			System.out.println(String.format(MESSAGE_NO_FILE_NAME));
		}
	}
	
	public static void welcomeMsg() {
		System.out.println(String.format(WELCOME_MESSAGE, fileName));
	}
	
	/**
	 * This operation determines which of the supported command types the user wants to perform
	 */
	public static String executeCommand(String userInput) {
		
		String userCommand = splitText(userInput);
		COMMAND_TYPE commandType = determineCommandType(userCommand);

		switch (commandType) {
			case ADD :
				return add();
	
			case DELETE :
				return delete();
	
			case CLEAR :
				return clear();
	
			case DISPLAY :			
				return display();
	
			case EXIT :
				sc.close();
				System.exit(0);
				break;
	
			case INVALID :
				return String.format(MESSAGE_INVALID_COMMAND);
	
			default :
				throw new Error(MESSAGE_INVALID_COMMAND);
		}
		
		return MESSAGE_INVALID_COMMAND;

	}
	
	/**
	 * This operation splits the string into the user's command and the rest of the text.
	 * @param userInput
	 * @return userCommand = the first word.
	 */
	public static String splitText(String userInput) {
		String[] textArr = userInput.split(" ", 2);
		if (textArr.length > 1 ) {
			restOfText = textArr[1];
		} else {
			restOfText = "";
		}
		return textArr[0];
	}
	
	/**
	 * This operation determines which of the supported command types the user wants to perform
	 * @param userInput is the first word of the user command
	 */
	public static COMMAND_TYPE determineCommandType(String userInput) {
	
		if (userInput == null) {
			throw new Error(MESSAGE_INVALID_COMMAND);
		}
		
		if (userInput.equalsIgnoreCase("add")) {
			return COMMAND_TYPE.ADD;
		} else if (userInput.equalsIgnoreCase("delete")) {
			return COMMAND_TYPE.DELETE;
		} else if (userInput.equalsIgnoreCase("display")) {
		 	return COMMAND_TYPE.DISPLAY;
		} else if (userInput.equalsIgnoreCase("clear")) {
			return COMMAND_TYPE.CLEAR;
		} else if (userInput.equalsIgnoreCase("exit")) {
			return COMMAND_TYPE.EXIT;
		} else {
			return COMMAND_TYPE.INVALID;
		}
	}
	
	public static String display() {
		if (!textFile.isEmpty()) {
			int size = 1;
			for (int i = 0; i < textFile.size()-1; i++) {
				System.out.println(size +". " + textFile.get(i));
				size++;
			}
			// prints the last line in textFile as toDisplay will be printed last. 
			return size + ". " + textFile.get(textFile.size()-1);
		} else {
			return String.format(MESSAGE_FILE_EMPTY_DISPLAY, fileName);
		}
	}

	public static String clear() {
		textFile.clear();
		return String.format(MESSAGE_FILE_CLEARED, fileName);
	}

	public static String delete() {
		int indexToDelete = Integer.parseInt(restOfText.substring(0));
		
		// Integer to delete is invalid
		if ((indexToDelete > textFile.size()) || (indexToDelete < 1)) {
			 return MESSAGE_INVALID_COMMAND;
		} else {
			String deletedLine = textFile.get(indexToDelete-1);
			textFile.remove(indexToDelete-1);
			return "deleted from " + fileName + " \"" + deletedLine + "\"";
		}
	}

	public static String add() {
		if (restOfText.isEmpty()) {
			return String.format(MESSAGE_INVALID_COMMAND);
		}
		textFile.add(restOfText);	
		isAdded(restOfText);
		return String.format(MESSAGE_FILE_ADDED, fileName, restOfText);
	}
	
	public static boolean isAdded(String text) {
		if (textFile.get(textFile.size()-1).equals(text)) {
			return true;
		}
		return false;
	}
	
	/**
	 * This operation saves the file. If file already exists, it will be overwritten. 
	 */
	public static void saveFile() {
		try {
			BufferedWriter fileWrite = new BufferedWriter(new FileWriter(fileName));
			Iterator<String> textFileItr = textFile.iterator();
			while (textFileItr.hasNext()) {
				fileWrite.write(textFileItr.next().toString());
				fileWrite.newLine();
			}	
			fileWrite.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}