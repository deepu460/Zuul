package com.harshramesh.zuul.room;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.harshramesh.zuul.item.Item;
import com.harshramesh.zuul.item.ItemDatabase;
import com.harshramesh.zuul.item.ItemReq;
import com.harshramesh.zuul.narrator.Narrator;

/**
 * Class Room - a room in an adventure game.
 * 
 * This class is part of the "World of Zuul" application. "World of Zuul" is a
 * very simple, text based adventure game.
 * 
 * A "Room" represents one location in the scenery of the game. It is connected
 * to other rooms via exits. The exits are labeled north, east, south, west. For
 * each direction, the room stores a reference to the neighboring room, or null
 * if there is no exit in that direction. It also stores a list of items that
 * are currently in this room along with any requirements and narration.
 * 
 * @author Michael Kolling, David J. Barnes, & Harsh Ramesh
 * @version Alpha 5
 */
public class Room {

	private String description;

	private Map<String, Room> exits;

	private List<Item> items;

	private ItemReq requirement;

	private Narrator narrator;

	/**
	 * Create a room described "description". Initially, it has no exits.
	 * "description" is something like "a kitchen" or "an open court yard".
	 * 
	 * @param description
	 *            The room's description.
	 */
	public Room(String description) {
		this.description = description;
		exits = new HashMap<>();
		items = new ArrayList<>();
	}

	public void addAllItems(String[] keys, ItemDatabase dBase) {
		for (String string : keys) {
			Item item = dBase.get(string);
			if (item != null)
				items.add(item);
		}
	}

	public Room addExit(String desc, Room room) {
		if (desc == null || room == null)
			return room;
		return exits.put(desc, room);
	}

	public void addExits(Map<? extends String, ? extends Room> map) {
		if (map != null)
			exits.putAll(map);
	}

	public void addExits(String[] keys, Room[] rooms) {
		if (keys == null || rooms == null)
			return;
		if (keys.length != rooms.length)
			return;
		for (int i = 0; i < keys.length; i++) {
			exits.put(keys[i], rooms[i]);
		}
	}

	public void addItem(Item item) {
		items.add(item);
	}

	public void addItems(Item[] items) {
		this.items.addAll(Arrays.asList(items));
	}

	public Item getItem(String name) {
		Iterator<Item> iterator = items.iterator();

		while (iterator.hasNext()) {
			Item item = iterator.next();
			if (item.getName().equalsIgnoreCase(name))
				return item;
		}

		return null;
	}

	public String[] getExitNames() {
		Set<String> set = exits.keySet();
		return set.toArray(new String[set.size()]);
	}

	public String getExitList() {
		Set<String> set = exits.keySet();
		Iterator<String> iterator = set.iterator();
		StringBuilder builder = new StringBuilder();

		while (iterator.hasNext()) {
			builder.append(iterator.next() + " ");
		}

		return builder.toString();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Map<String, Room> getExits() {
		return exits;
	}

	public void setExits(Map<String, Room> exits) {
		this.exits = exits;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Room other = (Room) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		return true;
	}

	public boolean hasItems() {
		return items == null ? false : items.size() > 0;
	}

	public boolean hasExit(String name) {
		return exits.containsKey(name);
	}

	public Room getExit(String name) {
		return exits.get(name);
	}

	public String getItemNames() {
		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < items.size(); i++) {
			builder.append(items.get(i));
			if (i == items.size() - 2)
				builder.append(", & ");
			else if (i < items.size() - 2)
				builder.append(", ");
		}

		return builder.toString();
	}

	public boolean hasReq() {
		return requirement != null;
	}

	public ItemReq getRequirement() {
		return requirement;
	}

	public void setRequirement(ItemReq requirement) {
		this.requirement = requirement;
	}

	public boolean unlock(Item key) {
		if (requirement == null)
			return false;
		requirement.unlock(key);
		return !requirement.isActive();
	}

	public void lock() {
		requirement.lock();
	}

	public boolean hasRequirement() {
		return requirement != null;
	}

	public List<ItemReq> getNeighborReqs() {
		List<ItemReq> reqs = new ArrayList<>();
		Iterator<String> iterator = exits.keySet().iterator();

		while (iterator.hasNext()) {
			Room r = exits.get(iterator.next());
			if (r.hasReq())
				reqs.add(r.getRequirement());
		}

		return reqs;
	}

	public Narrator getNarrator() {
		return narrator;
	}

	public void setNarrator(Narrator narrator) {
		this.narrator = narrator;
	}

	public boolean hasNarrator() {
		return narrator != null;
	}

}
