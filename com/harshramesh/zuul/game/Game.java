package com.harshramesh.zuul.game;

import static com.harshramesh.zuul.command.CommandWord.getValidCommands;
import static java.lang.System.exit;

import java.util.Iterator;
import java.util.List;

import com.harshramesh.zuul.command.Command;
import com.harshramesh.zuul.command.CommandWord;
import com.harshramesh.zuul.item.Inventory;
import com.harshramesh.zuul.item.Item;
import com.harshramesh.zuul.item.ItemReq;
import com.harshramesh.zuul.room.Room;
import com.harshramesh.zuul.room.RoomDatabase;

/**
 * This class is the main class of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game. Users can walk
 * around some scenery. That's all. It should really be extended to make it more
 * interesting!
 * 
 * To play this game, create an instance of this class and call the "play"
 * method.
 * 
 * This main class creates and initializes all the others: it creates the parser
 * and starts the game. It also evaluates and executes the commands that the
 * parser returns.
 * 
 * @author Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
public class Game {

	private Parser parser;

	private Room currentRoom;

	private List<Item> inventory;

	private RoomDatabase rBase;

	/**
	 * Create the game and initialize its internal map.
	 */
	public Game() {
		super();
		initialize();
	}

	private void clearScreen() {
		for (int i = 0; i < 1000; i++) {
			System.out.println();
		}
	}

	private void cry() {
		System.out.println("You cried for a good hour.\n"
				+ "You feel so much better!");
	}

	private void dance() {
		System.out.println("You dance...\n"
				+ "What a great way to spend your time!");
	}

	public Room getCurrentRoom() {
		return currentRoom;
	}

	private void getRoomExits(Room room) {
		System.out.println(room.getExitList());
		System.out.println();
	}

	/**
	 * Try to go to one direction. If there is an exit, enter the new room,
	 * otherwise print an error message.
	 */
	private void goRoom(Command command) {
		if (!command.hasSecondWord()) {
			// If there is no second word, we don't know where to go...
			System.out.println("Go where?");
			return;
		}

		String direction = command.getSecondWord();

		// Try to leave current room.
		Room nextRoom = null;

		if (currentRoom.hasExit(direction))
			nextRoom = currentRoom.getExit(direction);

		if (nextRoom == null)
			System.out.println("You walk into a wall.\n"
					+ "You feel sorry for yourself");
		else {
			if (nextRoom.equals(rBase.get("death"))) {
				nextRoom = rBase.get("start");
				System.out.println("You don't seem to remember"
						+ " anything after that...");
			} else if (nextRoom.equals(rBase.get("end")))
				winSequence();

			if (nextRoom.hasRequirement())
				if (nextRoom.getRequirement().isActive()) {
					System.out.println(nextRoom.getRequirement().getDesc());
					return;
				}

			currentRoom = nextRoom;
			printRoom();
		}
	}

	private void initialize() {
		rBase = new RoomDatabase();
		parser = new Parser();
		inventory = new Inventory<>();
		currentRoom = rBase.get("start"); // start game outside
	}

	private void inventory() {
		System.out.println("Your inventory: "
				+ (inventory.isEmpty() ? "nothing!" : inventory));
	}

	private void look() {
		printRoom();
		System.out.println("Items in this room: "
				+ (currentRoom.hasItems() ? currentRoom.getItemNames()
						: "Nothing!"));
	}

	/**
	 * Main play routine. Loops until end of play.
	 */
	public void play() {
		printWelcome();

		// Enter the main command loop. Here we repeatedly read commands and
		// execute them until the game is over.

		boolean finished = false;
		while (!finished) {
			Command command = parser.getCommand();
			finished = processCommand(command);
		}
		System.out.println("Thank you for playing.  Good bye.");
	}

	/**
	 * Print out some help information. Here we print some stupid, cryptic
	 * message and a list of the command words.
	 */
	private void printHelp() {
		System.out.println("You are lost. You are alone. You panic...\n");
		System.out.println("Then you remember your faithful words: \n"
				+ getValidCommands());
	}

	private void printRoom() {
		printRoom(currentRoom);
	}

	private void printRoom(Room room) {
		System.out.println("\nYou are " + room.getDescription());
		System.out.print("Exits: ");
		getRoomExits(room);
		if (room.hasNarrator()) {
			String text = room.getNarrator().getNextMessage();
			System.out.print(text != null ? "A disembodied voice says, \""
					+ text + "\"\n" : "");
		}
	}

	/**
	 * Print out the opening message for the player.
	 */
	private void printWelcome() {
		System.out.println("This is the story of Bob the Bored Worker.");
		System.out.println("He lives the ordinary life.");
		System.out.println("Until one day...");
		printRoom();
	}

	/**
	 * Given a command, process (that is: execute) the command.
	 * 
	 * @param command
	 *            The command to be processed.
	 * @return true If the command ends the game, false otherwise.
	 */
	private boolean processCommand(Command command) {
		if (command == null)
			return false;
		if (command.isUnknown()) {
			System.out.println("I don't know what you mean...\n");
			return false;
		}

		CommandWord commandWord = command.getCommandWord();

		switch (commandWord) {
		case HELP:
			printHelp();
			break;
		case GO:
			goRoom(command);
			break;
		case QUIT:
			return quit(command);
		case DANCE:
			dance();
			break;
		case WAIT:
			waitInRoom();
			break;
		case TAKE:
			take(command);
			break;
		case INVENTORY:
			inventory();
			break;
		case LOOK:
			look();
			break;
		case USE:
			use(command);
			break;
		case EXITS:
			System.out.print("Exits: ");
			getRoomExits(currentRoom);
			break;
		case CRY:
			cry();
			break;
		case SIT:
			sit();
			break;
		case STAND:
			stand();
			break;
		case CLEAR:
			clearScreen();
			break;
		default:
			System.out.println("Say what?");
			break;
		}

		return false;
	}

	/**
	 * "Quit" was entered. Check the rest of the command to see whether we
	 * really quit the game.
	 * 
	 * @return true, if this command quits the game, false otherwise.
	 */
	private boolean quit(Command command) {
		if (command.hasSecondWord()) {
			if (command.getSecondWord().equalsIgnoreCase("game")
					|| command.getSecondWord().equalsIgnoreCase("this"))
				return true; // signal that we want to quit
			System.out.println("How does one quit " + command.getSecondWord()
					+ "?");
			return false;
		}
		System.out.println("Quit what?");
		return false;
	}

	private void sit() {
		System.out.println("You sat on the floor");
	}

	private void stand() {
		System.out.println("You stand.");
	}

	private void take(Command command) {
		if (command == null)
			return;
		else if (command.getSecondWord() == null) {
			System.out.println("Take what?");
			return;
		}
		String word = command.getSecondWord();
		if (currentRoom.hasItems()) {
			Item item = currentRoom.getItem(word);
			if (item == null)
				System.out.println("What the heck is a " + word + "?");
			else if (inventory.contains(item))
				System.out.println("You already have a " + word + "!");
			else {
				inventory.add(item);
				System.out.println("You took a " + item.getName() + "!");
			}
		} else
			System.out.println("What the heck is a " + word + "?");
	}

	private void use(Command command) {
		if (!command.hasSecondWord()) {
			System.out.println("Use what?");
			return;
		}
		String secondWord = command.getSecondWord();

		if (currentRoom.equals(rBase.get("computer room"))
				|| currentRoom.equals(rBase.get("office"))
				|| currentRoom.equals(rBase.get("start"))) {

			if (secondWord.equalsIgnoreCase("computer")) {
				System.out.println("You waste 5 hours on facebook. Congrats!"
						+ "\nThen you go onto certain sites that"
						+ " your mother would not aprove of...");
				return;
			}
		}

		if (!inventory.isEmpty()) {
			for (Item item : inventory) {

				if (secondWord.equalsIgnoreCase(item.getName())) {
					Iterator<ItemReq> it = currentRoom.getNeighborReqs()
							.iterator();
					if (it == null) {
						System.out.println("There is nothing to use "
								+ secondWord + " on!");
						return;
					}

					while (it.hasNext()) {
						ItemReq itrq = it.next();
						if (!itrq.isActive() && itrq.getKey().equals(item)) {
							System.out.println("You've already used the "
									+ item.getName());
							return;
						}

						if (itrq.getKey().equals(item)) {
							itrq.unlock(item);
							return;
						}
					}
				}
			}
		}
	}

	private void waitInRoom() {
		System.out.println("You waited. Nothing happend.\n"
				+ "What did you expect?");
	}

	private void winSequence() {
		System.out.println("You have won!");
		System.out.print("Press enter to contine...");
		parser.nextLine();
		parser.close();
		exit(0);
	}

}
