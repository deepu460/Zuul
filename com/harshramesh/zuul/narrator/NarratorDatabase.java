package com.harshramesh.zuul.narrator;

import static java.lang.Math.random;
import static java.lang.Integer.MAX_VALUE;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

public class NarratorDatabase {

	private Map<String, Narrator> dBase;

	public NarratorDatabase() {
		dBase = new HashMap<>();
		setUp();
	}

	private void setUp() {
		// TODO: Set up the narrators.
		genHalls();
		genBase();
		genMisc();
	}

	private void genMisc() {
		dBase.put("kitchen", new Narrator(new String[] { "It's the kitchen",
				"This kitchen is so amazing!",
				"What do you want to do here in this kitchen?" }, "random"));
	}

	private void genBase() {
		dBase.put("start", new Narrator(new String[] { "Welcome to the game!",
				"Type \"help\" for help." }, "last repeat"));
		dBase.put("death", new Narrator(
				new String[] { "HAX" + genHaxString() }, "random"));
	}

	private void genHalls() {
		dBase.put("hall 1", new Narrator(new String[] { "This is a hallway.",
				"Hallways are epic.", "How many halls are there?",
				"I like pie." }, "random"));
		dBase.put("hall 2", dBase.get("hall 1"));
		dBase.put("hall 3", dBase.get("hall 1"));
		dBase.put("hall 4", dBase.get("hall 1"));
	}

	private String genHaxString() {
		StringBuilder builder = new StringBuilder();
		Random rand = new Random();
		for (int i = 0; i < rand.nextInt(MAX_VALUE); i++) {
			builder.append(rand.nextInt(++i) % 2 == 0 ? 0 : 1);
			if (random() < 0.04)
				builder.append("\n");
		}

		return builder.toString();
	}

	public void clear() {
		dBase.clear();
	}

	public boolean containsKey(Object arg0) {
		return dBase.containsKey(arg0);
	}

	public boolean containsValue(Object arg0) {
		return dBase.containsValue(arg0);
	}

	public Set<Entry<String, Narrator>> entrySet() {
		return dBase.entrySet();
	}

	public boolean equals(Object arg0) {
		return dBase.equals(arg0);
	}

	public Narrator get(Object arg0) {
		return dBase.get(arg0);
	}

	public int hashCode() {
		return dBase.hashCode();
	}

	public boolean isEmpty() {
		return dBase.isEmpty();
	}

	public Set<String> keySet() {
		return dBase.keySet();
	}

	public Narrator put(String arg0, Narrator arg1) {
		return dBase.put(arg0, arg1);
	}

	public void putAll(Map<? extends String, ? extends Narrator> arg0) {
		dBase.putAll(arg0);
	}

	public Narrator remove(Object arg0) {
		return dBase.remove(arg0);
	}

	public int size() {
		return dBase.size();
	}

	public Collection<Narrator> values() {
		return dBase.values();
	}

}