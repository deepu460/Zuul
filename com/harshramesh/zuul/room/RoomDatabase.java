package com.harshramesh.zuul.room;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.harshramesh.zuul.item.ItemDatabase;
import com.harshramesh.zuul.item.ItemReq;
import com.harshramesh.zuul.narrator.NarratorDatabase;

/**
 * This class is responsible for making and keeping track of all of the rooms.
 * It has a default map loaded into it, and is only used to store the location
 * of all rooms.
 * 
 * @author Harshavardhan Ramesh - Nov 14, 2013
 */
public class RoomDatabase {

	private Map<String, Room> rooms;

	private ItemDatabase iBase;

	private NarratorDatabase nBase;

	public RoomDatabase() {
		super();
		rooms = new HashMap<>();
		iBase = new ItemDatabase();
		nBase = new NarratorDatabase();
		this.setUpRooms();
	}

	private void addBaseExits() {
		// Start
		rooms.get("start").addExit("north", rooms.get("hall 1"));
	}

	private void addExitExits() {
		// Exit 1
		rooms.get("exit 1").addExits(
				new String[] { "north", "east", "south" },
				new Room[] { rooms.get("death"), rooms.get("hall 4"),
						rooms.get("hall 3") });
		// Exit 2
		rooms.get("exit 2").addExits(new String[] { "south", "west" },
				new Room[] { rooms.get("hall 2"), rooms.get("closet 2") });
		// Exit 3
		rooms.get("exit 3").addExits(new String[] { "north", "east" },
				new Room[] { rooms.get("exit 4"), rooms.get("hall 2") });
		// Exit 4
		rooms.get("exit 4").addExits(
				new String[] { "north", "south", "west" },
				new Room[] { rooms.get("end"), rooms.get("exit 3"),
						rooms.get("death") });
	}

	private void addExits() {
		// <--- Initialize room exits --->
		addBaseExits();
		// --- Hallways ---
		addHallExits();
		// --- Recreational rooms ---
		addRecRoomExits();
		// --- Exits ---
		addExitExits();
		// --- Misc. ---
		addMiscExits();
	}

	private void addHallExits() {
		// Hall 1
		rooms.get("hall 1").addExits(
				new String[] { "north", "east", "south", "west" },
				new Room[] { rooms.get("computer room"), rooms.get("kitchen"),
						rooms.get("start"), rooms.get("office") });

		// Hall 2
		rooms.get("hall 2").addExits(
				new String[] { "north", "east", "south", "west" },
				new Room[] { rooms.get("exit 2"), rooms.get("rec room 1"),
						rooms.get("conference room"), rooms.get("exit 3") });

		// Hall 3
		rooms.get("hall 3").addExits(
				new String[] { "north", "east", "south", "west" },
				new Room[] { rooms.get("exit 1"), rooms.get("hall 4"),
						rooms.get("empty closet"), rooms.get("rec room 2") });

		// Hall 4
		rooms.get("hall 4").addExits(
				new String[] { "north", "east", "south" },
				new Room[] { rooms.get("exit 1"), rooms.get("closet 1"),
						rooms.get("hall 3") });
	}

	private void addItemReqs() {
		// <--- Add requirements --->
		rooms.get("empty closet").setRequirement(
				new ItemReq(iBase.get("copper key"),
						"It seems that the door is locked.",
						"You unlocked the door"));
		rooms.get("exit 4").setRequirement(
				new ItemReq(iBase.get("flamethrower"),
						"It seems that some one barricaded the door.",
						"You burned down the wood."));
		rooms.get("exit 3").setRequirement(
				new ItemReq(iBase.get("bronze key"),
						"It seems that the door is locked.",
						"You unlocked the door."));
	}

	private void addItems() {
		// <--- Add iBase --->
		rooms.get("empty closet").addItem(iBase.get("flamethrower"));
		rooms.get("closet 1").addItem(iBase.get("copper key"));
		rooms.get("closet 2").addItem(iBase.get("bronze key"));
		rooms.get("kitchen").addAllItems(
				new String[] { "tuna", "water", "broom" }, iBase);
	}

