package com.harshramesh.zuul.item;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class ItemDatabase {

	private HashMap<String, Item> items;

	public ItemDatabase() {
		super();
		items = new HashMap<>();
		setUp();
	}

	private void setUp() {
		// Adds items
		items.put("flamethrower", new Item("a flamethrower", "flamethrower", 2));
		items.put("copper key", new Item("a copper key", "key", 0));
		items.put("bronze key", new Item("a bronze key", "key", 1));
		items.put("tuna", new Item("a can of tuna", "can of tuna", -1));
		items.put("water", new Item("a bottle of water", "bottle of water", -1));
		items.put("broom", new Item("a broom", "broom", -1));
	}

	public void clear() {
		items.clear();
	}

	public Object clone() {
		return items.clone();
	}

	public boolean containsKey(Object arg0) {
		return items.containsKey(arg0);
	}

	public boolean containsValue(Object arg0) {
		return items.containsValue(arg0);
	}

	public Set<Entry<String, Item>> entrySet() {
		return items.entrySet();
	}

	public boolean equals(Object arg0) {
		return items.equals(arg0);
	}

	public Item get(Object arg0) {
		return items.get(arg0);
	}

	public int hashCode() {
		return items.hashCode();
	}

	public boolean isEmpty() {
		return items.isEmpty();
	}

	public Set<String> keySet() {
		return items.keySet();
	}

	public Item put(String arg0, Item arg1) {
		return items.put(arg0, arg1);
	}

	public void putAll(Map<? extends String, ? extends Item> arg0) {
		items.putAll(arg0);
	}

	public Item remove(Object arg0) {
		return items.remove(arg0);
	}

	public int size() {
		return items.size();
	}

	public String toString() {
		return items.toString();
	}

	public Collection<Item> values() {
		return items.values();
	}

}