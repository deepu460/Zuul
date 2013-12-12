package com.harshramesh.zuul.command;

/**
 * This class is part of the "World of Zuul" application. "World of Zuul" is a
 * very simple, text based adventure game.
 * 
 * This class is an enumeration of all command words known to the game. It is
 * used to recognize commands as they are typed in.
 * 
 * @author Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
public enum CommandWord {
	// Commands
	GO, QUIT, HELP, WAIT, TAKE, USE, NARRATE, DANCE, INVENTORY, EXITS, LOOK, CRY, STAND, SIT, CLEAR;

	private static final CommandWord[] commands;

	static {
		commands = CommandWord.class.getEnumConstants();
	}

	// Gets the valid commands
	public static String getValidCommands() {
		StringBuilder builder = new StringBuilder(
				"\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

		// Adds commands to the string builder
		for (int i = 0; i < commands.length; i++) {
			builder.append(commands[i].toString());
			if (i == commands.length - 2)
				builder.append(", & ");
			else if (i < commands.length - 2)
				builder.append(", ");
			if (i != 0 && i % 5 == 0)
				builder.append("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		}
		builder.append("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

		// Returns the list of commands
		return builder.toString();
	}

	// Checks if the string is a command.
	public static boolean isCommand(String aString) {
		for (CommandWord cw : commands) {
			if (cw.toString().equalsIgnoreCase(aString))
				return true;
		}
		// If we get here, the string was not found in the commands
		return false;
	}

	public static CommandWord get(String string) {
		for (int i = 0; i < commands.length; i++) {
			if (commands[i].toString().equalsIgnoreCase(string))
				return commands[i];
		}

		return null;
	}

}