	private void addMiscExits() {
		// Closets
		rooms.get("closet 1").addExit("west", rooms.get("hall 4"));
		rooms.get("closet 2").addExit("east", rooms.get("exit 2"));
		rooms.get("empty closet").addExit("north", rooms.get("hall 3"));

		// Kitchen
		rooms.get("kitchen").addExit("west", rooms.get("hall 1"));

		// Computer room
		rooms.get("computer room").addExits(
				new String[] { "north", "south", "west" },
				new Room[] { rooms.get("rec room 1"), rooms.get("hall 1"),
						rooms.get("conference room") });

		// Office
		rooms.get("office")
				.addExits(
						new String[] { "east", "west" },
						new Room[] { rooms.get("hall 1"),
								rooms.get("conference room") });

		// Conference room
		rooms.get("conference room").addExits(
				new String[] { "north", "east", "south" },
				new Room[] { rooms.get("hall 2"), rooms.get("office"),
						rooms.get("computer room") });
	}

	private void addNarrators() {
		Iterator<String> keys = nBase.keySet().iterator();

		while (keys.hasNext()) {
			String key = keys.next();
			rooms.get(key).setNarrator(nBase.get(key));
		}

	}

	private void addRecRoomExits() {
		// Recreational room 1
		rooms.get("rec room 1").addExits(
				new String[] { "south", "west", "up" },
				new Room[] { rooms.get("computer room"), rooms.get("hall 2"),
						rooms.get("rec room 2") });
		// Recreational room 2
		rooms.get("rec room 2").addExits(new String[] { "east", "down" },
				new Room[] { rooms.get("hall 3"), rooms.get("rec room 1") });
	}

	public boolean contains(Room room) {
		return rooms.containsValue(room);
	}

	public boolean contains(String string) {
		return rooms.containsKey(string);
	}

	private void genBase() {
		rooms.put("start", new Room(
				"in your cubicle. Boy, isn't this surprising?"));
		rooms.put("death", new Room("death"));
		rooms.put("end", new Room("in the parking lot. You can leave!"));
	}

	private void genClosets() {
		rooms.put("closet 1", new Room("in a abandoned closet. You see a key."));
		rooms.put("closet 2", new Room("in a abandoned closet. You see a key."));
		rooms.put("empty closet", new Room(
				"in a closet. Northing to see here..."
						+ "\nDefinitely not a flamethrower or anything..."));
	}

	private void genExits() {
		rooms.put("exit 1", new Room("in the employee exit."));
		rooms.put("exit 2", new Room("in the employee exit."));
		rooms.put("exit 3", new Room("in the employee exit."));
		rooms.put("exit 4", new Room("in the fire exit."));
	}

	private void genHalls() {
		rooms.put("hall 1", new Room("in a hallway."
				+ "\nWhere are all of your co-workers?"));
		rooms.put("hall 2", new Room("in a hallway"));
		rooms.put("hall 3", new Room("in another hallway. You see an exit."
				+ "\nYou really want to walk towards it."));
		rooms.put("hall 4", new Room("in a hallway."));
	}

	private void genMisc() {
		rooms.put("kitchen", new Room(
				"in the worker kitchen. You still see the effects\nof your last"
						+ " attempt of cooking food for your co-workers."));
		rooms.put("computer room", new Room(
				"in the tech room. IT guys always creeped you out.\n"
						+ "You wonder where they might be today."));
		rooms.put("office", new Room(
				"in your boss's office. You think he might be taking his break right now."
						+ "\nIt's not anything totally foreign for him to do."));
		rooms.put("conference room", new Room("in the conference room."));
	}

	private void genRecRooms() {
		rooms.put("rec room 1", new Room(
				"in the employee recreation room.\nYou don't remember"
						+ " all of those creepy paintings,\n"
						+ "but it's only a game, right?" + "\nRight?"));
		rooms.put("rec room 2", new Room("in the top floor of the rec room.\n"
				+ "Where is everyone?"));
	}

	private void genRooms() {
		// <--- Creates the rooms --->
		genBase();
		// Hallways
		genHalls();
		// Rec rooms
		genRecRooms();
		// Exits
		genExits();
		// Closets
		genClosets();
		// Misc.
		genMisc();
	}

	public Room get(String string) {
		return rooms.get(string);
	}

	public String[] getKeys() {
		int count = 0;
		Set<String> keySet = rooms.keySet();
		Iterator<String> iterator = keySet.iterator();
		String[] array = new String[keySet.size()];
		while (iterator.hasNext()) {
			array[count] = iterator.next();
			count++;
		}
		return array;
	}

	public Map<String, Room> getRooms() {
		return rooms;
	}

	public void put(String key, Room room) {
		rooms.put(key, room);
	}

	public void setRooms(Map<String, Room> rooms) {
		this.rooms = rooms;
	}

	private void setUpRooms() {
		this.genRooms();
		this.addExits();
		this.addItems();
		this.addItemReqs();
		this.addNarrators();
	}

}